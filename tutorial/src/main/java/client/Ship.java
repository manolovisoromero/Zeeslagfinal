package client;

import java.awt.*;
import java.util.ArrayList;

public class Ship {
    private ShipType shipType;
    private Point origin;
    private boolean horizontal;

    public Ship(ShipType shipType, Point origin, boolean horizontal) {
        this.shipType = shipType;
        this.origin = origin;
        this.horizontal = horizontal;
    }

    public ShipType getShipType() {
        return shipType;
    }

    public void setShipType(ShipType shipType) {
        this.shipType = shipType;
    }

    public Point getOrigin() {
        return origin;
    }

    public void setOrigin(Point origin) {
        this.origin = origin;
    }

    public boolean isHorizontal() {
        return horizontal;
    }

    public void setHorizontal(boolean horizontal) {
        this.horizontal = horizontal;
    }

    public int getLength() {
        switch (this.shipType) {
            case AIRCRAFTCARRIER:
                return 5;
            case BATTLESHIP:
                return 4;
            case SUBMARINE:
                return 3;
            case CRUISER:
                return 3;
            case MINESWEEPER:
                return 2;
        }
        return 0;
    }

    public ArrayList<Point> getAllPoints() {
        ArrayList<Point> points = new ArrayList<>();
        int x = this.getOrigin().x;
        int y = this.getOrigin().y;

        for (int i = 0; i < this.getLength(); i++) {
            points.add(new Point(x, y));
            if (this.isHorizontal()) x++;
            else y++;
        }
        return points;
    }
}
