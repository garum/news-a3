package observer;

import java.util.ArrayList;
import java.util.List;

public class EventSource {
    private final List<Observer> observers = new ArrayList<>();

    public void notifyObservers(String event) {
        observers.forEach(observer -> observer.update(event));
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }
}
