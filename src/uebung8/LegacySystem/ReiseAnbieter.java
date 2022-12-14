package uebung8.LegacySystem;

import java.util.ArrayList;
import java.util.List;

public class ReiseAnbieter {

    private final List<String> dummyData = new ArrayList<>();
    private void fillDummyData(){
        dummyData.add("Hotel1_100");
        dummyData.add("Hotel2_200");
        dummyData.add("Hotel3_300");
        dummyData.add("Hotel4_400");
        dummyData.add("Hotel5_500");
        dummyData.add("Hotel6_600");
        dummyData.add("Hotel7_700");
        dummyData.add("Hotel8_800");
        dummyData.add("Hotel9_900");
        dummyData.add("Hotel10_1000");
    }

    public QueryResult execute(QueryObject queryObject) {
        return new QueryResult(dummyData);
    }
}
