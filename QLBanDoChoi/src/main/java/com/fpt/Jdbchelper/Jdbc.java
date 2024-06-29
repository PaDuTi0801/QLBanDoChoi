/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fpt.Jdbchelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author hquan
 */
public class Jdbc {

    public static Connection getDBConnection() {
        Connection c = null;
        try {
            String connectionURL = "jdbc:sqlserver://localhost:1433;databaseName=QLBANDOCHOI;"
                    + "user=sa;"
                    + "password=123;" + "encrypt=false;" + "trustServerCertificate=false";
            c = DriverManager.getConnection(connectionURL);
        } catch (SQLException ex) {
            System.out.println("I am not connected to the Server");
        }
        return c;
    }
   
    public static PreparedStatement getStmt(String sql, Object... args) throws SQLException {
        Connection conn = getDBConnection();
        PreparedStatement stmt;
        if (sql.trim().startsWith("{")) {
            stmt = conn.prepareCall(sql);
        } else {
            stmt = conn.prepareCall(sql);
        }
        for (int i = 0; i < args.length; i++) {
            stmt.setObject(i + 1, args[i]);
        }
        return stmt;
    }

    public static ResultSet query(String sql, Object... args) throws SQLException {
        PreparedStatement stmt = Jdbc.getStmt(sql, args);
        return stmt.executeQuery();
    }

    public static Object value(String sql, Object... args) {
        try {
            ResultSet rs = Jdbc.query(sql, args);
            if (rs.next()) {
                return rs.getObject(0);
            }
            rs.getStatement().getConnection().close();
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static int update(String sql, Object... args) {
        try {
            PreparedStatement stmt = Jdbc.getStmt(sql, args);
            try {
                return stmt.executeUpdate();
            } finally {
                stmt.getConnection().close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
