package server.jdbc;

import client.model.ScooterModels.SModel;
import client.model.spareParts.SparePart;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LogJDBC {
    private JDBC database;

    public LogJDBC() {
        database = JDBC.getInstance();

    }

    public void logToDatabase(String log) {
        String statement = "INSERT INTO " + "\"SEP2\"" + ".log(logstamp) VALUES " + "( '" + log + "')";
        database.executeUpdate(statement);
    }

    public ArrayList<String> getDatabaseLogs(SparePart part, SModel model) {
        String statement = "SELECT * FROM " + "\"SEP2\"" + ".log WHERE logstamp like " + "'%" + part.getName() + "%'" + " And  logstamp like" + "'%" + model.getModelName() + "%'";
        ResultSet rs = database.executeQuery(statement);
        ArrayList<String> logs = new ArrayList<>();
        try {
            while (rs.next()) {
                String log = rs.getString(1);
                logs.add(log);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return logs;
    }
}
