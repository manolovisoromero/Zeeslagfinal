package client;

import java.awt.*;
import java.util.ArrayList;

public class Fleet {
    private ArrayList<Ship> ships;

    Fleet() {
        this.ships = new ArrayList<>();
    }

    public ArrayList<Ship> getShips() {
        return ships;
    }

    private boolean addShip(Ship shipParam) {
        for (Ship ship : this.ships) {
            if (shipParam.getShipType() == ship.getShipType()) {
                this.ships.remove(ship);
            }

            for (Point pExisting : ship.getAllPoints()) {
                for (Point pNew : shipParam.getAllPoints()) {
                    if (pExisting.x == pNew.x && pExisting.y == pNew.y || pNew.x >= 10 || pNew.y >= 10) {
                        return false;
                    }
                }
            }
        }

        Point p = shipParam.getAllPoints().get(shipParam.getAllPoints().size() - 1);
        if (p.x >= 10 || p.y >= 10) {
            return false;
        }

        this.ships.add(shipParam);

        return true;
    }

    public boolean addShip(ShipType shipType, int x, int y, boolean horizontal) {
        Point p = new Point(x, y);
        Ship ship = new Ship(shipType, p, horizontal);
        return this.addShip(ship);
    }

    public Ship getShipOfSquare(int x, int y) {
        for (Ship ship : this.ships) {
            for (Point currentPoint : ship.getAllPoints()) {
                if (currentPoint.x == x && currentPoint.y == y) {
                    return ship;
                }
            }
        }
        return null;
    }

    public Ship getShipOfShipType(ShipType type) {
        for (Ship ship : this.ships) {
            if (ship.getShipType() == type) {
                return ship;
            }
        }
        return null;
    }

    public void removeShip(Ship ship) {
        this.ships.remove(ship);
    }

    public void resetShips() {
        this.ships = new ArrayList<>();
    }

    public boolean getIfAllShipsArePlaced() {
        return this.ships.size() == 5;
    }
}
