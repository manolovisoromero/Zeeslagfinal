package client;

public interface ISubject {

    void Attach(IObserver o);
    void Detach(IObserver o);
    void Notify();
}
