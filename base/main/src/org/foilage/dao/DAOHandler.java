package org.foilage.dao;

import org.foilage.utils.checkers.AbstractNullChecker;
import org.pmw.tinylog.Logger;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DAOHandler<T> extends AbstractNullChecker implements InvocationHandler {

    protected T delegate;

    public DAOHandler(T delegate) {
        this.delegate = delegate;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] argumentList) throws Throwable {

        try {

            notNullChecks(method, argumentList);

            Object obj = method.invoke(delegate, argumentList);

            return obj;

        } catch (InvocationTargetException e) {

            String msg = "DAOHandler reports: "+method.getDeclaringClass().getName()+"."+method.getName() + " - " + e.getCause();
            Logger.debug(msg);
            throw e.getCause();
        }
    }
}
