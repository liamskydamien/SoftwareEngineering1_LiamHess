package LearningPatterns.CommandPattern;

public class CommandB implements Command{
    @Override
    public void execute() {
        System.out.println("Command B executed");
    }

    @Override
    public void undo() {
        System.out.println("Command B undone");
    }
}
