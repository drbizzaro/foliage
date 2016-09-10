package org.foilage.authorization;

import org.foilage.authorization.exceptions.NotAuthorizedException;

public abstract class AbstractAuthorizationPerformer implements AuthorizationPerformer {

    public AbstractAuthorizationPerformer() {
    }

    @Override
    public void authorize(AuthorizationAction authorizationAction, String resource) throws NotAuthorizedException {

        performGeneralAuthorization();

        performAuthorization(authorizationAction, resource);

    }

    protected void performGeneralAuthorization() throws NotAuthorizedException {
    }

    protected abstract void performAuthorization(AuthorizationAction authorizationAction, String resource) throws NotAuthorizedException;

}
