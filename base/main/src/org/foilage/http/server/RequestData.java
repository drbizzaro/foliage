package org.foilage.http.server;

import org.foilage.http.RequestMethod;
import org.pmw.tinylog.Logger;

import java.util.Map;

public class RequestData {

    private final RequestMethod method;

    private final String url;

    private final Map<String,String> headerMap;

    private final Map<String,String> requestMap;

    public RequestData(RequestMethod method, String url, Map<String, String> headerMap, Map<String,String> requestMap) {

        this.method = method;
        this.url = url;
        this.headerMap = headerMap;
        this.requestMap = requestMap;
    }

    public RequestMethod getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }

    public Map<String, String> getHeaderMap() {
        return headerMap;
    }

    public String getHeader(String header) {

        return headerMap.get(header);
    }

    public Map<String, String> getRequestMap() {
        return requestMap;
    }

    public String getParam(String parameter) {

        return requestMap.get(parameter);
    }

    public int getIntParam(String parameter) {

        try {

            return Integer.parseInt(requestMap.get(parameter));

        } catch (NumberFormatException e) {

            Logger.info("In parameter " + parameter + " with value " + requestMap.get(parameter) + " not an integer.");

            return Integer.MIN_VALUE;
        }
    }
}
