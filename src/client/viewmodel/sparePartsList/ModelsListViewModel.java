package client.viewmodel.sparePartsList;

import client.model.ScooterModels.IMSModel;
import client.model.ScooterModels.ISModel;
import client.model.ScooterModels.SModel;
import client.view.sparePartsManager.SparePartsMController;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.beans.PropertyChangeEvent;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class ModelsListViewModel {
    private IMSModel model;
    private ObservableList<String> models;
    private SimpleStringProperty currentModel = new SimpleStringProperty();

    public ObservableList<String> getModelsProperty() {
        return models;
    }

    public SimpleStringProperty getCurrentModelProperty() {
        return currentModel;
    }

    public ModelsListViewModel(IMSModel model) {
        this.model = model;
        models = FXCollections.observableArrayList();
        try {
            model.addListener("addedModel", evt -> updateModels(evt));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        try {
            model.addListener("deletedModel", evt -> deleteModel(evt));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


    public void updateAllModels() {
        ArrayList<ISModel> modelcollection = new ArrayList<>();
        try {
            modelcollection = model.getAllModels();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < modelcollection.size(); i++) {
            ISModel model = modelcollection.get(i);
            if (!(models.contains(model.getModelName()))) {
                models.add(model.getModelName());
            }
        }
    }

    public void updateModels(PropertyChangeEvent evt) {
        Platform.runLater(() -> models.add((String) evt.getNewValue()));
    }

    public void deleteModel(PropertyChangeEvent evt) {
        Platform.runLater(() -> models.remove((String) evt.getNewValue()));
    }

    public void deleteModel() {
        try {
            System.out.println(currentModel.getValue());
            model.removeModel(new SModel(currentModel.getValue()));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
