package org.foilage.http.server.dao;

import org.foilage.authorization.exceptions.NotAuthenticatedException;
import org.foilage.http.server.model.SavedLogin;

public interface SavedLoginDAO {

    void add(SavedLogin savedLogin);

    SavedLogin getByHash(String hash) throws NotAuthenticatedException;

}
