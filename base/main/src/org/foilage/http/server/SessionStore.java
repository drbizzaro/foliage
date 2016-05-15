package org.foilage.http.server;

import com.sun.org.apache.xml.internal.security.utils.Base64;
import org.foilage.utils.DateUtil;
import org.foilage.utils.Now;

import java.util.HashMap;
import java.util.Map;

public enum SessionStore {

    I;

    // Max idle time in Milliseconds
    private final int maxIdle = 60*60*1000;

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

                obj.setLastActionDate(Now.date());

                return true;
            }
        }
    }

    public SessionObject getSession(String sessionId, String ip) {

        if(sessionId==null || sessionId.length()==0) {

            return addSession(ip);

        } else {

            String id = sessionId.replace("X-FOILAGE-SESSION-ID=", "");

            SessionObject obj = activeMap.get(id);

            if (obj == null) {

                return addSession(ip);

            } else {

                if (Now.date().after(DateUtil.stepForward(obj.getLastActionDate(), maxIdle))) {

                    return addSession(ip);

                } else {

                    obj.setLastActionDate(Now.date());

                    return obj;
                }
            }
        }
    }

    private SessionObject addSession(String ip) {

        SessionObject sessionObject = new SessionObject(Now.date(), Now.date(), Base64.encode((Math.random()*10000000000000L+ip+System.currentTimeMillis()).getBytes()), ip, true);

        activeMap.put(sessionObject.getSessionId(), sessionObject);

        return sessionObject;
    }

}
