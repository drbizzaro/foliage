package org.foilage.http.server;

import org.foilage.http.StatusCode;
import org.foilage.http.exceptions.URLNotFoundException;

import java.io.File;
import java.util.List;
import java.util.Locale;

public class HttpServerEnvironment {

    private final String serverName;

    private final int port;

    private final int bufferSize;

    private final boolean sessionsActive;

    private final File dataFilesRoot;

    private final List<ServerEndPoint> endPointList;

    private final List<ServerEndPoint> errorEndPointList;

    private final ServerEndPoint defaultErrorEndPoint;

    private final List<Locale> availableLanguages;

    public HttpServerEnvironment(String serverName, int port, int bufferSize, boolean sessionsActive, File dataFilesRoot, List<ServerEndPoint> endPointList, List<ServerEndPoint> errorEndPointList, ServerEndPoint defaultErrorEndPoint, List<Locale> availableLanguages) {

        this.serverName = serverName;
        this.port = port;
        this.bufferSize = bufferSize;
        this.sessionsActive = sessionsActive;
        this.dataFilesRoot = dataFilesRoot;
        this.endPointList = endPointList;
        this.errorEndPointList = errorEndPointList;
        this.defaultErrorEndPoint = defaultErrorEndPoint;
        this.availableLanguages = availableLanguages;
    }

    public String getServerName() {
        return serverName;
    }

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

    public ServerEndPoint findEndPointByUrl(String url) throws URLNotFoundException {

        for(ServerEndPoint endPoint: endPointList) {

            for(int i=0;i<endPoint.getPath().size();i++) {

                if (endPoint.isCatchAllBelow() && url.startsWith(endPoint.getPath().get(i).toString())) {

                    return endPoint;

                } else if (endPoint.getPath().get(i).toString().equalsIgnoreCase(url)) {

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

                if(path.equalsIgnoreCase(statusCode.getName())) {

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
