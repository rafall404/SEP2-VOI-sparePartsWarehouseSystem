package client.model;

import client.model.ScooterModels.IMSModel;
import client.model.logModel.ILogger;
import client.model.logModel.Logger;
import client.model.spareParts.IMSparePart;
import client.model.ScooterModels.MSModel;
import client.model.spareParts.MSparePart;
import client.model.modelaccount.AccountModel;
import client.model.modelaccount.IAccountsModel;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ModelFactory {
    private IAccountsModel accountsModel;
    private IMSModel modelOfScooter;
    private IMSparePart sparePartModel;
    private ILogger loggerModel;

    public IAccountsModel getAccountsModel() throws RemoteException, NotBoundException {
        if(accountsModel ==null)
        {
            accountsModel = new AccountModel();
        }
        return accountsModel;
    }

    public IMSModel getIMSModelModel() throws RemoteException, NotBoundException {
        if(modelOfScooter ==null)
        {
            modelOfScooter = new MSModel();
        }
        return modelOfScooter;
    }

    public IMSparePart getSparePartModel() throws RemoteException, NotBoundException {
       if(sparePartModel==null) {
            sparePartModel= new MSparePart();
       }
        return sparePartModel;
    }

    public ILogger getLogModel() {
        if(loggerModel==null)
        {
            loggerModel= Logger.getInstance();
        }
        return loggerModel;
    }
}
