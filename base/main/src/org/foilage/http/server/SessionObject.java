package org.foilage.http.server;

import java.util.Date;

public class SessionObject {

    private Date createdDate;

    private Date lastActionDate;

    private String sessionId;

    private String ipAddress;

    public SessionObject(Date createdDate, Date lastActionDate, String sessionId, String ipAddress) {
        this.createdDate = createdDate;
        this.lastActionDate = lastActionDate;
        this.sessionId = sessionId;
        this.ipAddress = ipAddress;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastActionDate() {
        return lastActionDate;
    }

    public void setLastActionDate(Date lastActionDate) {
        this.lastActionDate = lastActionDate;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}
