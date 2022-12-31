package uebung9;

public abstract class DocumentImplementation implements Document{
    private int id;

    public DocumentImplementation(int id){
        this.id = id;
    }

    public int get(){
        return id;
    }

    public void set(int newID){
        id = newID;
    }
}
