package shared.remoteServer;

import client.model.modelaccount.Account;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;

public interface AccountsRServer extends Remote {
    boolean checkIfExists(String userName,String password)throws RemoteException;
    boolean checkUsername(String username) throws SQLException,RemoteException;
    void addAccount(Account acc, boolean isManager)throws RemoteException;
    boolean accountIsManager(String username, String password) throws RemoteException;
}
