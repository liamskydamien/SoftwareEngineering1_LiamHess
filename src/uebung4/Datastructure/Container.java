package uebung4.Datastructure;

import uebung4.Exception.ClientException;
import uebung4.Exception.ContainerException;
import uebung4.Exception.PersistenceException;
import uebung4.Model.Interface.Employee;
import uebung4.Persistance.PersistenceStrategy;
import uebung4.Persistance.PersistenceStrategyStream;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Container {
    private static Container instance;
    private List<Employee> storage = new ArrayList<>();

    private PersistenceStrategy<Employee> strategy;

    private Container() {

    }

    public synchronized static Container getInstance(){
        if(instance == null){
            instance = new Container();
        }
        return instance;
    }
    public void addEmployee(Employee employee) throws ContainerException {
        if(employee == null){
            throw new ContainerException(ContainerException.ContainerExceptionType.EntityNotValid,"Das Employee-Objekt ist null und kann daher nicht hizugefügt werden");
        }
        for (Employee currentEmployee: storage) {
            if (Objects.equals(currentEmployee.getID(), employee.getID())){
                throw new ContainerException(ContainerException.ContainerExceptionType.EntityAlreadyAdded, "Das Employee-Objekt mit der ID "+ employee.getID() + " ist bereits vorhanden.");
            }
        }
        storage.add(employee);
    }

    public void deleteEmployee(int employeeId) throws ContainerException {
        int counter = 0;
        for (Employee currentEmpoyee: storage) {
            if (Objects.equals(currentEmpoyee.getID(), employeeId)){
                storage.remove(counter);
                return;
            }
        }
        throw new ContainerException(ContainerException.ContainerExceptionType.EntityNotFound, "Das Employee-Objekt mit der ID " + employeeId + " konnte nicht gefunden werden.");
    }

    public int size(){
        return storage.size();
    }

    public void setStrategy(PersistenceStrategy<Employee> strategy) {
        this.strategy = strategy;
    }

    public void store() throws PersistenceException{
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

    public void loadAndMerge() throws PersistenceException {
        if(strategy == null){
            throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet, "No strategy is set.");
        }
        List<Employee> loadedList = strategy.load();
        int i = 0;
        while (!loadedList.isEmpty()){
            boolean isInside = false;
            Employee employee = loadedList.remove(i);
            for (Employee current: storage){
                if (employee.compareTo(current) == 0) {
                    isInside = true;
                    break;
                }
            }
            if(!isInside){
                storage.add(employee);
            }
        }
    }

    public List<Employee> getCurrentList(){
        return storage;
    }
}
