package client.viewmodel.newSparePart;

import client.model.spareParts.IMSparePart;
import client.model.ScooterModels.SModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.rmi.RemoteException;

public class NewSparePartViewModel {
    private IMSparePart model;

    private StringProperty partName;
    private StringProperty selectedModel;

    public NewSparePartViewModel(IMSparePart model) {
        System.out.println("constructor " + model);
        this.model = model;
        partName = new SimpleStringProperty();
        selectedModel = new SimpleStringProperty();
    }

    public StringProperty getPartNameProperty() {
        return partName;
    }

    public StringProperty getSelectedModel() {
        return selectedModel;
    }

    public void addPart() {
        try {
            System.out.println(model);
            model.addSparepart(partName.getValue(), new SModel(selectedModel.getValue()));

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


}
