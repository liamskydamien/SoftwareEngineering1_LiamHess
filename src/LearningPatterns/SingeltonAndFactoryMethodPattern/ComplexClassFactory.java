package LearningPatterns.SingeltonAndFactoryMethodPattern;

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


}
