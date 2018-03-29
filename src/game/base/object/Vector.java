package game.base.object;

public class Vector {
    private double velX, velY, velAng;
    public Vector(double velX, double velY, double velAng){
        this.velX = velX;
        this.velY = velY;
        this.velAng = velAng;
    }

    public double getVelX() {
        return velX;
    }

    public double getVelY() {
        return velY;
    }

    public double getVelAng() {
        return velAng;
    }

    public Vector setVelX(double velX) {
        this.velX = velX;
        return this;
    }

    public Vector setVelY(double velY) {
        this.velY = velY;
        return this;
    }

    public Vector setVelAng(double velAng) {
        this.velAng = velAng;
        return this;
    }
}