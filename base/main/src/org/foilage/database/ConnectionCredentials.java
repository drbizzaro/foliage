package org.foilage.database;

import static org.foilage.utils.checkers.GreaterThenZeroChecker.greaterThenZero;
import static org.foilage.utils.checkers.HttpURLChecker.containsOnlyValidCharacters;
import static org.foilage.utils.checkers.NullChecker.notNull;

public class ConnectionCredentials {

    private final String database;

    private final int port;

    private final String user;

    private final String password;

    private final String serverTimeZone;

    public ConnectionCredentials(String database, int port, String user, String password, String serverTimeZone) {

        this.database = containsOnlyValidCharacters(notNull(database));
        this.port = greaterThenZero(port);
        this.user = containsOnlyValidCharacters(notNull(user));
        this.password = containsOnlyValidCharacters(notNull(password));
        this.serverTimeZone = notNull(serverTimeZone);
    }

    public String getDatabase() {
        return database;
    }

    public int getPort() {return port;}

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
