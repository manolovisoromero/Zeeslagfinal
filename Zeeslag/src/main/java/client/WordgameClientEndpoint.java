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


    private Logger logger = Logger.getLogger(this.getClass().getName());

    @OnOpen
    public void onOpen(Session session) throws IOException {
        logger.info("Connected... " + session.getId());
        session.getBasicRemote().sendText("Start");
    }

    @OnMessage
    public void onMessage(String message, Session session){


        logger.info("Received...."+ message);

            connector.getInstance().send(message);

            //session.getBasicRemote().sendText("s");


    }


    @OnClose
    public void onClose(Session session, CloseReason closeReason){
        logger.info(String.format("Session %s close because of %s", session.getId(), closeReason));
    }


}
