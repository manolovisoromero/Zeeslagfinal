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

            if(count ==0){
                Thread.sleep(2000);
                setSend(false);
            }
        }

        count = count + getCalc();
        return count;
    }


    public void sender(Session session) throws IOException, InterruptedException {
        while(isSend() == true) {
            Thread.sleep(50);
            session.getAsyncRemote().sendText(Integer.toString(getCount()));
        }
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int count = 1;


}
