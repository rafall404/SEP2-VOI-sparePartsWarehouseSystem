package client.viewmodel.logIn;

import client.model.modelaccount.IAccountsModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Alert;

import java.rmi.RemoteException;

public class LoginViewModel {

    private StringProperty userName;
    private StringProperty password;

    private IAccountsModel accountsModel;

    public LoginViewModel(IAccountsModel accountsModel) {
        this.accountsModel = accountsModel;

        userName = new SimpleStringProperty();
        password = new SimpleStringProperty();
    }

    public StringProperty getUserNameProperty() {
        return userName;
    }

    public StringProperty getPasswordProperty() {
        return password;
    }

    public boolean isManager() {
        try {
            return accountsModel.accountIsManager(userName.getValue(), password.getValue());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Validate input of username and password
     * if both are correct - calling another view trough view handler
     * else warning message
     */
    public boolean checkIfExists() {
        try {
            if (accountsModel.accountExists(userName.getValue(), password.getValue())) {
                System.out.println(accountsModel.accountExists(userName.getValue(), password.getValue()));
                return true;
            } else {
                return false;
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }


}
