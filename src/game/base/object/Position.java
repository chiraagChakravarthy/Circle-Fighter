package game.base.object;

import javafx.geometry.Pos;

import java.awt.*;

public class Position {
    private double x, y, rotation;
    public Position(double x, double y, double rotation){
        this.x = x;
        this.y = y;
        this.rotation = rotation;
    }

    public Position(double x, double y){
        this(x, y, 0);
    }

    public Position(Point point){
        this(point.x, point.y);
    }

    public double distance(Position position){
        double a = position.x-x,
                o=position.y-y;
        return Math.sqrt(a*a+o*o);
    }

    public double angleTo(Position position){
        double a = position.x-x,
                o = position.y-y,
                h = Math.sqrt(a*a+o*o),
                c = a/h,
                t = Math.acos(c);
        return o<0?t:o<0?t+180:a>0?0:180;
    }

    public Position apply(Vector vector){
        x += vector.getVelX();
        y += vector.getVelY();
        rotation += vector.getVelAng();
        return this;
    }

    public Position setX(double x) {
        this.x = x;
        return this;
    }

    public Position setY(double y) {
        this.y = y;
        return this;
    }

    public Position setRotation(double rotation) {
        this.rotation = rotation;
        return this;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getRotation() {
        return rotation;
    }

    public Position clone(){
        return new Position(x, y, rotation);
    }
}
