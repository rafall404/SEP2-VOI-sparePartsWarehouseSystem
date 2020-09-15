package server.serverManager;

import shared.PropertyChangeSubject;
import client.model.ScooterModels.ISModel;
import client.model.spareParts.ISparePart;
import client.model.spareParts.SparePart;
import server.jdbc.SparePartsJDBC;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.SQLException;
import java.util.ArrayList;

public class SparePartsSManager implements PropertyChangeSubject {
    private SparePartsJDBC sparePartsJDBC;

    private PropertyChangeSupport support = new PropertyChangeSupport(this);

    public SparePartsSManager() {
        sparePartsJDBC = new SparePartsJDBC();
    }

    public void addSparePart(ISparePart sparePart, ISModel model) {
        sparePartsJDBC.addSparePart(sparePart,model);
        support.firePropertyChange("change", null, model);

    }

    public void removeSparePart(ISparePart sparePart, ISModel model) {
        sparePartsJDBC.removeSparePart(sparePart,model);
        support.firePropertyChange("change", null, model);
    }

    public ArrayList<SparePart> getAllSpareParts(ISModel model) {

        ArrayList<SparePart> partArrayList= new ArrayList<>();
        try {
            partArrayList= sparePartsJDBC.getAllSpareParts(model);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return partArrayList;
    }

    public void editSparePart(ISparePart part, ISModel model, int quantity, int amountNeeded)
    {
        sparePartsJDBC.editSparePart(part, model, quantity, amountNeeded);
        support.firePropertyChange("change", null, model);
    }

    public void incrementSparePartQuantity(ISparePart part, String scooterModel) {
        sparePartsJDBC.incrementSparePartQuantity(part,scooterModel);
        support.firePropertyChange("change",null,scooterModel);
    }

    public void decrementSparePartQuantity(ISparePart part, String scooterModel) {
        sparePartsJDBC.decrementSparePartQuantity(part,scooterModel);
        support.firePropertyChange("change",null,scooterModel);
    }

    @Override
    public void addListener(String names, PropertyChangeListener listener)
    {
        support.addPropertyChangeListener(names,listener);
    }

    @Override
    public void removeListener(String names, PropertyChangeListener listener)
    {

    }


}
