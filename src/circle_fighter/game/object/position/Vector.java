package circle_fighter.game.object.position;

import circle_fighter.file.DataStorage;
import circle_fighter.functionaliy.HardSavable;

public class Vector implements HardSavable{
    private float velX, velY, velAng;

    public Vector(float velX, float velY, float velAng){
        this.velX = velX;
        this.velY = velY;
        this.velAng = velAng;
    }


    public float getVelX() {
        return velX;
    }

    public float getVelY() {
        return velY;
    }

    public float getVelAng() {
        return velAng;
    }

    public Vector setVelX(float velX) {
        this.velX = velX;
        return this;
    }

    public Vector setVelY(float velY) {
        this.velY = velY;
        return this;
    }

    public Vector setVelAng(float velAng) {
        this.velAng = velAng;
        return this;
    }

    public float velocity(){
        return (float) Math.sqrt(velX*velX+velY*velY);
    }

    @Override
    public void hardLoad(DataStorage storage) {
        velX = storage.getFloat(0);
        velY = storage.getFloat(1);
        velAng = storage.getFloat(2);
    }

    @Override
    public void hardSave(DataStorage storage) {
        storage.setFloat(0, velX).setFloat(1, velY).setFloat(2, velAng);
    }
}