package uebung4.Persistance;

import uebung4.Exception.PersistenceException;

import java.io.*;
import java.util.List;

public class PersistenceStrategyStream<E> implements PersistenceStrategy<E> {

    // URL of file, in which the objects are stored
    private String location = "src/uebung4/employees.ser";
    private ObjectInputStream ois;
    private FileInputStream fis;
    private ObjectOutputStream oos;
    private FileOutputStream fos;

    //Boolean um zu bestimmen welcher "Kanal" geöffnet oder geschlossen werden soll
    private boolean isInput = true;

    // Backdoor method used only for testing purposes, if the location should be changed in a Unit-Test
    // Example: Location is a directory (Streams do not like directories, so try this out ;-)!
    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public void openConnection() throws PersistenceException {
        try{
            if (isInput) {
                fis = new FileInputStream(location);
                ois = new ObjectInputStream(fis);
            } else {
                fos = new FileOutputStream(location);
                oos = new ObjectOutputStream(fos);
            }
        }
        catch (FileNotFoundException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.ImplementationNotAvailable, "No File found.");
        }
        catch (IOException e){
            e.printStackTrace();
            throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable, "Connection not possible.");
        }
    }

    @Override
    public void closeConnection() throws PersistenceException {
        try {
            if(isInput) {
                ois.close();
                fis.close();
            }
            else {
                oos.close();
                fos.close();
            }
        }
        catch (FileNotFoundException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.NoFileFound, "No File found.");
        }
        catch (IOException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable, "No Connection open.");
        }
    }

    @Override
    public void save(List<E> members) throws PersistenceException  {
        isInput = false;
        //Auslagerung in openConnection
        openConnection();


        try {
            oos.writeObject(members);
        }
        catch (IOException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.RuntimeError, "Something went wrong.");
        }
        //Auslagerung in closeConnection
        closeConnection();


    }

    @Override
    @SuppressWarnings("unchecked")
    public List<E> load() throws PersistenceException  {
        isInput = true;
        List<E> newList = null;

        //Auslagerung in openConnection
        openConnection();


        try {
            Object obj = ois.readObject();
            if(obj instanceof List<?>){
                newList = (List<E>) obj;
            }
        }
        catch (IOException | ClassNotFoundException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.RuntimeError, "Something went wrong.");
        }

        //Auslagerung in closeConnection
        closeConnection();

        return newList;
    }
}
