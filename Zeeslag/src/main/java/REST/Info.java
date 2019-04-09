package REST;


import java.util.ArrayList;

public class Info {

    private static Info info = new Info();

    private Info(){

    }

    public static Info getInstance(){
        return info;
    }

    public ArrayList<Combi> getCombis() {
        return combis;
    }

    private ArrayList<Combi> combis = new ArrayList<>();

    public boolean login(Combi comb){
        for(Combi combi : combis){
            if(combi == comb){
                return true;
            }
        }
        return false;
    }

    public Combi splitter(String login){
        String name = "";
        String pass = "";
        String temp = "";

        for(int i = 0; i< login.length(); i++){
            if(Character.toString(login.charAt(i)) != ":"){
                temp = temp + login.charAt(i);
            }else {
                name = temp;
                temp = "";
            }
        }
        pass = temp;
        Combi combi = new Combi(name,pass);
        return combi;
    }










}
