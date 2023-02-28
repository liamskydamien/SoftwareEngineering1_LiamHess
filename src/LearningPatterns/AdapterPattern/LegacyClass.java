package LearningPatterns.AdapterPattern;

import java.util.ArrayList;
import java.util.List;

public class LegacyClass {

    List<DTO_Old> list = new ArrayList<>();

    private void fillData(){
        list.add(new DTO_Old("15-08-2001"));
        list.add(new DTO_Old("09-02-2001"));
    }

    public DTO_Old execute(){
        fillData();
        System.out.println(list.get(0).date());
        return list.get(0);
    }
}
