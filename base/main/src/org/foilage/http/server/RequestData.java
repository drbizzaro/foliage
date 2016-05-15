package org.foilage.http.server;

import org.foilage.http.RequestMethod;
import org.pmw.tinylog.Logger;

import java.util.Map;

import static org.foilage.utils.checkers.NullChecker.notNull;

public class RequestData {

    private final RequestMethod method;

    private final String url;

    private final Map<String,String> headerMap;

    private final Map<String,String> requestMap;

    private SessionObject session = null;

    public RequestData(RequestMethod method, String url, Map<String, String> headerMap, Map<String,String> requestMap) {

        this.method = notNull(method);
        this.url = notNull(url);
        this.headerMap = notNull(headerMap);
        this.requestMap = notNull(requestMap);

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

    public SessionObject getSession() {
        return session;
    }

    public void setSession(SessionObject session) {
        this.session = session;
    }
}
