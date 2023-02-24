package LearningPatterns.SingeltonAndFactoryMethodPattern;

public class Main {
    public static void main(String[] args) {
        View view = new View();
        view.createClasses();
        view.displayClasses();
    }
}
