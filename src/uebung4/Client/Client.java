package uebung4.Client;

import uebung4.Datastructure.Container;
import uebung4.Exception.ClientException;
import uebung4.Exception.ContainerException;
import uebung4.Model.Entity.EmployeeConcrete;
import uebung4.Model.Expertise;
import uebung4.Model.Interface.Employee;

import java.util.HashMap;
import java.util.List;

public class Client {
    private final Container container = Container.getInstance();

    public void enter(Long id, String firstname, String lastname, String role, String department, HashMap<String, Expertise> expertises) throws ClientException {
        if (!checkFormat(firstname)){
            throw new ClientException(ClientException.ClientExceptionType.WrongFormatFirstname, "Fehler: Vorname weist falsches Format auf. Bitte probiere es erneut.");
        }
        if(!checkFormat(lastname)){
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
            else if(e.getExceptionType() == ContainerException.ContainerExceptionType.EntityNotValid){
                throw new ClientException(ClientException.ClientExceptionType.IdIsNotSet,"Fehler: ID ist ungültig. Bitte probiere es erneut.");
            }
            else {
                throw new ClientException(ClientException.ClientExceptionType.Failure, "Fehler: Laufzeit-Fehler. Bitte probiere es erneut.");
            }
        }
    }
    private boolean checkFormat(String toCheck){
        try {
            Integer.parseInt(toCheck);
            return true;
        }
        catch (NumberFormatException e){
            return false;
        }
    }

    public String dump(){
        List<Employee> employeeList = container.getCurrentList();
        String output = "  ID |        Vorname |       Nachname |       Abteilung |           Rolle |       Expertise" + "\n";
        for (Employee employee: employeeList){
            output = output + makeRow(employee) + "\n";
        }
        return output;
    }

    private String makeRow(Employee employee){
        String output = "";
        for(int i = 0; i < 4 - employee.getID().toString().length(); i++){
            output = output + " ";
        }
        output = output + employee.getID() + "  ";

        for(int j = 0; j < 16 - employee.getFirstname().length(); j++){
            output = output + " ";
        }
        output = output + employee.getFirstname() + "  ";

        for(int u = 0; u < 16 - employee.getLastname().length(); u++){
            output = output + " ";
        }
        output = output + employee.getLastname() + "  ";

        if(employee.getDepartment() != null) {
            for (int o = 0; o < 16 - employee.getDepartment().length(); o++) {
                output = output + " ";
            }
            output = output + employee.getDepartment() + "  ";
        }
        else {
            for (int o = 0; o < 16 - employee.getDepartment().length(); o++) {
                output = output + "-";
            }
        }

        for(int p = 0; p < 16 - employee.getRole().length(); p++){
            output = output + " ";
        }
        output = output + employee.getRole() + "  ";

        output = output + employee.getExpertiseString();

        return output;
    }

    /* Methoden zum Testen der Klasse */
    public int size(){
        return container.size();
    }

}
