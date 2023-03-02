package LearningPatterns.StrategyPattern;

public class User {
    private Strategy strategy = null;

    public void setStrategy(Strategy strategy){
        this.strategy = strategy;
    }

    public void doSomething(){
        System.out.println("Do Something" + strategy);
    }

}
