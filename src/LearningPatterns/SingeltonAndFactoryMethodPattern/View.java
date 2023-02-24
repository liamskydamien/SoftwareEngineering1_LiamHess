package LearningPatterns.SingeltonAndFactoryMethodPattern;

import java.util.Date;

public class View {
    private ComplexClass[] complexClasses = new ComplexClass[5];
    private final ComplexClassFactory factory = ComplexClassFactory.getInstance();

    public void createClasses(){
        for (int i = 0; i < 5; i++) {
            complexClasses[i] = factory.createComplexClass("Liam", new Date("15-08-2001"));
        }
    }

    public void displayClasses(){
        for (ComplexClass c : complexClasses){
            System.out.println(c.toString());
        }
    }
}
