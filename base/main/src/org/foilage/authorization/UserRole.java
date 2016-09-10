package org.foilage.authorization;

public interface UserRole {

    Integer NOT_AUTHENTICATED = 0;

    Integer DISMISSED = 1;
    Integer LOCKED = 2;
    Integer MUST_CHANGE_PASSWORD = 3;

    Integer USER = 100;

    Integer USER_CREATOR = 199;
    Integer ADMIN = 200;

}
