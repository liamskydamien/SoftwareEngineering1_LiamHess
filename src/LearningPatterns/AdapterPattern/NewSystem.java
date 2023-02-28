package LearningPatterns.AdapterPattern;

public class NewSystem {
    static Handler handler = new Adapter();
    public static void main(String[] args){
        System.out.println(handler.returnResult().date());
    }
}
