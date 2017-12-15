package org.foilage.http.server;

import org.foilage.authorization.Role;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.foilage.utils.checkers.NullChecker.notNull;

public class SessionObject {

    private Date createdDate;

    private Date lastActionDate;

    private String sessionId;

    private String ipAddress;

    private List<Role> roleList;

    private int userId;

    private boolean createClientCookie;

    private boolean createAutoLoginCookie;

    public SessionObject(Date createdDate, Date lastActionDate, String sessionId, String ipAddress, boolean createClientCookie, boolean createAutoLoginCookie) {
        this.createdDate = notNull(createdDate);
        this.lastActionDate = notNull(lastActionDate);
        this.sessionId = notNull(sessionId);
        this.ipAddress = notNull(ipAddress);
        this.roleList = new ArrayList<>();
        this.roleList.add(Role.NOT_AUTHENTICATED);
        this.userId = 0;
        this.createClientCookie = createClientCookie;
        this.createAutoLoginCookie = createAutoLoginCookie;
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

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean isCreateClientCookie() {
        return createClientCookie;
    }

    public void setCreateClientCookie(boolean createClientCookie) {
        this.createClientCookie = createClientCookie;
    }

    public boolean isCreateAutoLoginCookie() {
        return createAutoLoginCookie;
    }

    public void setCreateAutoLoginCookie(boolean createAutoLoginCookie) {
        this.createAutoLoginCookie = createAutoLoginCookie;
    }
}
