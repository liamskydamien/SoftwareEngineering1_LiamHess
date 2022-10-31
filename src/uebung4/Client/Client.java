package uebung4.Client;

import uebung4.Datastructure.Container;
import uebung4.Exception.ClientException;
import uebung4.Exception.ContainerException;
import uebung4.Exception.PersistenceException;
import uebung4.Model.Entity.EmployeeConcrete;
import uebung4.Model.Expertise;
import uebung4.Model.Interface.Employee;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public String dump(){
        List<Employee> employeeList = container.getCurrentList();
        employeeList.sort(null);
        StringBuilder output = new StringBuilder("""
                ID |         Vorname |        Nachname |       Abteilung |                   Rolle |        Expertise
                ---+-----------------+-----------------+-----------------+-------------------------+-------------------------
                       """);
        for (Employee employee: employeeList){
            output.append(makeRow(employee)).append("\n");
        }
        return output.toString();
    }

    private String makeRow(Employee employee){
        StringBuilder output = new StringBuilder();
        output.append(" ".repeat(Math.max(0, 2 - String.valueOf(employee.getID()).length())));
        output.append(employee.getID()).append(" |");

        output.append(" ".repeat(Math.max(0, 16 - employee.getFirstname().length())));
        output.append(employee.getFirstname()).append(" |");

        output.append(" ".repeat(Math.max(0, 16 - employee.getLastname().length())));
        output.append(employee.getLastname()).append(" |");

        if(employee.getDepartment() != null) {
            output.append(" ".repeat(Math.max(0, 16 - employee.getDepartment().length())));
            output.append(employee.getDepartment()).append(" |");
        }
        else {
                output.append("     --------    |");
        }

        output.append(" ".repeat(Math.max(0, 24 - employee.getRole().length())));
        output.append(employee.getRole()).append(" |");

        output.append(employee.getExpertiseString());

        return output.toString();
    }

    public String search(String expertise){
        List<Employee> list = container.getCurrentList();
        list.sort(null);
        StringBuilder output = new StringBuilder();
        for (Employee employee: list){
            HashMap<String, Expertise> hashMap = employee.getExpertise();
            for (HashMap.Entry<String, Expertise> expertiseEntry: hashMap.entrySet()){
                if(expertiseEntry.getKey().equals(expertise)){
                    output.append(employee).append("\n");
                }
            }
        }
        return output.toString();
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

}
