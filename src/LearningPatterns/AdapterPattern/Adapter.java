package LearningPatterns.AdapterPattern;

import java.util.Date;

public class Adapter implements Handler{
    private LegacyClass legacyClass = new LegacyClass();
    public DTO_New returnResult(){
        DTO_Old r = receive();
        String[] strings = r.date().split("-");
        Integer[] integers = {Integer.parseInt(strings[0]),Integer.parseInt(strings[1]),Integer.parseInt(strings[2])};
        System.out.println(integers[0]+" ,"+ integers[1] + ", " + integers[2]);
        DTO_New n = new DTO_New(new Date(integers[2], integers[1], integers[0]));
        System.out.println(n.date());
        return n;
    }

    private DTO_Old receive(){
        return legacyClass.execute();
    }
}
