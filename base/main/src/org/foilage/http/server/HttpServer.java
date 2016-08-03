package org.foilage.http.server;

import org.foilage.http.StatusCode;
import org.pmw.tinylog.Logger;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class HttpServer {

    protected final HttpServerEnvironment environment;

    public static boolean SERVER_RUNNING = true;

    public static void main(String[] args) {

        List<ServerEndPoint> endPointList = new ArrayList<>();

        List<LogicWorker> logicList = new ArrayList<>();
        logicList.add(new TestLogicWorker());

        endPointList.add(new TestEndPoint(Arrays.asList("/"), logicList));
        endPointList.add(new TestEndPoint(Arrays.asList("/test"), new ArrayList<LogicWorker>()));
        endPointList.add(new TestEndPoint(Arrays.asList("/all/*"), new ArrayList<LogicWorker>()));

        List<ServerEndPoint> errorEndPointList = new ArrayList<>();

        errorEndPointList.add(new DefaultWWWErrorEndPoint(Arrays.asList(StatusCode.NOT_FOUND_404.getName()), "Page not found!", "Could not find a page with the provided url!"));

        List<Locale> localesList = new ArrayList<>();

        localesList.add(new Locale("sv", "SE"));
        localesList.add(Locale.ENGLISH);

        new HttpServer(
                new HttpServerEnvironment(
                        "#42",
                        8000,
                        8192,
                        false,
                        new File("/home/fredrik/Downloads/"),
                        new ArrayList<PreEndPointLogicWorker>(),
                        endPointList,
                        errorEndPointList,
                        new DefaultWWWErrorEndPoint(Arrays.asList("/error"), "An error occurred!", "Unfortunately an error has occurred that we couldn't handle."),
                        localesList
                )
        );
    }

    protected HttpServer(HttpServerEnvironment environment) {

        this.environment = environment;

        try {

            ServerSocket serverSocket = new ServerSocket(environment.getPort());

            Logger.info("Http server " + environment.getServerName() + " running on port " + environment.getPort() + ".");

            while(SERVER_RUNNING) {

                try {

                    new Thread(new ClientSocketThread(serverSocket.accept(), environment)).start();

                } catch(IOException e) {

                    Logger.error(e.getMessage());
                }
            }

        } catch(IOException e) {

            Logger.error(e.getMessage());
        }

        System.exit(0);
    }
}
