package client.view.amountReceived;

import client.model.ScooterModels.SModel;
import client.view.ViewHandler;
import client.viewmodel.amountReceived.AmountReceivedViewModel;
import client.viewmodel.sparePartsList.SparePartViewModel;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AmountReceivedController {

    public TextField textField;

    private ViewHandler handler;
    private AmountReceivedViewModel amountReceivedViewModel;
    private SparePartViewModel sparePartViewModel;

    public void init(ViewHandler handler, AmountReceivedViewModel amountReceivedViewModel, SparePartViewModel sparePartViewModel)
    {
        this.handler= handler;
       this.amountReceivedViewModel = amountReceivedViewModel;
       this.sparePartViewModel= sparePartViewModel;
       textField.textProperty().bindBidirectional(amountReceivedViewModel.getAmount());
    }

    public void onSaveAction() {
        SModel model= new SModel(sparePartViewModel.getCurrentModelProperty().getValue());
        amountReceivedViewModel.receivedAmount(sparePartViewModel.getCurrentSparePart(),model);
        Stage stage= (Stage) textField.getScene().getWindow();
        handler.closeView(stage);
    }
}
