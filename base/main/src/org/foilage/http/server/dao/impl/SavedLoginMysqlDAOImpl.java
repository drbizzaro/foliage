package org.foilage.http.server.dao.impl;

import org.foilage.authorization.exceptions.NotAuthenticatedException;
import org.foilage.dao.ProxyMySQLDAO;
import org.foilage.database.CommitType;
import org.foilage.database.MySQLConnectionPool;
import org.foilage.http.server.dao.SavedLoginDAO;
import org.foilage.http.server.model.SavedLogin;
import org.foilage.http.server.model.impl.SavedLoginImpl;

import java.sql.*;
import java.time.LocalDateTime;

public class SavedLoginMysqlDAOImpl extends ProxyMySQLDAO<SavedLoginMysqlDAOImpl> implements SavedLoginDAO {

    /*
    *

    CREATE TABLE `login_save` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `login_hash` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `valid_from` datetime NOT NULL,
  `valid_to` datetime NOT NULL,
  `login_ip` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;



    * */
    public SavedLoginMysqlDAOImpl(MySQLConnectionPool connectionPool) {
        super(connectionPool);
    }

    @Override
    public void add(SavedLogin savedLogin) {

        Connection con = null;
        PreparedStatement ps = null;

        try {

            con = connectionPool.getConnection(CommitType.AUTO);

            ps = prepareAdd(con, savedLogin);

            ps.execute();

        } catch (SQLException e) {

            throw new RuntimeException(generateSQLErrorMessage(e, this.getClass(), this.getClass().getEnclosingMethod()));

        } finally {

            connectionPool.returnConnection(con, ps);
        }
    }

    private PreparedStatement prepareAdd(Connection con, SavedLogin savedLogin) throws SQLException {

        String query = "INSERT INTO login_save(user_id, login_hash, valid_from, valid_to, login_ip) VALUES(?, ?, ?, ?, ?)";

        PreparedStatement ps = con.prepareStatement(query);

        ps.setInt(1, savedLogin.getUserId());
        ps.setString(2, savedLogin.getLoginHash());
        ps.setTimestamp(3, Timestamp.valueOf(savedLogin.getValidFrom()));
        ps.setTimestamp(4, Timestamp.valueOf(savedLogin.getValidTo()));
        ps.setString(5, savedLogin.getLoginIp());

        return ps;
    }

    @Override
    public SavedLogin getByHash(String hash) throws NotAuthenticatedException {

        Connection con = null;
        PreparedStatement ps = null;

        try {

            con = connectionPool.getConnection(CommitType.AUTO);

            ps = prepareGetByHash(con, hash);

            return executeGetByHash(ps, hash);

        } catch (SQLException e) {

            throw new RuntimeException(generateSQLErrorMessage(e, this.getClass(), this.getClass().getEnclosingMethod()));

        } finally {

            connectionPool.returnConnection(con, ps);
        }
    }

    private PreparedStatement prepareGetByHash(Connection con, String hash) throws SQLException {

        String query = "SELECT * FROM login_save WHERE login_hash = ? AND valid_from <= ? AND valid_to >= ?";

        PreparedStatement ps = con.prepareStatement(query);

        ps.setString(1, hash);
        ps.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
        ps.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));

        return ps;
    }

    private SavedLogin executeGetByHash(PreparedStatement ps, String hash) throws SQLException, NotAuthenticatedException {

        ResultSet rs = ps.executeQuery();

        if(rs.next()) {

            return createFromResultSet(rs);

        } else {

            throw new NotAuthenticatedException("Login attempt with saved hash not valid for hash "+hash);
        }
    }

    private SavedLogin createFromResultSet(ResultSet rs) throws SQLException {

        return new SavedLoginImpl(
                rs.getInt("id"),
                rs.getInt("user_id"),
                rs.getString("login_hash"),
                rs.getTimestamp("valid_from").toLocalDateTime(),
                rs.getTimestamp("valid_to").toLocalDateTime(),
                rs.getString("login_ip")
        );
    }

}
