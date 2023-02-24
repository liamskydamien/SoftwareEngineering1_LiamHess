package LearningPatterns.SingeltonAndFactoryMethodPattern;

import java.util.Date;

public class ComplexClass {
    private final String name;
    private final Date birthday;

    public ComplexClass(String name, Date birthday) {
        this.name = name;
        this.birthday = birthday;
    }

    public String toString(){
        return "I am " + name + " and I was born on the " + birthday;
    }

}
