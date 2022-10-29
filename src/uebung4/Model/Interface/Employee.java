package uebung4.Model.Interface;

import uebung4.Model.Expertise;

import java.util.Comparator;
import java.util.HashMap;

public interface Employee extends Comparable<Employee> {
    int getID();
    String getFirstname();
    String getLastname();
    String getRole();
    String getDepartment();
    HashMap<String, Expertise> getExpertise();
    String getExpertiseString();
}
