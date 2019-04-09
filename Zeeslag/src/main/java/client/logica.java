package client;

import java.util.ArrayList;

public class logica implements  ISubject {

    public String name = "sjaak";
    public ArrayList<IObserver> getObservers() {
        return observers;
    }

    public ArrayList<String> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<String> messages) {
        this.messages = messages;
    }

    public ArrayList<String> messages = new ArrayList<>();

    private ArrayList<IObserver> observers = new ArrayList<IObserver>();

    private static final logica INSTANCE = new logica();

    private logica() {}

    public static logica getInstance() {
        return INSTANCE;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
        notify();
    }

    public void Attach(IObserver o) {

        observers.add(o);

    }

    public void Detach(IObserver o) {

        observers.remove(o);

    }

    public void Notify() {

        for(int i = 0; i< observers.size(); i++){
            observers.get(i).update(this);
        }

    }

    }

