package client.viewmodel.scooterModel;

import client.model.ScooterModels.IMSModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.rmi.RemoteException;

public class NewModelVM {
    private StringProperty modelName;
    private IMSModel model;

    public NewModelVM(IMSModel model) {
        this.model = model;
        modelName = new SimpleStringProperty();
    }

    public StringProperty getModelNameProperty() {
        return modelName;
    }

    public void addScooterModel() {
        try {
            model.addModel(modelName.getValue());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


}
