package Game;

import client.ShipType;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import client.ShipType;
import client.Ship;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class SeaBattleGameTest {

    public Game.SeaBattleGame seaBattleGame = new Game.SeaBattleGame();
    public ArrayList<ShipType> notplaced = new ArrayList<>();
    public int grid [][] = new int[10][10];
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void placeShipsAutomatically() {
        //Assign
        for(ShipType ship: ShipType.values()){
            notplaced.add(ship);
        }
        int testgrid[][] = new int [10][10];
        boolean expected = true;
        boolean DiffGrid = true;
        ArrayList<ShipType> testList = new ArrayList<>();


        //Act
        seaBattleGame.placeShipsAutomatically(1,notplaced,grid);
        seaBattleGame.placeShipsAutomatically(1,testList,testgrid);
        for(int i = 0; i< 10; i++){
            for(int j = 0; j<10; j++){
                if(grid[i][j] != testgrid[i][j]){
                    DiffGrid = true;
                }
            }
        }


        //Assert
        assertEquals(expected,DiffGrid);

    }

    @Test
    void placeShip() {
        seaBattleGame.placeShip(1, ShipType.MINESWEEPER,2,4,true, grid);
        Random rn = new Random();
    }

    @Test
    void getShipLength(){
        Assert.assertEquals(5,seaBattleGame.getShipLength(ShipType.AIRCRAFTCARRIER));
    }

    @Test
    void noShipDetected(){
        Assert.assertEquals(false,seaBattleGame.shipDetected(grid,0,0,ShipType.MINESWEEPER,true));
    }

    @Test
    void shipDetected(){
        grid[0][2] = 1;
        grid[0][3] = 1;
        Assert.assertEquals(false,seaBattleGame.shipDetected(grid,0,0,ShipType.MINESWEEPER,true));
    }

    @Test
    void addNewShip(){
        int checked = seaBattleGame.playerships.size();
        seaBattleGame.addShip(ShipType.MINESWEEPER,1,seaBattleGame.playerships);
        Assert.assertEquals(checked+1,seaBattleGame.playerships.size());
    }

    @Test
    void addSameShip(){
        seaBattleGame.playerships.add(ShipType.MINESWEEPER);
        int checked = seaBattleGame.playerships.size();
        seaBattleGame.addShip(ShipType.MINESWEEPER,1,seaBattleGame.playerships);
        Assert.assertEquals(checked,seaBattleGame.playerships.size());
    }
    @Test
    void removeShip() {
        grid [0][0] = 1;
        ArrayList<ShipType> lijst = new ArrayList<>();
        lijst.add(ShipType.SUBMARINE);
        seaBattleGame.removeShip(1,0,0,lijst,grid,true);
        assertEquals(0,lijst.size());
    }

    @Test
    void removeAllShips() {
    }

    @Test
    void notifyWhenReady() {
    }

    @Test
    void fireShot() {
    }

    @Test
    void startNewGame() {
    }

    @Test
    void getPlayerCount() {
    }

    @Test
    void registerPlayer() {
        Game.SeaBattleGame seaBattleGame = new Game.SeaBattleGame();

        int expectedValue = 1;

        //act
        seaBattleGame.registerPlayer("Player1", "geheim", null, true);

        //Assert
        Assert.assertEquals(expectedValue, seaBattleGame.getPlayerCount());
    }

    @Test
    void registerTwoPlayer() {
        Game.SeaBattleGame seaBattleGame = new Game.SeaBattleGame();

        int expectedValue = 2;

        //act
        seaBattleGame.registerPlayer("Player1", "geheim", null, true);
        seaBattleGame.registerPlayer("Player2", "Geheim", null, true);

        //Assert
        Assert.assertEquals(expectedValue, seaBattleGame.getPlayerCount());
    }
}
