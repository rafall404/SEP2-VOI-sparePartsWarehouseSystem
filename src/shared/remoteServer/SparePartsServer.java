package shared.remoteServer;

import shared.RemotePropertyChangeListener;
import client.model.ScooterModels.ISModel;
import client.model.spareParts.ISparePart;
import client.model.spareParts.SparePart;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface SparePartsServer extends Remote {

    void addSparePart(ISparePart sparePart,ISModel model)throws RemoteException;
    void removeSparePart(ISparePart sparePart,ISModel model)throws RemoteException;
    ArrayList<SparePart> getAllSpareParts(ISModel model)throws RemoteException;
    void editSparePart(ISparePart part, ISModel model, int quantity, int amountNeeded) throws RemoteException;
    void wrappListener(String names, RemotePropertyChangeListener listener) throws RemoteException;
    void incrementSparePartQuantity(ISparePart part, String scooterModel)throws RemoteException;
    void decrementSparePartQuantity(ISparePart part,String scooterModel)throws RemoteException;
}
