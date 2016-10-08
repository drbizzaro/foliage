package org.foilage.database;

import static org.foilage.utils.checkers.HttpURLChecker.containsOnlyValidCharacters;
import static org.foilage.utils.checkers.NullChecker.notNull;

public class ConnectionCredentials {

    private final String database;

    private final String user;

    private final String password;

    private final String serverTimeZone;

    public ConnectionCredentials(String database, String user, String password, String serverTimeZone) {

        this.database = containsOnlyValidCharacters(notNull(database));
        this.user = containsOnlyValidCharacters(notNull(user));
        this.password = containsOnlyValidCharacters(notNull(password));
        this.serverTimeZone = notNull(serverTimeZone);
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
