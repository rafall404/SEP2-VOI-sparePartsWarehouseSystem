package client.model.spareParts;

import client.model.ScooterModels.ISModel;
import client.model.ScooterModels.SModel;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class MSparePartDummyModel implements IMSparePart
{
    ArrayList<SparePart> spareParts = new ArrayList<>();

    public MSparePartDummyModel()
    {
        spareParts.add(new SparePart("part1"));
    }

    @Override
    public void removeSparepart(String name, ISModel model)
    {
        for (int i = 0; i < spareParts.size(); i++)
        {
            if (spareParts.get(i).getName().equals(name))
            {
                System.out.println(spareParts.get(i));
                spareParts.remove(i);

            }
        }
        System.out.println("spare part removed");
    }

    @Override
    public ArrayList<SparePart> getAllSpareparts(ISModel model)
    {
        return spareParts;
    }

    @Override
    public void addSparepart(String name, ISModel model)
    {
        spareParts.add(new SparePart(name));
    }

    @Override
    public void editSparePart(ISparePart part, ISModel model, int quantity, int amountNeeded)
    {

    }

    @Override
    public void incrementSparePartQuantity(ISparePart part, String scooterModel)
    {

        for (SparePart i : spareParts)
        {
            if (i.getName().equals(part.getName()))
            {

                i.setQuantity(i.getQuantity() + 1);
                System.out.println(i.getQuantity());
            }
        }
        System.out.println("spare part incr");
    }

    @Override
    public void decrementSparePartQuantity(ISparePart part, String scooterModel)
    {
        for (SparePart i : spareParts)
        {
            if (i.getName().equals(part.getName()))
            {
                System.out.println(i.getQuantity());
                i.setQuantity(i.getQuantity() - 1);
                System.out.println(i.getQuantity());
            }
        }
        System.out.println("spare part decr");
    }

    @Override
    public void placeOrder(ISModel model, String comments)
    {

    }

    @Override
    public void receivedAmount(SparePart part, SModel model, int amount)
    {

    }

    @Override
    public void addListener(String names, PropertyChangeListener listener)
    {

    }

    @Override
    public void removeListener(String names, PropertyChangeListener listener)
    {

    }

}
