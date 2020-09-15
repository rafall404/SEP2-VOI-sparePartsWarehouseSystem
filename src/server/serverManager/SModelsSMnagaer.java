package server.serverManager;

import client.model.ScooterModels.ISModel;
import client.model.spareParts.ISparePart;
import server.jdbc.SModelJDBC;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public class SModelsSMnagaer {
    private SModelJDBC sModelJDBC;

    public SModelsSMnagaer() {
        this.sModelJDBC = new SModelJDBC();
    }

    public void addModel(ISModel model) {
        sModelJDBC.addModel(model);
    }

    public void removeModel(ISModel model) {
        sModelJDBC.removeModel(model);
    }

    public ArrayList<ISModel> getAllModels() {
        try {
            return sModelJDBC.getAllModels();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
