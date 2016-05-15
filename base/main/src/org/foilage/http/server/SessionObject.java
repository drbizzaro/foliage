package org.foilage.http.server;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class SessionObject {

    private Date createdDate;

    private Date lastActionDate;

    private String sessionId;

    private String ipAddress;

    private List<Integer> roleList;

    private int userId;

    public SessionObject(Date createdDate, Date lastActionDate, String sessionId, String ipAddress) {
        this.createdDate = createdDate;
        this.lastActionDate = lastActionDate;
        this.sessionId = sessionId;
        this.ipAddress = ipAddress;
        this.roleList = Arrays.asList(0);
        this.userId = 0;
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

    public List<Integer> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Integer> roleList) {
        this.roleList = roleList;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
