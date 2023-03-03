package LearningPatterns.ReportPublisher;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractReportPublisher {
    private List<Consumer> subscribers = new ArrayList<>();

    public void register(Consumer consumer){
        subscribers.add(consumer);
    }

    public void deregister(Consumer consumer){
        subscribers.remove(consumer);
    }

    public void notify(int id, String topic){
        for (Consumer c : subscribers){
            c.update(id, topic);
        }
    }

    public abstract void produce();

    public abstract void getReport();
}
