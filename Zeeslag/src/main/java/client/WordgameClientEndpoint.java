package client;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Logger;

import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.DeploymentException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

import org.glassfish.tyrus.client.ClientManager;


@ClientEndpoint
public class WordgameClientEndpoint {

    public String msg;

    private static CountDownLatch latch;
    logica log = logica.getInstance();

    private Logger logger = Logger.getLogger(this.getClass().getName());

    @OnOpen
    public void onOpen(Session session){
        logger.info("Connected... " + session.getId());
        //session.getBasicRemote().sendText("Start");
    }

    @OnMessage
    public void onMessage(String message, Session session){
        String msg = "x";

//        if(message != "x"){
//            log.setName(message);
//        }
        //BufferedReader bufferRead = new BufferedReader((new InputStreamReader(System.in)));
//        if(log.getMessages().size() > 0){
//            msg = log.getMessages().get(0);
//        }
//        else{msg = "x";}
        logger.info("Received...."+ message);
        //String userInput = bufferRead.readLine();
        try {
            connector.getInstance().setServermsg(message);
            if(message.length() > 4){session.getBasicRemote().sendText("van client");}

        } catch (IOException e) {
            e.printStackTrace();
        }
        //return msg;
    }


    @OnClose
    public void onClose(Session session, CloseReason closeReason){
        logger.info(String.format("Session %s close because of %s", session.getId(), closeReason));
        latch.countDown();
    }

//    public static void main(String[] args){
//        latch = new CountDownLatch(1);
//
//        ClientManager client = ClientManager.createClient();
//        try {
//            client.connectToServer(WordgameClientEndpoint.class, new URI("ws://localhost:8025/websockets/game"));
//        } catch (DeploymentException e) {
//            e.printStackTrace();
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        }
//        try {
//            latch.wait();
//        } catch ( InterruptedException e) {
//           throw new RuntimeException();
//        }
//
//
//    }
}
