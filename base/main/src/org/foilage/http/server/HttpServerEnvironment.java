package org.foilage.http.server;

import org.foilage.http.StatusCode;
import org.foilage.http.exceptions.URLNotFoundException;

import java.io.File;
import java.util.List;
import java.util.Locale;

public class HttpServerEnvironment {

    private final String serverName;

    private final String domain;

    private final String baseUrl;

    private final int port;

    private final int bufferSize;

    private final boolean sessionsActive;

    private final File dataFilesRoot;

    private final List<PreEndPointLogicWorker> preEndPointWorkers;

    private final List<ServerEndPoint> endPointList;

    private final List<ServerEndPoint> errorEndPointList;

    private final ServerEndPoint defaultErrorEndPoint;

    private final List<Locale> availableLanguages;

    public HttpServerEnvironment(String serverName, String domain, String baseUrl, int port, int bufferSize, boolean sessionsActive, File dataFilesRoot, List<PreEndPointLogicWorker> preEndPointWorkers, List<ServerEndPoint> endPointList, List<ServerEndPoint> errorEndPointList, ServerEndPoint defaultErrorEndPoint, List<Locale> availableLanguages) {

        this.serverName = serverName;
        this.domain = domain;
        this.baseUrl = baseUrl;
        this.port = port;
        this.bufferSize = bufferSize;
        this.sessionsActive = sessionsActive;
        this.dataFilesRoot = dataFilesRoot;
        this.preEndPointWorkers = preEndPointWorkers;
        this.endPointList = endPointList;
        this.errorEndPointList = errorEndPointList;
        this.defaultErrorEndPoint = defaultErrorEndPoint;
        this.availableLanguages = availableLanguages;
    }

    public String getServerName() {
        return serverName;
    }

    public String getDomain() {
        return domain;
    }

    public String getBaseUrl() {return baseUrl;}

    public int getPort() {
        return port;
    }

    public int getBufferSize() {
        return bufferSize;
    }

    public boolean isSessionsActive() {
        return sessionsActive;
    }

    public File getDataFilesRoot() {
        return dataFilesRoot;
    }

    public List<PreEndPointLogicWorker> getPreEndPointWorkers() {

        return preEndPointWorkers;
    }

    public ServerEndPoint findEndPointByUrl(String url) throws URLNotFoundException {

        for(ServerEndPoint endPoint: endPointList) {

            for(int i=0;i<endPoint.getPath().size();i++) {

                if (endPoint.isCatchAllBelow() && url.startsWith(endPoint.getPath().get(i).toString())) {

                    return endPoint;

                } else if (endPoint.getPath().get(i).toString().equalsIgnoreCase(url) || (endPoint.getPath().get(i).toString()+"/").equalsIgnoreCase(url)) {

                    return endPoint;
                }
            }
        }

        throw new URLNotFoundException("URL "+url+" not found on server!");
    }

    public ServerEndPoint findErrorEndPointByStatusCode(StatusCode statusCode) {

        for(ServerEndPoint endPoint: errorEndPointList) {

            List<String> pathList = endPoint.getPath();

            for(String path: pathList) {

                if(path.equalsIgnoreCase(statusCode.getId()+"")) {

                    return endPoint;
                }
            }
        }

        return defaultErrorEndPoint;
    }

    public List<Locale> getAvailableLanguages() {
        return availableLanguages;
    }
}
