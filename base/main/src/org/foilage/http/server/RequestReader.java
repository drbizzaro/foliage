package org.foilage.http.server;

import org.foilage.http.RequestMethod;
import org.foilage.http.exceptions.HttpRequestLineException;
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

    public RequestData readInData(InputStream inputStream, int bufferSize) throws HttpRequestLineException, IOException {

        byte[] buffer = new byte[bufferSize];

        int read = 0;
        int bufferLength = 0;
        int splitByte = 0;

        while (read > -1 && splitByte <= 0) {

            try {

                read = inputStream.read(buffer, bufferLength, bufferSize - bufferLength);

            } catch (Exception e) {

                Logger.error(e.getMessage());
            }

            bufferLength += read;

            splitByte = findHeaderEnd(buffer, bufferLength);
        }

        if(bufferLength>0) {

            Logger.debug(new String(Arrays.copyOf(buffer, bufferLength)));

            String headerString = new String(Arrays.copyOf(buffer, bufferLength));

            List<String> requestDataLines = new ArrayList<>();

            for (String s : headerString.split("\r\n")) {

                requestDataLines.add(s);
            }

            String[] requestLine = requestDataLines.get(0).split("\\ ");

            RequestMethod requestMethod = parseRequestMethod(requestLine);

            Map<String,String> parameterMap = parseParameterMap(requestLine[1]);

            if(requestMethod==RequestMethod.POST) {

                parsePostParameters(parameterMap, requestDataLines.get(requestDataLines.size()-1));
            }

            return new RequestData(requestMethod, parseRequestURL(requestLine), parseHeaderMap(requestDataLines), parameterMap);

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

                requestMap.put(split[0].toLowerCase(), data.trim());
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
