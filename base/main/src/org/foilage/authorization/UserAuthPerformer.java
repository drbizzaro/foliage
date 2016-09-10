package org.foilage.authorization;

import org.foilage.authorization.exceptions.NotAuthorizedException;
import org.pmw.tinylog.Logger;

public class UserAuthPerformer extends AbstractAuthorizationPerformer {

    private final User user;

    protected final int[] accessRoles;
    protected final int[] denyAccessRoles;

    public UserAuthPerformer(User user, int[] accessRoles, int[] denyAccessRoles) {

        this.user = user;

        this.accessRoles = accessRoles;
        this.denyAccessRoles = denyAccessRoles;

    }

    @Override
    protected void performAuthorization(AuthorizationAction authorizationAction, String resource) throws NotAuthorizedException {

        isUserDeniedAccess(user, resource);

        isUserGrantedAccess(user, resource);

    }

    private void isUserDeniedAccess(User user, String resource) throws NotAuthorizedException {

        for (int role : denyAccessRoles) {

            for (int userRole: user.getUserRoleIds()) {
                if (role == userRole) {

                    Logger.info("User getId: " + user.getId() + " displayName: " + user.getDisplayName() + " denied access on role: " + role + " to: " + resource);
                    throw new NotAuthorizedException();
                }
            }
        }
    }

    private void isUserGrantedAccess(User user, String resource) throws NotAuthorizedException {

        boolean accessGranted = false;

        if (accessRoles.length == 0) {

            accessGranted = true;

        } else {

            for (int role : accessRoles) {

                for (int userRole: user.getUserRoleIds()) {
                    if(role == userRole) {
                        accessGranted = true;
                        break;
                    }
                }
            }
        }

        if (!accessGranted) {

            Logger.info("User getId: " + user.getId() + " displayName: " + user.getDisplayName() + " not granted access to: "+resource+", has not any of roles: " + accessRoles);
            throw new NotAuthorizedException();
        }

    }

}
