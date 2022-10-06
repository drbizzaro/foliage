package org.foilage.authorization;

import org.foilage.authorization.exceptions.NotAuthorizedException;
import org.foilage.utils.log.Log;

public class UserAuthPerformer extends AbstractAuthorizationPerformer {

    private final User user;

    protected final Role[] accessRoles;
    protected final Role[] denyAccessRoles;

    public UserAuthPerformer(User user, Role[] accessRoles, Role[] denyAccessRoles) {

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

        for (Role role : denyAccessRoles) {

            for (Role userRole: user.getUserRoles()) {
                if (role == userRole) {

                    Log.info("User getId: " + user.getId() + " displayName: " + user.getDisplayName() + " denied access on role: " + role + " to: " + resource);
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

            for (Role role : accessRoles) {

                for (Role userRole: user.getUserRoles()) {
                    if(role == userRole) {
                        accessGranted = true;
                        break;
                    }
                }
            }
        }

        if (!accessGranted) {

            StringBuilder errorBuilder = new StringBuilder();

            errorBuilder.append("User getId: ");
            errorBuilder.append(user.getId());
            errorBuilder.append(" displayName: ");
            errorBuilder.append(user.getDisplayName());
            errorBuilder.append(" not granted access to: ");
            errorBuilder.append(resource);
            errorBuilder.append(", has not any of roles: [");
            for(Role role: accessRoles) {

                errorBuilder.append(role.name());
                errorBuilder.append(",");
            }
            errorBuilder.append("]");

            Log.info(errorBuilder.toString());
            throw new NotAuthorizedException();
        }

    }

}
