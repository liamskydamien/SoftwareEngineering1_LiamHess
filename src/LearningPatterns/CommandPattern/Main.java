package LearningPatterns.CommandPattern;

public class Main {
    public static void main(String[] args) {
        CommandHandler commandHandler = new CommandHandler();
        String[] strings = {"A","A","B","A","C","C","B","A","C","B"};
        for (String s : strings){
            if(s.equals("C")){
                commandHandler.undoCommand();
            }
            else {
                commandHandler.useCommand(s);
            }
        }
    }
}
