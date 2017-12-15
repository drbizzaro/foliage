package org.foilage.http.server.services;

import org.foilage.annotations.Authorization;
import org.foilage.authorization.Role;
import org.foilage.authorization.User;
import org.foilage.authorization.exceptions.NotAuthenticatedException;
import org.foilage.http.server.model.SavedLogin;

public interface SavedLoginService {

    @Authorization(accessRoles = {Role.ADMIN, Role.USER})
    void add(User invocationUser, SavedLogin savedLogin);

    @Authorization(accessRoles = {Role.ADMIN, Role.USER, Role.NOT_AUTHENTICATED}, denyRoles = {Role.LOCKED, Role.DISMISSED})
    SavedLogin getByHash(User invocationUser, String hash) throws NotAuthenticatedException;
}
