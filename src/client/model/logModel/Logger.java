package client.model.logModel;

import client.RPCLImpl;
import client.model.ScooterModels.SModel;
import client.model.spareParts.ISparePart;
import shared.remoteServer.LogServer;

import java.beans.PropertyChangeListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Logger implements ILogger {
    private static Logger log;
    private static Lock lock = new ReentrantLock();
    private String username;
    private String time;
    private LogServer server;

    private Logger(){
        Registry reg = null;
        try {
            reg = LocateRegistry.getRegistry("Localhost",1099);
            server = (LogServer) reg.lookup("server");
            System.out.println("Connected to Server");
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            time = dtf.format(now);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }

    }

    public static Logger getInstance()
    {
        if(log==null)
        {
            synchronized (lock)
            {
                if(log==null)
                {
                    log= new Logger();
                }
            }
        }
        return log;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getUsername()
    {
        return username;
    }

    public ArrayList<String> getLogList(ISparePart part, SModel model)
    {
        try {
            return  server.getLogList(part,model);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void log(String event)
    {
        String log=  "Time :" + time +", user: " + username + " , " + event;
        try {
            server.logToDatabase(log);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void addListener(String names, PropertyChangeListener listener) throws RemoteException {
        server.wrappLogListener(names, new RPCLImpl(listener));

    }

    @Override
    public void removeListener(String names, PropertyChangeListener listener) throws RemoteException {

    }
}
