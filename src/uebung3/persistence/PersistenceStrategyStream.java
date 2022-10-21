package uebung3.persistence;
import java.io.*;
import java.util.List;

public class PersistenceStrategyStream<E> implements PersistenceStrategy<E> {

    // URL of file, in which the objects are stored
    private String location = "objects.ser";
    private ObjectInputStream ois;
    private FileInputStream fis;
    private ObjectOutputStream oos;
    private FileOutputStream fos;

    // Backdoor method used only for testing purposes, if the location should be changed in a Unit-Test
    // Example: Location is a directory (Streams do not like directories, so try this out ;-)!
    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    /**
     * Method for opening the connection to a stream (here: Input- and Output-Stream)
     * In case of having problems while opening the streams, leave the code in methods load
     * and save
     */
    public void openConnection() throws PersistenceException {
        try{
            fis = new FileInputStream(location);
            ois = new ObjectInputStream(fis);
            fos = new FileOutputStream(location);
            oos = new ObjectOutputStream(fos);
        }
        catch (IOException e){
            e.printStackTrace();
            throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable, "Connection not possible.");
        }
    }

    @Override
    /**
     * Method for closing the connections to a stream
     */
    public void closeConnection() throws PersistenceException {
        try {
            ois.close();
            fis.close();
            oos.close();
            fos.close();
        } catch (IOException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable, "No Connection open.");
        }
    }

    @Override
    /**
     * Method for saving a list of Member-objects to a disk (HDD)
     */
    public void save(List<E> members) throws PersistenceException  {
        FileOutputStream fileOutputStream;
        ObjectOutputStream objectOutputStream;
        try {
        fileOutputStream = new FileOutputStream(location);
        objectOutputStream = new ObjectOutputStream(fileOutputStream);
    }
    catch (FileNotFoundException e) {
        throw new PersistenceException(PersistenceException.ExceptionType.ImplementationNotAvailable, "No File found.");
    }
    catch (IOException e) {
        throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable, "Fail to connect");
    }

        try {
            objectOutputStream.writeObject(members);
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new PersistenceException(PersistenceException.ExceptionType.ImplementationNotAvailable, "No File to Read found.");
        }

        try {
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        /* openConnection();
        try {
            oos.writeObject(members);
        } catch (IOException e) {
            e.printStackTrace();
            throw new PersistenceException(PersistenceException.ExceptionType.ImplementationNotAvailable, "No File found.");
        }
        closeConnection();*/
    }

    @Override
    @SuppressWarnings("unchecked")
    /**
     * Method for loading a list of Member-objects from a disk (HDD)
     * Some coding examples come for free :-)
     * Take also a look at the import statements above ;-!
     */
    public List<E> load() throws PersistenceException  {
        List<E> newList = null;
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        //openConnection();

        try {
            fileInputStream = new FileInputStream(location);
            objectInputStream = new ObjectInputStream(fileInputStream);
        }
        catch (FileNotFoundException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.ImplementationNotAvailable, "No File found.");
        }
        catch (IOException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable, "Fail to connect");
        }

        try {
            Object obj = objectInputStream.readObject();
            if(obj instanceof List<?>){
                newList = (List<E>) obj;
            }
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new PersistenceException(PersistenceException.ExceptionType.ImplementationNotAvailable, "No File to Read found.");
        }

        try {
            objectInputStream.close();
            fileInputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return newList;
    }
}
