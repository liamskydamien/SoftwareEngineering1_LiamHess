package LearningPatterns.ObserverPattern;

import java.util.List;

public class Publisher {

    private List<Subscriber> subscribers;

    public void addSubscriber(Subscriber a){
        subscribers.add(a);
    }

    public void removeSubscriber(Subscriber a){
        subscribers.remove(a);
    }
    public void notifySubscriber(){
        for(Subscriber subscriber : subscribers){
            subscriber.update();
        }
    }
}
