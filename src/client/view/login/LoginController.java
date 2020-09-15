package client.view.login;

import client.view.ViewHandler;
import client.viewmodel.logIn.LoginViewModel;
import javafx.fxml.FXML;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.rmi.RemoteException;


public class LoginController {

    private ViewHandler viewHandler;
    private LoginViewModel loginViewModel;

    @FXML
    private TextField userNameTextField;

    @FXML
    private TextField passwordTextField;

    /**
     * initial method for controller, takes parameters and do binding
     *
     * @parameter view model of log in, view handler
     */
    public void init(LoginViewModel viewModel, ViewHandler viewHandler) {
        loginViewModel = viewModel;
        this.viewHandler = viewHandler;

        loginViewModel.getUserNameProperty().bindBidirectional(userNameTextField.textProperty());
        loginViewModel.getPasswordProperty().bindBidirectional(passwordTextField.textProperty());
    }

    public void buttonPressed(KeyEvent e) {
        if (e.getCode() == KeyCode.ENTER) {
            onLogInButton();
        }
    }

    public void onLogInButton() {
        Stage stage = (Stage) passwordTextField.getScene().getWindow();

        if (loginViewModel.checkIfExists()) {
            viewHandler.closeView(stage);
            if (loginViewModel.isManager()) {
                viewHandler.openView("sparePartsManager");
            } else
                viewHandler.openView("sparepartsVOS");
        } else {
            passwordTextField.textProperty().setValue("");
            userNameTextField.textProperty().setValue("");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Log in error");
            alert.setContentText("Invalid user name or password");
            alert.showAndWait();
        }
    }


}
