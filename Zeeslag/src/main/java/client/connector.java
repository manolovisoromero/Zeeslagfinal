package client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.glassfish.tyrus.client.ClientManager;

import javax.websocket.DeploymentException;
import javax.websocket.Session;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;


public class connector implements  ISubject {

    public String servermsg;

    public void setToSend(String toSend) {
        this.toSend = toSend;
    }

    public String getToSend() {
        return toSend;
    }

    public String toSend = "";

    public ArrayList<IObserver> getObservers() {
        return observers;
    }

    private ArrayList<IObserver> observers = new ArrayList<IObserver>();




    private static connector con = new connector();

    public static connector getInstance(){
        return con;
    }

    public Session sesh;

    public connector() {
    }


    public void start(){
        ClientManager client = ClientManager.createClient();
        Session session = null;
        try {
            session = client.connectToServer(WordgameClientEndpoint.class, new URI("ws://localhost:8025/websockets/game"));
        } catch (DeploymentException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        this.sesh = session;

    }

    public void send(String msg)  {
        setServermsg(msg);
        if(getToSend() != "-"){
            msg =  getToSend();
            setToSend("-");
        }else{msg = getToSend();}
        try {
            Thread.sleep(100);
            sesh.getBasicRemote().sendText(msg);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void Attach(IObserver o) {

        observers.add(o);

    }

    public void Detach(IObserver o) {

        observers.remove(o);

    }

    public void setServermsg(String msg){
        if(!msg.equals("-")){
            this.servermsg = msg;
            System.out.println("hoi");
            Notify();
        }
    }

    public String getServermsg(){
        return this.servermsg;
    }


    @Override
    public void Notify() {
        for(int i = 0; i< observers.size(); i++){
            observers.get(i).update(this);
        }

    }
}
