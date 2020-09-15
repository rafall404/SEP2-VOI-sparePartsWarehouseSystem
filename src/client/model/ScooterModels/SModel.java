package client.model.ScooterModels;

import java.io.Serializable;

public class SModel implements ISModel, Serializable {
    private String modelName;

    public SModel(String modelName)
    {
        this.modelName=modelName;
    }

    public String getModelName() {
        return modelName;
    }
}
