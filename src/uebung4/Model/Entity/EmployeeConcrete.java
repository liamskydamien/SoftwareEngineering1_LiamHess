package uebung4.Model.Entity;

import uebung4.Model.Expertise;
import uebung4.Model.Interface.Employee;

import java.util.HashMap;
import java.util.Map;

public class EmployeeConcrete implements Employee {
    private Long id;
    private String firstname;
    private String lastname;
    private String role;
    private String department;
    private HashMap<String , Expertise> expertises;

    public EmployeeConcrete(Long id,
                            String firstname,
                            String lastname,
                            String role,
                            String department,
                            HashMap<String, Expertise> expertises) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.role = role;
        this.department = department;
        this.expertises = expertises;
    }

    @Override
    public Long getID() {
        return id;
    }

    @Override
    public String getFirstname() {
        return firstname;
    }

    @Override
    public String getLastname() {
        return lastname;
    }

    @Override
    public String getRole() {
        return role;
    }

    @Override
    public String getDepartment() {
        return department;
    }

    @Override
    public HashMap<String, Expertise> getExpertise() {
        return expertises;
    }

    @Override
    public String getExpertiseString(){
        String output = "[";
        for (HashMap.Entry<String, Expertise> expertiseEntry: expertises.entrySet()) {
            output = output + "(" + expertiseEntry.getKey() + " : ";
            output = output + expertiseEntry.getValue().toString() + "),";
        }
        return output.substring(0,output.length()-2) + "]";
    }
}
