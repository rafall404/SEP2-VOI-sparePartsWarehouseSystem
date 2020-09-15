package client.model.spareParts;

import client.model.ScooterModels.ISModel;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileWrite implements IFileWriter {
    private BufferedWriter order;
    private ArrayList<SparePart> arrayListSpareParts;

    public FileWrite(ArrayList<SparePart> arrayList) {
        this.arrayListSpareParts = arrayList;
        try {
            order = new BufferedWriter(new FileWriter("order.txt", true));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createOrder(ISModel model, String comments) {
        try {
            order.write("      SCOOTER MODEL : " + model.getModelName() + "\n");
            for (ISparePart i : arrayListSpareParts) {
                if (i.getAmountNeeded() != 0) {
                    order.write(" spare part : " + i.getName() + " ammount needed : " + i.getAmountNeeded() + "\n");
                }

            }
            if (comments != null) {
                order.write("COMMENTS : " + "\n" + comments + "\n");
            }
            System.out.println("done writing order");
            order.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
