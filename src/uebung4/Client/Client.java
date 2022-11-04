package uebung4.Client;

import uebung4.Datastructure.Container;
import uebung4.Exception.ClientException;
import uebung4.Exception.ContainerException;
import uebung4.Exception.PersistenceException;
import uebung4.Model.Entity.EmployeeConcrete;
import uebung4.Model.Expertise;
import uebung4.Model.Interface.Employee;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * @author Liam Hess
 * @version 1.0
 */

public class Client {
    private final Container container = Container.getInstance();

    public void enter(Integer id, String firstname, String lastname, String role, String department, HashMap<String, Expertise> expertises) throws ClientException {
        if(id == null){
            throw new ClientException(ClientException.ClientExceptionType.IdIsNotSet,"Fehler: ID ist ungültig. Bitte probiere es erneut.");
        }

        if (checkFormat(firstname)){
            throw new ClientException(ClientException.ClientExceptionType.WrongFormatFirstname, "Fehler: Vorname weist falsches Format auf. Bitte probiere es erneut.");
        }

        if(checkFormat(lastname)){
            throw new ClientException(ClientException.ClientExceptionType.WrongFormatLastname,"Fehler: Nachname weist falsches Format auf. Bitte probiere es erneut.");
        }
        if(department.equals("-")){
            department = null;
        }

        try {
            container.addEmployee(new EmployeeConcrete(id,firstname,lastname, role, department,expertises));
        }
        catch (ContainerException e){
            if(e.getExceptionType() == ContainerException.ContainerExceptionType.EntityAlreadyAdded){
                throw new ClientException(ClientException.ClientExceptionType.IdAlreadyDefined,"Fehler: ID ist bereits vorhanden. Bitte probiere es erneut.");
            }
            else {
                throw new ClientException(ClientException.ClientExceptionType.Failure, "Fehler: Laufzeit-Fehler. Bitte probiere es erneut.");
            }
        }
    }
    private boolean checkFormat(String toCheck){
        char[] chars = toCheck.toCharArray();
        StringBuilder sb = new StringBuilder();
        for(char c : chars){
            if(Character.isDigit(c)){
                return true;
            }
        }
        return false;
    }

    public void remove(Integer id) throws ClientException {
        try {
            container.deleteEmployee(id);
        } catch (ContainerException e) {
            throw new ClientException(ClientException.ClientExceptionType.DeletionNotPossibleWrongId, "Mitarbeiter konnte nicht gelöscht werden.");
        }
    }

    public String dump(String abteilung){
        List<Employee> employeeList;
        if(abteilung.equals("Alle")) {
            employeeList = container.getCurrentList();
            employeeList.sort(null);
        }
        else {
            employeeList = container.getCurrentList()
                                        .stream()
                                        .filter((e) -> e.getDepartment().equals(abteilung))
                                        .sorted(Comparator.comparingInt(Employee::getID))
                                        .toList();
        }
        StringBuilder output = new StringBuilder();
        output.append(String.format("%8s%16s%16s%32s%16s%16s", "ID | ", "Vorname | ", "Nachname | ", "Rolle | ", "Abteilung | ", "Expertise")).append("\n");
        output.append("------+---------------+---------------+-------------------------------+---------------+-------------------------------------------------------").append("\n");
        for (Employee employee: employeeList){
            output.append(makeRow(employee)).append("\n");
        }
        return output.toString();
    }

    private String makeRow(Employee employee){
        return String.format("%8s%16s%16s%32s%16s%16s",
                employee.getID()+ " | ",
                employee.getFirstname() + " | ",
                employee.getLastname() + " | ",
                employee.getRole() + " | ",
                employee.getDepartment() + " | ",
                employee.getExpertiseString());
    }

    public String search(String expertise){
        List<Employee> list = container.getCurrentList()
                .stream()
                .filter((e) -> e.getExpertise().containsKey(expertise))
                .sorted(Comparator.comparingInt(Employee::getID))
                .toList();
        StringBuilder stringBuilder = new StringBuilder();
        for (Employee employee: list){
            stringBuilder.append(employee).append("\n");
        }
        return stringBuilder.toString();
    }

    public void load(boolean isForce) throws ClientException {
        if(isForce){
            try {
                container.load();
            }
            catch (PersistenceException e){
                throw new ClientException(ClientException.ClientExceptionType.ErrorWhileLoading, "Fehler beim Laden der File.");
            }
        }
        else {
            try {
                container.loadAndMerge();
            }
            catch (PersistenceException e){
                throw new ClientException(ClientException.ClientExceptionType.ErrorWhileLoading, "Fehler beim Laden der File.");
            }
        }
    }

    public void store() throws ClientException {
        try {
            container.store();
        } catch (PersistenceException e) {
            throw new ClientException(ClientException.ClientExceptionType.ErrorWhileSaving,"Daten konnten nicht gespeichert werden.");
        }
    }

    /* Methoden zum Testen der Klasse */
    public int size(){
        return container.size();
    }

    public void wipeMemory(){
        container.wipeContainer();
    }

}
