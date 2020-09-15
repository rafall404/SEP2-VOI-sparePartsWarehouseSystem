package server.jdbc;

import java.sql.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class JDBC implements Runnable {

    private static JDBC databaseConnector;
    private static Lock lock = new ReentrantLock();
    private Connection con = null;
    private Statement st = null;

    private JDBC() {
    }

    public static JDBC getInstance() {
        if (databaseConnector == null) {
            synchronized (lock) {
                if (databaseConnector == null) {
                    databaseConnector = new JDBC();
                }
            }
        }
        return databaseConnector;
    }

    public void executeUpdate(String statement) {
        try {
            System.out.println(statement);
            st.executeUpdate(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet executeQuery(String statement) {
        ResultSet rs = null;
        try {
            rs = st.executeQuery(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public void run() {

        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "qwerty");
            st = con.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
