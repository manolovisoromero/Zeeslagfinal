package client;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class clientcontroller implements ActionListener, IObserver {

    @FXML
    public Button Sendmsg;

    public logica log = logica.getInstance();

    @FXML
    private TextField NameText;

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void sendMsg(String msg){

       // websocket handle = new websocket("ws://localhost:8091/")



    }

    public void print(){
        System.out.println("hoi");
    }

    public void initialize(){

        log.Attach(this);


    }

    @Override
    public void update(Object o) {
        NameText.setText(log.getName());
    }
}
