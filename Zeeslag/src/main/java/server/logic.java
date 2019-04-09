package server;

import javax.websocket.Session;
import java.io.IOException;

public class logic {

    private static logic log = new logic();

    public boolean teller = true;

    public boolean isSend() {
        return send;
    }

    public void setSend(boolean send) {
        this.send = send;
    }

    public void switchSend(){
        this.send = !this.send;

    }

    public boolean send = true;


    private logic(){

    }

    public int getCalc() {
        return calc;
    }

    public void setCalc() {
        if(teller){
            calc = -1;
            teller = false;
        }else{calc = 1;
        teller = true;}
    }

    public int calc = 1;

    public static logic getInstance(){
        return log;
    }

    public int getCount() throws InterruptedException {
        if(count == 100 || count == 0){
            setCalc();

        }

        count = count + getCalc();
        return count;
    }


    public void sender(Session session, String msgin) throws IOException, InterruptedException {
        System.out.println(isSend());
        String msg;
        if(isSend()) {
            msg = Integer.toString(getCount());
        }else{ msg = "-";}
        Thread.sleep(100);
        session.getBasicRemote().sendText(msg);
    }



    public int count = 1;


}
