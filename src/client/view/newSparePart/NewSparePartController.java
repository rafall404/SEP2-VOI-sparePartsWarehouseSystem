package client.view.newSparePart;

import client.view.ViewHandler;
import client.viewmodel.newSparePart.NewSparePartViewModel;
import client.viewmodel.sparePartsList.ModelsListViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewSparePartController
{
    @FXML
    private TextField partNameTextField;

    @FXML
    private ComboBox<String> modelList;

    private ViewHandler viewHandler;

    private NewSparePartViewModel newSparePartViewModel;

    public void init(ViewHandler viewHandler, NewSparePartViewModel newSparePartViewModel, ModelsListViewModel modelsListViewModel) {
        this.viewHandler = viewHandler;
        this.newSparePartViewModel = newSparePartViewModel;

        this.newSparePartViewModel.getPartNameProperty().bindBidirectional(partNameTextField.textProperty());
        modelList.setItems(modelsListViewModel.getModelsProperty());
    }

    public void onAddPartButton() {
        String value = modelList.getValue();
        StringProperty modelList = new SimpleStringProperty(value);

        this.newSparePartViewModel.getSelectedModel().bindBidirectional(modelList);
        Stage stage = (Stage) partNameTextField.getScene().getWindow(); // getting the stage
        newSparePartViewModel.addPart();
        viewHandler.closeView(stage);
    }

}
