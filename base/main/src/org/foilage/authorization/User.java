package org.foilage.authorization;

import java.util.List;

public interface User {

    int getId();

    String getLoginName();

    String getDisplayName();

    List<Role> getUserRoles();
}
