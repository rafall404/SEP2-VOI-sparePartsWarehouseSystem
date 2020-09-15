package client.model.spareParts;

public interface ISparePart {
    void setQuantity(int quantity);
    int getQuantity();
    String getName();
    int getAmountNeeded();
    void receivedAmount(int amount);
}
