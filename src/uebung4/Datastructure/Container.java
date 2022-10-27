package uebung4.Datastructure;

import uebung4.Model.Interface.Employee;
import uebung4.Persistance.PersistenceStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Container {
    public final static Container instance = new Container();
    private List<Employee> storage = new ArrayList<>();

    private PersistenceStrategy<Employee> strategy;

    private Container() {
    }


    public static Container getInstance(){
        return instance;
    }
    public void addMember(Member member) throws ContainerException {
        if(member == null){
            throw new ContainerException("Das Member-Objekt ist null und kann daher nicht hizugefügt werden");
        }
        for (Member currentMember: storage) {
            if (Objects.equals(currentMember.getID(), member.getID())){
                throw new ContainerException("Das Member-Objekt mit der ID "+ member.getID() + " ist bereits vorhanden.");
            }
        }
        storage.add(member);
    }

    public String deleteMember(int memberID){
        int counter = 0;
        for (Member currentMember: storage) {
            if (Objects.equals(currentMember.getID(), memberID)){
                storage.remove(counter);
                return "Member-Objekt mit der ID " + memberID + " wurde erfolgreich gelöscht.";
            }
        }
        return "Member-Objekt mit der ID" + memberID + " existiert nicht im Container und konnte somit nicht gelöscht werden.";
    }

    //Antwort an die Frage "Warum ist eine Exception sinnvoller als eine Ausgabe wie hier."
    //Es ist sinnvoller beziehungsweise die Nachteile einer solchen Handhabung sind, dass man die Fehlermeldung nicht Tracebacken kann.
    //Zudem müsste man für jede Methode einen String ausgeben, ob die Methode funktioniert hat oder nicht.
    //Eine Exception muss man zudem nur einmal schreiben und kann diese überall wo sie benötigt wird in der Methode aufrufen. Eine solche Fehlermeldung durch einen String muss in jeder Methode neu implementiert werden.

    public int size(){
        return storage.size();
    }

    public void setStrategy(PersistenceStrategy<Member> strategy) {
        this.strategy = strategy;
    }

    public void store() throws PersistenceException {
        if(strategy == null){
            throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet, "No strategy is set.");
        }
        strategy.save(storage);
    }

    public void load() throws PersistenceException {
        if(strategy == null){
            throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet, "No strategy is set.");
        }
        storage = strategy.load();
    }

    public List<Member> getCurrentList(){
        return storage;
    }
}
