package client.view.createaccount;

import client.view.ViewHandler;
import client.viewmodel.createAccount.CreateAccountViewModel;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.CheckBox;

import java.rmi.RemoteException;
import java.sql.SQLException;


public class CreateAccountController
{
    @FXML
    TextField userNameTextField;

    @FXML
    TextField passwordTextField;

    @FXML
    TextField passwordConfTextField;

    @FXML
    CheckBox isManager;

    private ViewHandler handler;
    private CreateAccountViewModel viewModel;
    private StringProperty alertText;
    private IntegerProperty alertType;

    public void init(ViewHandler viewHandler, CreateAccountViewModel createAccountViewModel)
    {
        alertText = new SimpleStringProperty();
        alertType = new SimpleIntegerProperty();
        handler = viewHandler;
        viewModel = createAccountViewModel;
        viewModel.getUsername().bindBidirectional(userNameTextField.textProperty());
        viewModel.getPassword().bindBidirectional(passwordTextField.textProperty());
        viewModel.getConfirmPassword().bindBidirectional(passwordConfTextField.textProperty());
        viewModel.getIsManagerProperty().bindBidirectional(isManager.selectedProperty());

        viewModel.getAlertTypeProperty().bindBidirectional(alertType);//whenever something changes it executes showalert
        viewModel.getAlertTextProperty().bindBidirectional(alertText);
        alertText.addListener((observableValue, number, type) -> showAlert());
    }

    private void showAlert()
    {
        System.out.println("here");
        if (alertText.get() == null || alertText.get().equals("")) return;

        Alert.AlertType t = (Alert.AlertType.values()[alertType.getValue()]) ;
        Alert alert = new Alert(t);
        alert.setContentText(alertText.get());
        alertText.setValue("");
        alert.showAndWait();
    }

    public void onCreateButton()
    {
        Stage stage = (Stage) userNameTextField.getScene().getWindow();
        viewModel.createAccount();
        if (viewModel.isRightAccountProperty().getValue())
        {
            handler.closeView(stage);
        }
    }
}
