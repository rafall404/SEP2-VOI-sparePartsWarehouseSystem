package client.model.spareParts;

import client.RPCLImpl;
import client.model.ScooterModels.ISModel;

import client.model.logModel.Logger;
import client.model.ScooterModels.SModel;
import shared.remoteServer.SparePartsServer;

import java.beans.PropertyChangeListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

public class MSparePart implements IMSparePart {
    private ISparePart sparePart;
    private SparePartsServer server;

    private IFileWriter fileWrite;
    private Logger log = Logger.getInstance();


    public MSparePart() throws RemoteException, NotBoundException {
        Registry reg = LocateRegistry.getRegistry("localhost", 1099);
        server = (SparePartsServer) reg.lookup("server");
        System.out.println("Connected to Server");
    }

    public void addSparepart(String name, ISModel model) throws RemoteException {
        sparePart = new SparePart(name);
        server.addSparePart(sparePart, model);
    }

    @Override
    public void editSparePart(ISparePart part, ISModel model, int quantity, int amountNeeded) throws RemoteException {
        server.editSparePart(part, model, quantity, amountNeeded);
        log.log("edited sparepart" + part.getName() + "in model:" + model.getModelName() + "quantity =" + quantity + "amount needed =" + amountNeeded);
    }

    @Override
    public void incrementSparePartQuantity(ISparePart part, String scooterModel) {
        try {
            if (part != null) {
                server.incrementSparePartQuantity(part, scooterModel);
                log.log("incremented sparepart " + part.getName() + "in model:" + scooterModel);
            } else {
                System.out.println("nothing is selected");
            }


        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void decrementSparePartQuantity(ISparePart part, String scooterModel) {
        try {
            if (part != null) {
                server.decrementSparePartQuantity(part, scooterModel);
                log.log(" decremented sparepart " + part.getName() + " in model:" + scooterModel);
            } else {
                System.out.println("nothing is selected");
            }


        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void placeOrder(ISModel model, String comments) {
        try {
            fileWrite = new FileWrite(getAllSpareparts(model));
            fileWrite.createOrder(model, comments);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void receivedAmount(SparePart part, SModel model, int amount) throws RemoteException {
        part.receivedAmount(amount);
        server.editSparePart(part, model, part.getQuantity(), part.getAmountNeeded());
    }

    public void removeSparepart(String name, ISModel model) throws RemoteException {
        sparePart = new SparePart(name);
        server.removeSparePart(sparePart, model);

    }

    public ArrayList<SparePart> getAllSpareparts(ISModel model) throws RemoteException {
        return server.getAllSpareParts(model);
    }


    @Override
    public void addListener(String names, PropertyChangeListener listener) {
        try {
            server.wrappListener(names, new RPCLImpl(listener));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeListener(String names, PropertyChangeListener listener) {

    }
}
