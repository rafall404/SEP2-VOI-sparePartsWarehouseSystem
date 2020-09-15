package client.viewmodel.log;

import client.model.ScooterModels.SModel;
import client.model.logModel.ILogger;
import client.model.spareParts.ISparePart;
import client.model.spareParts.SparePart;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class LogViewModel {
    private ILogger model;

    private ObservableList<String> logs;
    public StringProperty currentpart;
    public StringProperty smodel;

    public LogViewModel(ILogger model) {
        this.model = model;
        currentpart = new SimpleStringProperty();
        smodel = new SimpleStringProperty();
        logs = FXCollections.observableArrayList();
        try {
            model.addListener("change", evt -> {
                getList(new SparePart(currentpart.getValue()), new SModel(smodel.getValue()));
            });
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<String> getLogs() {

        return logs;
    }

    public void getList(ISparePart part, SModel model) {
        Platform.runLater(() -> {
            try {
                currentpart.setValue(part.getName());
                smodel.setValue(model.getModelName());
                ArrayList<String> parts = this.model.getLogList(part, model);
                logs.clear();
                if (this.model.getLogList(part, model) != null) {
                    for (int i = 0; i < parts.size(); i++) {
                        logs.add(parts.get(i));
                    }
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });
    }
}
