import client.model.ScooterModels.SModel;
import client.model.spareParts.IMSparePart;
import client.model.spareParts.MSparePart;
import client.model.spareParts.MSparePartDummyModel;
import client.model.spareParts.SparePart;
import client.viewmodel.sparePartsList.SparePartViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;

import static org.junit.jupiter.api.Assertions.*;

class SparePartViewModelTest
{
    IMSparePart imSparePart;
    SparePartViewModel sparePartViewModel;

    @BeforeEach
    void setUp()
    {
        imSparePart = new MSparePartDummyModel();
        sparePartViewModel = new SparePartViewModel(imSparePart);

    }


    @Test
    void incrementPart()
    {
        sparePartViewModel.incrementPart(new SparePart("part1"), "bigScooter");
        sparePartViewModel.incrementPart(new SparePart("part1"), "bigScooter");
        try
        {
            assertEquals(2, imSparePart.getAllSpareparts(new SModel("bigScooter")).get(0).getQuantity());
        } catch (RemoteException e)
        {
            e.printStackTrace();
        }
    }

    @Test
    void decrementPart()
    {
        sparePartViewModel.decrementPart(new SparePart("part1"), "bigScooter");
        try
        {
            sparePartViewModel.incrementPart(new SparePart("part1"), "bigScooter");
            sparePartViewModel.incrementPart(new SparePart("part1"), "bigScooter");
            assertEquals(1, imSparePart.getAllSpareparts(new SModel("bigScooter")).get(0).getQuantity());
        } catch (RemoteException e)
        {
            e.printStackTrace();
        }
    }

    @Test
    void removeSparePart()
    {


        try
        {
            System.out.println(imSparePart.getAllSpareparts(new SModel("bigScooter")).size());
            sparePartViewModel.removeSparePart("part1", new SModel("bigScooter"));
            System.out.println(imSparePart.getAllSpareparts(new SModel("bigScooter")).size());
            assertEquals(0, imSparePart.getAllSpareparts(new SModel("bigScooter")).size());
        } catch (RemoteException e)
        {
            e.printStackTrace();
        }
    }


}