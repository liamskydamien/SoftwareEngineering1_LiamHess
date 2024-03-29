package LearningPatterns.ReportPublisher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class AbstractReportPublisher {
    private List<Consumer> subscribers = new ArrayList<>();
    final HashMap<Integer, Report> reports = new HashMap<>();

    public void register(Consumer consumer){
        subscribers.add(consumer);
    }

    public void deregister(Consumer consumer){
        subscribers.remove(consumer);
    }

    public void notify(int id, String topic){
        for (Consumer c : subscribers){
            c.update(new ReportDTO(id, topic));
        }
    }

    public abstract void produce();

    public abstract Report getReport(int id);
}
