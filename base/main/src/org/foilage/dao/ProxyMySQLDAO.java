package org.foilage.dao;

import org.foilage.database.MySQLConnectionPool;
import org.foilage.utils.log.Log;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.SQLException;

import static org.foilage.utils.checkers.NullChecker.notNull;

public abstract class ProxyMySQLDAO<T> {

    protected final MySQLConnectionPool connectionPool;

    public ProxyMySQLDAO(MySQLConnectionPool connectionPool) {
        this.connectionPool = notNull(connectionPool);
    }

    public T generateProxy() {

        T oldInstance = (T) this;

        return (T) Proxy.newProxyInstance(
                oldInstance.getClass().getClassLoader(),
                oldInstance.getClass().getInterfaces(),
                new DAOHandler<T>(oldInstance)
        );
    }

    public String generateSQLErrorMessage(SQLException e, Class daoClass, Method method) throws RuntimeException {

        return generateSQLErrorMessage(e, daoClass, method, "", true);
    }

    public String generateSQLErrorMessage(SQLException e, Class daoClass, Method method, boolean logError) throws RuntimeException {

        return generateSQLErrorMessage(e, daoClass, method, "", logError);
    }

    public String generateSQLErrorMessage(SQLException e, Class daoClass, Method method, String customErrorMessage) throws RuntimeException {

        return generateSQLErrorMessage(e, daoClass, method, customErrorMessage, true);
    }

    public String generateSQLErrorMessage(SQLException e, Class daoClass, Method method, String customErrorMessage, boolean logError) throws RuntimeException {

        StringBuilder msgBuilder = new StringBuilder();
        msgBuilder.append(daoClass.getName());
        msgBuilder.append(".");
        if(method != null) {
            msgBuilder.append(method.getName());
        }
        msgBuilder.append(e);

        msgBuilder.append(". ");
        msgBuilder.append(customErrorMessage);

        String msg = msgBuilder.toString();

        if(logError) {
            Log.error(msg);
        }

        return msg;
    }

}
