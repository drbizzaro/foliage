package org.foilage.http.server;

import org.foilage.http.RequestMethod;
import org.pmw.tinylog.Logger;

import java.util.Map;

import static org.foilage.utils.checkers.NullChecker.notNull;

public class RequestData {

    private final RequestMethod method;

    private final String baseUrl;

    private final String url;

    private final Map<String,String> headerMap;

    private final Map<String,String> parameterMap;

    private SessionObject session = null;

    public RequestData(RequestMethod method, String baseUrl, String url, Map<String, String> headerMap, Map<String,String> parameterMap) {

        this.method = notNull(method);
        this.baseUrl = notNull(baseUrl);
        this.url = notNull(url);
        this.headerMap = notNull(headerMap);
        this.parameterMap = notNull(parameterMap);

    }

    public RequestMethod getMethod() {
        return method;
    }

    public String getBaseUrl() { return baseUrl; }

    public String getUrl() {
        return url;
    }

    public Map<String, String> getHeaderMap() {
        return headerMap;
    }

    public String getHeader(String header) {

        return headerMap.get(header);
    }

    public boolean hasParam(String parameter) {

        return parameterMap.containsKey(parameter);
    }

    public Map<String, String> getParameterMap() {
        return parameterMap;
    }

    public String getParam(String parameter) {

        return parameterMap.get(parameter);
    }

    public int getIntParam(String parameter) {

        try {

            return Integer.parseInt(parameterMap.get(parameter));

        } catch (NumberFormatException e) {

            Logger.info("In parameter " + parameter + " with value " + parameterMap.get(parameter) + " not an integer.");

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
