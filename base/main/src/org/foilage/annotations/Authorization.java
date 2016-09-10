package org.foilage.annotations;

import org.foilage.authorization.AuthType;
import org.foilage.authorization.UserRole;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface Authorization {

    AuthType authType() default AuthType.USER_AUTH;

    int[] accessRoles() default {UserRole.ADMIN};
    int[] denyRoles() default {
            UserRole.DISMISSED,
            UserRole.LOCKED,
            UserRole.MUST_CHANGE_PASSWORD,
            UserRole.NOT_AUTHENTICATED
    };

}