package org.foilage.http.server;

import org.foilage.utils.Now;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public enum SessionStore {

    I;

    // Max idle time in seconds
    private final int maxIdle = 60*60;

    private Map<String,SessionObject> activeMap;

    SessionStore() {
        this.activeMap = new HashMap<>();
    }

    public boolean hasSession(String sessionId, String ip) {

        if(sessionId==null || sessionId.length()==0) {

            return false;

        } else {

            String id = sessionId.replace("X-FOILAGE-SESSION-ID=", "");

            SessionObject obj = activeMap.get(id);

            if (obj == null) {

                return false;

            } else {

                obj.setLastActionDate(Now.localDateTime());

                return true;
            }
        }
    }

    public SessionObject getSession(String sessionId, String ip) {

        if(sessionId==null || sessionId.length()==0) {

            return addSession(ip);

        } else {

            String id = "";

            for(String parameter:sessionId.split(";")) {

                if(parameter.trim().startsWith("X-FOILAGE-SESSION-ID=")) {

                    id = parameter.trim().replace("X-FOILAGE-SESSION-ID=", "");

                }
            }

            SessionObject obj = activeMap.get(id);

            if (obj == null) {

                return addSession(ip);

            } else if(!ip.equalsIgnoreCase(obj.getIpAddress())) {

                return addSession(ip);

            } else {

                if (Now.localDateTime().isAfter(obj.getLastActionDate().plusSeconds(maxIdle))) {

                    return addSession(ip);

                } else {

                    obj.setLastActionDate(Now.localDateTime());

                    return obj;
                }
            }
        }
    }

    private SessionObject addSession(String ip) {

        SessionObject sessionObject = new SessionObject(Now.localDateTime(), Now.localDateTime(), Base64.getEncoder().encodeToString((Math.random()*10000000000000L+ip+System.currentTimeMillis()).getBytes(StandardCharsets.UTF_8)), ip, true, false);

        activeMap.put(sessionObject.getSessionId(), sessionObject);

        return sessionObject;
    }

}
