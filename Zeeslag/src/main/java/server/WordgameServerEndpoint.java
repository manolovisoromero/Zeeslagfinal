package server;


import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.logging.Logger;

import javax.websocket.server.ServerEndpoint;


@ServerEndpoint("/game")
public class WordgameServerEndpoint {

    private Logger logger = Logger.getLogger(this.getClass().getName());
    logic log = logic.getInstance();

    @OnOpen
    public String onOpen(Session session) throws IOException, InterruptedException {
        logger.info("Connected...." + session.getId());
        return "open";


    }


    @OnMessage
    public void onMessage(String message, Session session) throws IOException, InterruptedException {
        System.out.println(message);
        switch(message){
            case "quit":
                try{
                    session.close(new CloseReason(CloseReason.CloseCodes.NORMAL_CLOSURE, "Game ended"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "x":
                //return "a";
        }

        //logic.getInstance().setCalc();
        if(logic.getInstance().send == false){
            logic.getInstance().send = true;
        }else{
            logic.getInstance().send = false;}
        logic.getInstance().sender(session);
        if(message != "x"){message = message + "---";}
        //return message;
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason){
        logger.info(String.format("Session %s closed because of %s", session.getId(), closeReason));
    }
}
