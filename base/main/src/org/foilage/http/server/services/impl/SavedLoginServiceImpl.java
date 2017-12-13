package org.foilage.http.server.services.impl;

import org.foilage.authorization.User;
import org.foilage.http.server.model.SavedLogin;
import org.foilage.http.server.services.SavedLoginService;
import org.foilage.services.ProxyService;

public class SavedLoginServiceImpl extends ProxyService<SavedLoginServiceImpl> implements SavedLoginService {

    public SavedLoginServiceImpl() {
    }

    @Override
    public void add(User invocationUser, SavedLogin savedLogin) {

    }

    @Override
    public SavedLogin getByHash(User invocationUser, String hash) {
        return null;
    }
}
