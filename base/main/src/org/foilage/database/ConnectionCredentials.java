package org.foilage.database;

public class ConnectionCredentials {

    private final String database;

    private final String user;

    private final String password;

    private final String serverTimeZone;

    public ConnectionCredentials(String database, String user, String password, String serverTimeZone) {
        this.database = database;
        this.user = user;
        this.password = password;
        this.serverTimeZone = serverTimeZone;
    }

    public String getDatabase() {
        return database;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getServerTimeZone() {
        return serverTimeZone;
    }
}
