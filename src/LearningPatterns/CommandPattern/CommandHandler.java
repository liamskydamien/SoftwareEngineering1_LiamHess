package LearningPatterns.CommandPattern;

import java.util.HashMap;
import java.util.Stack;

public class CommandHandler {
    HashMap<String, Command> commands = new HashMap<>();
    Stack<Command> history = new Stack<>();
    public CommandHandler(){
        commands.put("A", new CommandA());
        commands.put("B", new CommandB());
    }

    public void useCommand(String input){
        Command command = commands.get(input);
        history.add(command);
        command.execute();
    }

    public void undoCommand(){
        Command command = history.pop();
        command.undo();
    }
}
