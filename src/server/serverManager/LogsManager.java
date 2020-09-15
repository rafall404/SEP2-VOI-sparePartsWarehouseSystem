package server.serverManager;

import shared.PropertyChangeSubject;
import client.model.ScooterModels.SModel;
import client.model.spareParts.ISparePart;
import client.model.spareParts.SparePart;
import server.jdbc.LogJDBC;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class LogsManager implements PropertyChangeSubject {
    private LogJDBC logJDBC;
    private PropertyChangeSupport support = new PropertyChangeSupport(this);


    public LogsManager()
    {
        logJDBC= new LogJDBC();
    }

    public  ArrayList<String> getLogList(ISparePart part, SModel model) {
      return   logJDBC.getDatabaseLogs((SparePart) part,model);
    }


    public void logToDatabase(String log) {
        logJDBC.logToDatabase(log);
        support.firePropertyChange("change", null, log);
    }

    public void addListener(String names, PropertyChangeListener listener) {
        support.addPropertyChangeListener(names, listener);
    }

    @Override
    public void removeListener(String names, PropertyChangeListener listener)
    {

    }
}
