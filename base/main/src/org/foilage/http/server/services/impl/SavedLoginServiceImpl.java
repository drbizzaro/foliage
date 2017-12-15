package org.foilage.http.server.services.impl;

import org.foilage.authorization.User;
import org.foilage.authorization.exceptions.NotAuthenticatedException;
import org.foilage.http.server.dao.SavedLoginDAO;
import org.foilage.http.server.model.SavedLogin;
import org.foilage.http.server.services.SavedLoginService;
import org.foilage.services.ProxyService;

import static org.foilage.utils.checkers.NullChecker.notNull;

public class SavedLoginServiceImpl extends ProxyService<SavedLoginServiceImpl> implements SavedLoginService {

    private final SavedLoginDAO savedLoginDAO;

    public SavedLoginServiceImpl(SavedLoginDAO savedLoginDAO) {

        this.savedLoginDAO = notNull(savedLoginDAO);
    }

    @Override
    public void add(User invocationUser, SavedLogin savedLogin) {

        savedLoginDAO.add(savedLogin);
    }

    @Override
    public SavedLogin getByHash(User invocationUser, String hash) throws NotAuthenticatedException {

        return savedLoginDAO.getByHash(hash);
    }
}
