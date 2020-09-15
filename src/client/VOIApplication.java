package client;

import client.model.ModelFactory;
import client.view.ViewHandler;
import client.viewmodel.ViewModelFactory;
import javafx.application.Application;
import javafx.stage.Stage;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class VOIApplication extends javafx.application.Application {

    public void start(Stage stage) throws RemoteException, NotBoundException {
        ModelFactory mf= new ModelFactory();
        ViewModelFactory vm = new ViewModelFactory(mf);
        ViewHandler view = new ViewHandler(vm,stage);
        view.openView("logIn");
    }
}
