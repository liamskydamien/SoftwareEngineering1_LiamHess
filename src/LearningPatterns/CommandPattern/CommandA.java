package LearningPatterns.CommandPattern;

public class CommandA implements Command{
    public void execute(){
        System.out.println("Command A executed");
    }

    public void undo(){
        System.out.println("Command A undone");
    }
}
