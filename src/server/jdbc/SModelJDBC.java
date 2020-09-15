package server.jdbc;

import client.model.ScooterModels.ISModel;
import client.model.ScooterModels.SModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SModelJDBC {
    private JDBC database;

    public SModelJDBC() {
        database = JDBC.getInstance();
    }

    public void addModel(ISModel model) {
        String statement = "INSERT INTO " + "\"SEP2\"" + ".model(name) VALUES " + "( '" + model.getModelName() + "')";
        database.executeUpdate(statement);

    }

    public void removeModel(ISModel model) {
        String statement = "DELETE FROM" + "\"SEP2\"" + ".model WHERE" + " name =  '" + model.getModelName() + "'";
        database.executeUpdate(statement);
    }

    public ArrayList<ISModel> getAllModels() throws SQLException {
        ArrayList<ISModel> models = new ArrayList<>();
        String statement = "Select * FROM" + "\"SEP2\"" + ".model ";
        ResultSet rs = database.executeQuery(statement);
        while (rs.next()) {
            ISModel model = new SModel(rs.getString(2));
            models.add(model);
        }
        return models;
    }
}
