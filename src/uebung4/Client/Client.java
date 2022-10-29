package uebung4.Client;

import uebung4.Datastructure.Container;
import uebung4.Exception.ClientException;
import uebung4.Exception.ContainerException;
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

    public String dump(){
        List<Employee> employeeList = container.getCurrentList();
        employeeList.sort(null);
        String output = "  ID |         Vorname |        Nachname |       Abteilung |                   Rolle |        Expertise" + "\n";
        for (Employee employee: employeeList){
            output = output + makeRow(employee) + "\n";
        }
        return output;
    }

    private String makeRow(Employee employee){
        String output = "";
        for(int i = 0; i < 4 - String.valueOf(employee.getID()).length(); i++){
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
                output = output + "     --------     ";
        }

        for(int p = 0; p < 24 - employee.getRole().length(); p++){
            output = output + " ";
        }
        output = output + employee.getRole() + "  ";

        output = output + employee.getExpertiseString();

        return output;
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

    /* Methoden zum Testen der Klasse */
    public int size(){
        return container.size();
    }

}
