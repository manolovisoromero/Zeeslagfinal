/*
 * Sea Battle Start project.
 */
package Game;


import Game.ISeaBattleGame;
import javafx.scene.effect.Light;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import client.ISeaBattleGUI;
import client.ShipType;
import client.SquareState;
import client.Player;


import java.awt.*;
import java.util.*;

/**
 * The Sea Battle game. To be implemented.
 *
 * @author Nico Kuijpers
 */
public class SeaBattleGame implements ISeaBattleGame {

    private int playerNr = 0;
    public int connectedplayers = 0;
    public ArrayList<Player> players = new ArrayList<>();
    public ArrayList<ShipType> playerships = new ArrayList<>();
    public ArrayList<ShipType> notplaced = new ArrayList<>();
    public boolean hori;
    int randomx, randomy;
    Random rn = new Random();


    private static final Logger log = LoggerFactory.getLogger(SeaBattleGame.class);

    @Override
    public void registerPlayer(String name, String password, ISeaBattleGUI application, boolean singlePlayerMode) {
        //REST confirm registration
        Player player = new Player(returnPlayerNr(),name,password,singlePlayerMode,application);
        players.add(player);
        log.debug("Register Player {} - password {}", name, password);
        playerNr++;
        //throw new UnsupportedOperationException("Method registerPlayer() not implemented.");
    }

    public int returnPlayerNr(){
        if(connectedplayers == 0){
            return 1;
        }else{
            return 2;
        }
    }

    @Override
    public void placeShipsAutomatically(int playerNr, ArrayList<ShipType> notplaced, int grid [][]) {
        for(ShipType ship: ShipType.values()){
            notplaced.add(ship);
        }
        for(int i = 0; i< 5;){
            int randomhor = rn.nextInt(2);
            if(randomhor == 0){
                hori = true;
                randomx = rn.nextInt(10-getShipLength(notplaced.get(i)));
                randomy = rn.nextInt(10);
            }else{
                hori = false;
                randomx = rn.nextInt(10);
                randomy = rn.nextInt(10-getShipLength(notplaced.get(i)));
            }
            if(!shipDetected(grid,randomx,randomy,notplaced.get(i),hori)) {
                placeShip(1, notplaced.get(i), randomx, randomy, hori, grid);
                i++;
            }

        }
    }

    @Override
    public void placeShip(int playerNr, ShipType shipType, int bowX, int bowY, boolean horizontal, int [][] grid) {
        if(shipDetected(grid,bowX,bowY,shipType,horizontal)){
            return;
        }
        if(horizontal){
            for(int i = 0; i< getShipLength(shipType);i++){
                grid[bowY][i+bowX] = 1;
            }
        }
        if(!horizontal){
            for(int i = 0; i< getShipLength(shipType);i++){
                grid[bowY+i][bowX] = 1;
            }

        }
        addShip(shipType,playerNr,playerships);
        System.out.println("field\n"+Arrays.deepToString(grid).replace("], ", "]\n"));


        //throw new UnsupportedOperationException("Method placeShip() not implemented.");
    }

    public void addShip(ShipType ship, int PlayerNr, ArrayList<ShipType> list){
        //get player list = playerships

        for(ShipType Ship : list){
            if(Ship == ship){
                return;
            }
        }
        list.add(ship);
    }

    public boolean shipDetected(int [][] grid, int bowX, int bowY, ShipType shipType, boolean horizontal){
        if(horizontal){
            for(int i = 0; i< getShipLength(shipType);i++){
                if(grid[bowY][i+bowX] != 0){
                    System.out.println("Ship detected.");
                    return true;
                }
            }
        }
        if(!horizontal){
            for(int i = 0; i< getShipLength(shipType);i++){
                if(grid[i+bowY][bowX] != 0){
                    System.out.println("Ship detected.");
                    return true;
                }
            }
        }
        return false;
    }

    public  int getShipLength(ShipType ship){
        switch(ship){
            case CRUISER:
                return 3;
            case SUBMARINE:
                return 3;
            case BATTLESHIP:
                return 4;
            case MINESWEEPER:
                return 2;
            case AIRCRAFTCARRIER:
                return 5;
        }

        return 0;
    }



    @Override
    public void removeShip(int playerNr, int posX, int posY, ArrayList<ShipType> list, int grid [][],boolean hori) {
        //When player is ready, the arraylist and grid parameter can be removed
        for(Iterator<ShipType> iter = list.listIterator(); iter.hasNext();){
            ShipType ship = iter.next();
            for(int i = 0; i< getShipLength(ship); i++){
                if(hori){
                    if(grid[posY][posX+i] == 1){
                        iter.remove();
                    }
                }
            }
        }
    }

    public Player getEnemy(int playerNr){
        for(Player player: players){
            if(player.getPlayerNr() == playerNr){
                return player;
            }
        }
        return null;
    }



    @Override
    public void removeAllShips(int playerNr) {
        throw new UnsupportedOperationException("Method removeAllShips() not implemented.");
    }

    @Override
    public void notifyWhenReady(int playerNr) {
        throw new UnsupportedOperationException("Method notifyWhenReady() not implemented.");
    }

    public boolean alreadyShot(int playerNr, Point p){
        for(Point point : getEnemy(playerNr).getGrid().getPointsShot()){
            if(point == p){
                return false;
            }
        }
        return true;


    }

    @Override
    public void fireShot(int playerNr, int posX, int posY) {
    }

    @Override
    public void startNewGame(int playerNr) {
        throw new UnsupportedOperationException("Method startNewGame() not implemented.");
    }

    @Override
    public int getPlayerCount() {
        return playerNr;
    }


}
