package LearningPatterns.ObserverPattern;

public class ConcreteSubscriber implements Subscriber{
    @Override
    public void update() {
        System.out.println("Updated");
    }
}
