package client.viewmodel.amountReceived;

import client.model.ScooterModels.SModel;
import client.model.spareParts.IMSparePart;
import client.model.spareParts.SparePart;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.rmi.RemoteException;

public class AmountReceivedViewModel {
    private StringProperty amount;
    private IMSparePart model;

    public AmountReceivedViewModel(IMSparePart model) {
        this.model = model;
        amount = new SimpleStringProperty();
    }

    public void receivedAmount(SparePart part, SModel model) {

        int amount = Integer.parseInt(this.amount.getValue());
        try {
            System.out.println(amount);
            this.model.receivedAmount(part, model, amount);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public StringProperty getAmount() {
        return this.amount;
    }
}
