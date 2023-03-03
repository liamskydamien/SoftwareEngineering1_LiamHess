package LearningPatterns.ReportPublisher;

import java.util.ArrayList;
import java.util.List;

public class CompanyConsumer implements Consumer{
    List<String> topics = new ArrayList<>();

    public void addTopic(String newTopic){
        topics.add(newTopic);
    }

    @Override
    public void update(ReportDTO reportDTO) {
        if(topics.contains(reportDTO.topic())){
            System.out.println(reportDTO.report());
        }
    }
}
