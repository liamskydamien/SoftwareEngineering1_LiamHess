package uebung4.Model.Interface;

import uebung4.Model.Expertise;

import java.util.HashMap;

public interface Employee {
    public Long getID();
    public String getFirstname();
    public String getLastname();
    public String getRole();
    public String getDepartment();
    public HashMap<String, Expertise> getExpertise();
    public String getExpertiseString();
}
