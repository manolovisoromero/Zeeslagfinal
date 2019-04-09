package client;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Grid {
    private Fleet fleet;
    private HashMap<Point, SquareState> grid;
    private ArrayList<Point> pointsShot;

    Grid(Fleet fleet) {
        this.fleet = fleet;
        this.grid = new HashMap<>();
        this.pointsShot = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Point p = new Point(i, j);
                this.grid.put(p, SquareState.WATER);
            }
        }
    }

    public Fleet getFleet() {
        return fleet;
    }

    public void setSquareStateOfSquare(Point p, SquareState state) throws IndexOutOfBoundsException {
        if (p.x >= 10 || p.y >= 10 || p.x < 0 || p.y < 0) throw new IndexOutOfBoundsException();

        this.grid.put(p, state);
    }

    public void setSquareStateOfSquare(int x, int y, SquareState state) throws IndexOutOfBoundsException {
        this.setSquareStateOfSquare(new Point(x, y), state);
    }

    public SquareState getSquareStateOfSquare(int posX, int posY) {
        Point p = new Point(posX,posY);
        return this.grid.get(p);
    }

    public ArrayList<Point> getPointsShot() {
        return this.pointsShot;
    }

    public void addPointShot(Point p) {
        this.pointsShot.add(p);
    }

    public void addPointShot(int x, int y) {
        this.pointsShot.add(new Point(x, y));
    }

    public void resetPointShot() {
        this.pointsShot = new ArrayList<>();
    }

    public boolean ShipSunk(Ship ship) {
        ArrayList<Point> allpointshit = new ArrayList<>();
        for (Point p : ship.getAllPoints()) {
            if (this.getSquareStateOfSquare(p.x, p.y) == SquareState.SHOTHIT || this.getSquareStateOfSquare(p.x, p.y) == SquareState.SHIPSUNK) {
                allpointshit.add(p);
            }
        }
        if (allpointshit.size() == ship.getLength()) return true;

        return false;
    }
    public boolean allShipsSunk() {
        int i = 0;
        for (Ship ship : this.getFleet().getShips()) {
            if (this.ShipSunk(ship)) {
                i++;
            }
        }
        if (i == 5) {
            return true;
        }
        else {
            return false;
        }
    }
}
