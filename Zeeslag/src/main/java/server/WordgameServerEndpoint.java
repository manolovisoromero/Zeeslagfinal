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
        session.getAsyncRemote().sendText("Start");
        return "open";


    }


    @OnMessage
    public void onMessage(String message, Session session) throws IOException, InterruptedException {
        logger.info("Received...."+ message);


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

        if(message.equals("Switch")){
            logic.getInstance().switchSend();
        }
        logic.getInstance().sender(session, message);
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason){
        logger.info(String.format("Session %s closed because of %s", session.getId(), closeReason));
    }
}
