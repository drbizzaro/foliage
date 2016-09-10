package org.foilage.services;


import org.foilage.annotations.Authorization;
import org.foilage.authorization.AuthType;
import org.foilage.authorization.User;
import org.foilage.authorization.UserAuthAction;
import org.foilage.authorization.UserAuthPerformer;
import org.foilage.authorization.exceptions.NotAuthorizedException;
import org.foilage.utils.checkers.AbstractNullChecker;
import org.pmw.tinylog.Logger;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ServiceHandler<T> extends AbstractNullChecker implements InvocationHandler {

    protected T delegate;

    public ServiceHandler(T delegate) {

        this.delegate = delegate;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] argumentList) throws Throwable {

        try {

            notNullChecks(method, argumentList);

            authorization(method, argumentList);

            return method.invoke(delegate, argumentList);

        } catch (InvocationTargetException e) {
            String msg = method.getDeclaringClass().getName()+"."+method.getName() + " - " + e.getCause();
            Logger.error(msg);
            throw e.getCause();
        }
    }

    private void authorization(Method method, Object[] argumentList) throws NotAuthorizedException {

        for(Annotation annotation: method.getAnnotations()) {

            if(annotation.annotationType() == Authorization.class) {

                performAuthorization((Authorization)annotation, method, argumentList);
            }
        }
    }

    private void performAuthorization(Authorization authAnnotation, Method method, Object[] argumentList) throws NotAuthorizedException {

        if(authAnnotation.authType() == AuthType.USER_AUTH) {

            UserAuthPerformer userAuthPerformer = new UserAuthPerformer(
                    getInvocationUser(method, argumentList),
                    authAnnotation.accessRoles(),
                    authAnnotation.denyRoles()
            );

            userAuthPerformer.authorize(UserAuthAction.HAS_VALID_ROLE, method.getDeclaringClass().getCanonicalName()+":"+method.getName());

        } else if(authAnnotation.authType() == AuthType.SPECIAL_SUB_CLASSED) {

            subClassedAuthorization(authAnnotation, method, argumentList, getInvocationUser(method, argumentList));

        }
    }

    protected void subClassedAuthorization(Authorization authAnnotation, Method method, Object[] argumentList, User invocationUser) throws NotAuthorizedException {}

    private User getInvocationUser(Method method, Object[] argumentList) {

        try {
            for(Object obj: argumentList) {

                if(obj instanceof User) {

                    return (User)obj;
                }
            }
        } catch(NullPointerException e) {

            Logger.error(method.getName()+" has authorization annotation and must have a invocation user as in parameter!");
        }

        String message = generateInvocationUserErrorMessage(method);
        Logger.error(message);
        throw new IllegalArgumentException(message);
    }

    private String generateInvocationUserErrorMessage(Method method) {

        StringBuilder messageBuilder = new StringBuilder();

        messageBuilder.append("No invocation user sent into method ");
        messageBuilder.append(method.getName());
        messageBuilder.append(" of class ");
        messageBuilder.append(method.getDeclaringClass().getName());

        return messageBuilder.toString();
    }

}