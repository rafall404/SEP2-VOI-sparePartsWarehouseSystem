package client.viewmodel.createAccount;

import client.model.modelaccount.IAccountsModel;
import client.view.ViewHandler;
import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.rmi.RemoteException;
import java.sql.SQLException;

public class CreateAccountViewModel {
    private IAccountsModel iAccountsModel;

    private StringProperty username;
    private StringProperty password;
    private StringProperty confirmPassword;
    private StringProperty alertText;
    private BooleanProperty managerAccount;
    private BooleanProperty isRightAccount;
    private IntegerProperty alertType;

    public CreateAccountViewModel(IAccountsModel iAccountsModel) {
        this.iAccountsModel = iAccountsModel;
        username = new SimpleStringProperty();
        password = new SimpleStringProperty();
        alertText = new SimpleStringProperty();
        confirmPassword = new SimpleStringProperty();
        managerAccount = new SimpleBooleanProperty();
        isRightAccount = new SimpleBooleanProperty();
        isRightAccount.setValue(false);
        alertType = new SimpleIntegerProperty();
    }

    public StringProperty getUsername() {
        return username;
    }

    public StringProperty getPassword() {
        return password;
    }

    public StringProperty getConfirmPassword() {
        return confirmPassword;
    }

    public BooleanProperty isRightAccountProperty() {
        return isRightAccount;
    }

    public BooleanProperty getIsManagerProperty() {
        return managerAccount;
    }

    public boolean createAccount() {
        try {
            //check if empty
            if (password.getValue().equals("") || confirmPassword.getValue().equals("") || username.getValue().equals("")) {
                System.out.println("this is empty");
                alertType.set(Alert.AlertType.ERROR.ordinal());
                alertText.set("Please fill in all fields");
                managerAccount.setValue(false);
                System.out.println("Fields are empty");
                return false;

            }
            //checks if already in dbs
            else if (iAccountsModel.checkUsername(username.getValue())) {
                alertType.set(Alert.AlertType.WARNING.ordinal());
                alertText.set("This account already exists");
                managerAccount.setValue(false);
                System.out.println("Account already exists in DBS");
                return false;
            }

            //checks if passwords match
            else if (!password.getValue().equals(confirmPassword.getValue())) {
                alertType.set(Alert.AlertType.WARNING.ordinal());
                alertText.set("The password doesn't match");
                System.out.println("passwords doesnt match");
                return false;
            } else if (username.getValue().length() > 20 || password.getValue().length() > 20) {
                System.out.printf("fields are to long max is 20 chars");
                alertType.set(Alert.AlertType.ERROR.ordinal());
                alertText.set("The max length is 20 chars");
                password.setValue("");
                confirmPassword.setValue("");
                return false;

            } else {
                System.out.println(password.getValue() + " " + confirmPassword.getValue());
                System.out.println("Account has been created");
                iAccountsModel.createAccount(username.getValue(), password.getValue(), managerAccount.getValue());
                alertType.set(Alert.AlertType.INFORMATION.ordinal());
                alertText.set("New account has been created");

                isRightAccount.setValue(true);
                return true;
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    public IntegerProperty getAlertTypeProperty() {
        return alertType;
    }


    public StringProperty getAlertTextProperty() {
        return alertText;
    }
}
