package org.foilage.authorization;

import org.foilage.authorization.exceptions.NotAuthorizedException;

/**
 * This is the interface for the classes that do the actual authorization.
 */
public interface AuthorizationPerformer {

    void authorize(AuthorizationAction authorizationAction, String resource) throws NotAuthorizedException;

}
