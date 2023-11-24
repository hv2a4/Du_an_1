package com.quanlyquananvat.ThuVienTienIch;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcHelper {

    static String url = "jdbc:sqlserver://localhost:1433;databasename=Du_an_1;user=sa;password=123456;encrypt=false;";

    public static Connection connection() throws SQLException {
        return DriverManager.getConnection(url);
    }

    public static PreparedStatement ptm(String sql, Object... args) throws SQLException {
        Connection connection = connection();
        PreparedStatement ptm = null;
        if (sql.trim().startsWith("{")) {
            ptm = connection.prepareCall(sql);
        } else {
            ptm = connection.prepareStatement(sql);
        }
        for (int i = 0; i < args.length; i++) {

            ptm.setObject(i + 1, args[i]);
        }
        return ptm;
    }

    public static int update(String sql, Object... agrs) {
        try {
            PreparedStatement ptm = ptm(sql, agrs);
            try {
                return ptm.executeUpdate();

            } finally {
                ptm.getConnection().close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static ResultSet query(String sql, Object... agrs) {
        try {
            PreparedStatement ptm = ptm(sql, agrs);
            return ptm.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Object value(String sql, Object... args) {
        try {
            ResultSet rs = query(sql, args);
            if (rs.next()) {
                return rs.getObject(1);
            }
            rs.getStatement().close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
