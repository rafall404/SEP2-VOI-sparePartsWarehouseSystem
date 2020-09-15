package client.model.spareParts;

import java.io.Serializable;

public class SparePart implements ISparePart, Serializable {
    private String name;
    private int quantity;
    private int amountNeeded;

    public SparePart(String name) {
        this.name = name;
        this.quantity = 0;
        this.amountNeeded = 0;
    }

    public SparePart(String name, int quantity, int amountNeeded) {
        this.name = name;
        this.quantity = quantity;
        this.amountNeeded = amountNeeded;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getAmountNeeded() {
        return amountNeeded;
    }

    @Override
    public void receivedAmount(int amount) {
        quantity = quantity + amount;
        amountNeeded = amountNeeded - amount;
        if (amountNeeded < 0) {
            amountNeeded = 0;
        }
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setAmountNeeded(int amountNeeded) {
        this.amountNeeded = amountNeeded;
    }
}
