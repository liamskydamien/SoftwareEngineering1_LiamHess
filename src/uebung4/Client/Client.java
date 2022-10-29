package uebung4.Client;

import uebung4.Datastructure.Container;
import uebung4.Exception.ClientException;
import uebung4.Exception.ContainerException;
import uebung4.Model.Entity.EmployeeConcrete;
import uebung4.Model.Expertise;

import java.util.HashMap;

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


}
