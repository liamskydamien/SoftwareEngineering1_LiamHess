package LearningPatterns.SingeltonAndFactoryMethodPattern;

import java.util.Date;

public class ComplexClassFactory {

    private static ComplexClassFactory instance = null;

    public static synchronized ComplexClassFactory getInstance(){
        if (instance == null){
            return new ComplexClassFactory();
        }
        return instance;
    }

    private ComplexClassFactory() {
        instance = this;
    }

    public ComplexClass createComplexClass(String name, Date birthday){
        return new ComplexClass(name, birthday);
    }


}
