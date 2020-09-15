package client.view.sparePartsManager;

import client.model.ScooterModels.*;
import client.model.spareParts.SparePart;
import client.view.ViewHandler;
import client.viewmodel.sparePartsList.ModelsListViewModel;
import client.viewmodel.sparePartsList.SparePartViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import java.rmi.RemoteException;

public class SparePartsMController {
    @FXML
    private TableView<SparePart> sparePartsList;

    @FXML
    private TableColumn<SparePart, String> nameColumn;

    @FXML
    private TableColumn<SparePart, Integer> quantityColumn;

    @FXML
    private TableColumn<SparePart, Integer> amountNeededColumn;

    @FXML
    private TextArea commentArea;

    @FXML
    private ComboBox<String> modelList;

    private ViewHandler viewHandler;
    private ModelsListViewModel modelsViewModel;
    private SparePartViewModel sparePartsViewModel;
    private StringProperty currentModel;

    public void init(ModelsListViewModel modelsViewModel, SparePartViewModel sparePartsViewModel, ViewHandler viewHandler){
        this.viewHandler=viewHandler;
        this.modelsViewModel=modelsViewModel;
        this.sparePartsViewModel=sparePartsViewModel;
        currentModel= new SimpleStringProperty();
        currentModel.bindBidirectional(sparePartsViewModel.getCurrentModelProperty());
        currentModel.bindBidirectional(modelsViewModel.getCurrentModelProperty());
        commentArea.textProperty().bindBidirectional(sparePartsViewModel.getCommentsProperty());
        modelsViewModel.updateAllModels();
        inittialLoad();
        initCols();
    }

    public void inittialLoad() {
        sparePartsList.setItems(sparePartsViewModel.getSparePartsProperty());
        sparePartsList.setPlaceholder(new Label("No Content In List"));

        nameColumn.setCellValueFactory(new PropertyValueFactory<SparePart, String>("name"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<SparePart, Integer>("quantity"));
        amountNeededColumn.setCellValueFactory(new PropertyValueFactory<SparePart, Integer>("amountNeeded"));

        modelList.setItems(modelsViewModel.getModelsProperty());
        modelList.setPlaceholder(new Label("No models to show"));
        modelList.setValue("Choose");

        initCols();
    }

    public void initCols(){
        nameColumn.setCellValueFactory(new PropertyValueFactory<SparePart, String>("name"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<SparePart, Integer>("quantity"));
        amountNeededColumn.setCellValueFactory(new PropertyValueFactory<SparePart, Integer>("amountNeeded"));

        editableCols();
    }

    public void editableCols(){
        quantityColumn.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Integer>() {
            @Override
            public String toString(Integer i) {
                return Integer.toString(i);
            }

            @Override
            public Integer fromString(String s) {
                return Integer.parseInt(s);
            }
        }));



        quantityColumn.setOnEditCommit(e -> {

            e.getRowValue().setQuantity(e.getNewValue());

            ISModel model= new SModel(currentModel.getValue());
            sparePartsViewModel.editSparePart(e.getRowValue(), model);
        });

        amountNeededColumn.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Integer>() {
            @Override
            public String toString(Integer integer) {
                return Integer.toString(integer);
            }

            @Override
            public Integer fromString(String s) {
                return Integer.parseInt(s);
            }
        }));

        amountNeededColumn.setOnEditCommit(e -> {

            e.getRowValue().setAmountNeeded(e.getNewValue());

            ISModel model= new SModel(currentModel.getValue());
            sparePartsViewModel.editSparePart(e.getRowValue(), model);
            System.out.println("Controller object of part" +e.getRowValue().getAmountNeeded());
        });
    }

    public void onNewAccount(){
        viewHandler.openView("createAccount");
    }

    public void onNewModel(){
        viewHandler.openView("newModel");
    }

    public void onLogOff(){
        Stage stage = (Stage)sparePartsList.getScene().getWindow();
        viewHandler.closeView(stage);
        viewHandler.openView("logIn");
    }


    public void onModelList() {
       currentModel.setValue((String) modelList.getValue());

       sparePartsViewModel.getList(currentModel.getValue());

            sparePartsList.setRowFactory(tv -> new TableRow<>() {
                @Override
                public void updateItem(SparePart item, boolean empty) {
                    super.updateItem(item, empty);
                     if(item==null){setStyle("");}
                     else if(item.getQuantity() <= 10) {
                        setStyle("-fx-background-color: tomato;");
                    }
                }
            });

    }

    public void onNewSparePart() {
        viewHandler.openView("newsparepart");
    }

    public void onDeleteSparePart() {

        if(!sparePartsList.getSelectionModel().isEmpty()) {

            currentModel.setValue((String) modelList.getValue());
            SModel model=  new SModel(currentModel.getValue());
            sparePartsViewModel.removeSparePart(sparePartsList.getSelectionModel().getSelectedItem().getName(),model);
            sparePartsViewModel.getList(model.getModelName());
        }
    }

    public void onSubtract() {
        sparePartsViewModel.decrementPart(sparePartsList.getSelectionModel().getSelectedItem(),modelList.getValue());
    }

    public void onAdd() {
        sparePartsViewModel.incrementPart(sparePartsList.getSelectionModel().getSelectedItem(),modelList.getValue());

    }

    public void onPlaceOrder()
    {
        sparePartsViewModel.placeOrder();
    }

    public void onReceive() {
        if(!(sparePartsList.getSelectionModel().isEmpty())) {
            sparePartsViewModel.setCurrentSparepart(sparePartsList.getSelectionModel().getSelectedItem());
            viewHandler.openView("amountReceived");
        }
    }

    public void onLogAction()
    {
        if(!(sparePartsList.getSelectionModel().isEmpty())) {
            sparePartsViewModel.setCurrentSparepart(sparePartsList.getSelectionModel().getSelectedItem());
            viewHandler.openView("viewLog");
        }
    }
    public void onDeleteModel()
    {
        modelsViewModel.deleteModel();
    }

}
