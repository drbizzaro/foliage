package org.foilage.authorization;

public interface User {

    int getId();

    String getLoginName();

    String getDisplayName();

    int[] getUserRoleIds();
}
