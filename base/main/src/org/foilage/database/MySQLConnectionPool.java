package org.foilage.database;

import org.foilage.utils.ObjectPool;
import org.pmw.tinylog.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.foilage.utils.checkers.NullChecker.notNull;

public class MySQLConnectionPool {

    private final ObjectPool<Connection> pool;

    public MySQLConnectionPool(PoolSettings poolSettings, ConnectionCredentials credentials) {

        this.pool = new ObjectPool<Connection>(poolSettings.getPoolMinSize(), poolSettings.getPoolMaxSize(), poolSettings.getValidationRefreshTimeInSeconds()) {
            @Override
            protected Connection createObject() {

                try {
                    return DriverManager.getConnection("jdbc:mysql://localhost/"+credentials.getDatabase()+"?" +
                            "user="+credentials.getUser()+"&password="+credentials.getPassword()+"&useSSL=false&useUnicode=true&characterEncoding=utf-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone="+credentials.getServerTimeZone());
                } catch(SQLException e) {

                    Logger.error("Problem creating connection for database pool - "+e.getClass().toString()+" - "+e.getMessage());
                    return null;
                }
            }
        };
    }

    public Connection getConnection(CommitType commitType) {

        notNull(commitType, "MySQLConnectionPool.getConnection CommitType cant be null!");

        try {

            Connection con = pool.borrowObject();

            while(!con.isValid(1)) {

                con = pool.borrowObject();
            }

            if (commitType == CommitType.CONTROL) {

                con.setAutoCommit(false);

            } else {

                con.setAutoCommit(true);
            }

            return con;

        } catch(SQLException e) {

            Logger.error("Could not get database connection from pool "+e.getMessage());
            throw new RuntimeException("Could not get database connection from pool "+e.getMessage(), e);
        }
    }

    public void returnConnection(Connection con, PreparedStatement ps) {

        notNull(con, "MySQLConnectionPool.returnConnection Connection cant be null!");

        try {

            if(!con.getAutoCommit()) {
                con.commit();
            }

            if(ps!=null) {
                ps.close();
            }

            pool.returnObject(con);

        } catch(SQLException e) {

            Logger.error("MySQLConnectionPool.returnConnection Problem releasing pooled connection "+e.getMessage());
        }

    }
}
