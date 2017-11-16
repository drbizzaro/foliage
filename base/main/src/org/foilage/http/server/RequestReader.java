package org.foilage.http.server;

import org.foilage.http.RequestMethod;
import org.foilage.http.exceptions.HttpRequestLineException;
import org.foilage.model.MimeType;
import org.pmw.tinylog.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;

public enum RequestReader {

    INSTANCE;

    RequestReader() {
    }

    public RequestData readInData(String baseUrl, InputStream inputStream, int bufferSize) throws HttpRequestLineException, IOException {

        byte[] buffer = new byte[bufferSize];
        byte[] dataBuffer;

        int read = 0;
        int bufferLength = 0;
        int splitByte = 0;
        int contentLength = -1;

        Map<String,String> headerMap = new HashMap<>();

        while (read > -1 && contentLength!=bufferLength) {

            try {

                read = inputStream.read(buffer, bufferLength, bufferSize - bufferLength);

            } catch (Exception e) {

                Logger.error(e.getMessage());
            }

            bufferLength += read;

            if(splitByte==0) {

                splitByte = findHeaderEnd(buffer, bufferLength);

                if(splitByte!=0) {

                    String headerString = new String(Arrays.copyOf(buffer, splitByte));

                    headerMap = parseHeaderMap(Arrays.asList(headerString.split("\r\n")));
                    Logger.debug(headerString);
                    if(headerMap.containsKey("content-length")) {

                        contentLength = splitByte+Integer.parseInt(headerMap.get("content-length"));

                    } else {

                         contentLength = bufferLength;
                    }

                }
            }
        }

        if(bufferLength>0) {

            Logger.trace(new String(Arrays.copyOf(buffer, bufferLength)));

            String headerString = new String(Arrays.copyOf(buffer, bufferLength));

            List<String> requestDataLines = new ArrayList<>();

            dataBuffer = new byte[bufferLength-splitByte];
            System.arraycopy(buffer, splitByte, dataBuffer, 0, bufferLength-splitByte);

            for (String s : headerString.split("\r\n")) {

                requestDataLines.add(s);
            }

            String[] requestLine = requestDataLines.get(0).split("\\ ");

            RequestMethod requestMethod = parseRequestMethod(requestLine);

            Map<String,String> parameterMap = new HashMap<>();
            List<UploadedFile> fileList = new ArrayList<>();

            if(headerMap.containsKey("content-type") && headerMap.get("content-type").startsWith("multipart/form-data")) {

                String boundaryString = "--"+headerMap.get("content-type").split("boundary=")[1];

                boolean startFound = false;

                for(int pos=0;pos<requestDataLines.size();pos++) {

                    if(startFound) {

                        if (boundaryString.equals(requestDataLines.get(pos))) {

                            pos++;

                            String data = "";
                            String name = "";
                            String fileName = "";
                            MimeType mimeType = MimeType.NONE;

                            for(; !boundaryString.equals(requestDataLines.get(pos)) && !(boundaryString+"--").equals(requestDataLines.get(pos)) ;pos++) {

                                if(requestDataLines.get(pos).startsWith("Content-Disposition")) {

                                    for(String d: requestDataLines.get(pos).split(";")) {

                                        if(d.startsWith(" name=")) {

                                            name = d.split("\\\"")[1];

                                        } else if(d.startsWith(" filename=")) {

                                            fileName = d.split("\\\"")[1];
                                        }
                                    }

                                } else if(requestDataLines.get(pos).startsWith("Content-Type")) {

                                    mimeType = MimeType.parseType(requestDataLines.get(pos).split(": ")[1]);

                                } else if(requestDataLines.get(pos).length()>0) {

                                    data += requestDataLines.get(pos);
                                }
                            }

                            if(mimeType == MimeType.NONE) {

                                parameterMap.put(name, data);

                            } else {

                                fileList.add(new UploadedFile(fileName, mimeType, data.getBytes()));
                            }

                            pos--;
                        }

                    } else {

                        if(requestDataLines.get(pos).length()==0) {
                            startFound = true;
                        }
                    }
                }

            } else {

                parameterMap = parseParameterMap(requestLine[1]);

                if(requestMethod==RequestMethod.POST) {

                    parsePostParameters(parameterMap, requestDataLines.get(requestDataLines.size()-1));
                }
            }

            return new RequestData(requestMethod, baseUrl, parseRequestURL(requestLine), headerMap, parameterMap, fileList, new String(dataBuffer));

        } else{

            throw new HttpRequestLineException("Request line wrongly formatted, empty request.");
        }
    }

    private RequestMethod parseRequestMethod(String[] requestLine) throws HttpRequestLineException {

        if (requestLine.length == 3) {

            return RequestMethod.findByName(requestLine[0]);
        }

        throw new HttpRequestLineException("Request line wrongly formatted, not containing 3 parts.");
    }

    private String parseRequestURL(String[] requestLines) {

        return requestLines[1].split("\\?")[0];
    }

    private int findHeaderEnd(final byte[] buf, int bufferLength) {

        int splitbyte = 0;
        while (splitbyte + 3 < bufferLength) {
            if (buf[splitbyte] == '\r' && buf[splitbyte + 1] == '\n' && buf[splitbyte + 2] == '\r' && buf[splitbyte + 3] == '\n') {
                return splitbyte + 4;
            }
            splitbyte++;
        }
        return 0;
    }

    private Map<String,String> parseHeaderMap(List<String> dataList) {

        Map<String,String> requestMap = new HashMap<>();

        for(int i=1;i<dataList.size();i++) {

            String[] split = dataList.get(i).split(":");

            if(split.length>1) {

                String data = "";

                for(int si=1;si<split.length;si++) {

                    if(si>1) {

                        data += ":";
                    }

                    data += split[si];
                }

                if("content-type".equalsIgnoreCase(split[0]) && data.startsWith("multipart/form-data")) {

                    requestMap.put(split[0].toLowerCase(), data.split(";")[0].trim());

                } else {

                    requestMap.put(split[0].toLowerCase(), data.trim());
                }
            }
        }

        return requestMap;
    }

    private Map<String,String> parseParameterMap(String urlString) {

        Map<String,String> parameterMap = new HashMap<>();

        if(urlString.contains("?")) {

            String[] paramArray = urlString.split("\\?")[1].split("\\&");

            for(String param: paramArray) {

                try {

                    String[] p = param.split("=");

                    parameterMap.put(p[0], URLDecoder.decode(p[1], "UTF-8"));

                } catch (ArrayIndexOutOfBoundsException e) {

                    Logger.info("Malformed url parameter "+param);

                } catch (UnsupportedEncodingException e) {

                    Logger.error("Unsupported encoding url parameter "+param);
                }
            }
        }

        return parameterMap;
    }

    private void parsePostParameters(Map<String,String> parameterMap, String parameterLine) {

        for(String param: parameterLine.split("&")) {

            if(param.contains("=")) {

                String[] val = param.split("=");

                if(val.length>1) {

                    try {

                        parameterMap.put(val[0], URLDecoder.decode(val[1], "UTF-8"));

                    } catch (UnsupportedEncodingException e) {

                        Logger.error("Unsupported encoding post parameter "+param);
                    }

                } else {

                    parameterMap.put(val[0], "");
                }
            }
        }
    }

}
