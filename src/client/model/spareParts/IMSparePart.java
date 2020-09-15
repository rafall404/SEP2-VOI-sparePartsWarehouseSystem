package client.model.spareParts;

import shared.PropertyChangeSubject;
import client.model.ScooterModels.ISModel;
import client.model.ScooterModels.SModel;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IMSparePart extends Remote, PropertyChangeSubject
{
    void removeSparepart(String name, ISModel model)throws RemoteException;
    ArrayList<SparePart> getAllSpareparts(ISModel model)throws RemoteException;
    void addSparepart(String name,ISModel model)throws RemoteException;
    void editSparePart(ISparePart part, ISModel model, int quantity, int amountNeeded) throws RemoteException;
    void incrementSparePartQuantity(ISparePart part,String scooterModel);
    void decrementSparePartQuantity(ISparePart part,String scooterModel);
    void placeOrder(ISModel model,String comments);
    void receivedAmount(SparePart part, SModel model, int amount) throws RemoteException;
}
