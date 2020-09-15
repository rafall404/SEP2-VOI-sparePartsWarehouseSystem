package client.model.spareParts;

import client.model.ScooterModels.ISModel;

public interface IFileWriter
{
    void createOrder(ISModel model, String comments);
}
