package circle_fighter.game.object.position.movement;

import circle_fighter.file.DataStorage;
import circle_fighter.game.object.position.Position;
import circle_fighter.game.object.position.UpdatingPosition;
import circle_fighter.game.object.position.Vector;

public class OmniDirectionalMovement extends MovementVector {
    private float acc, maxVel;
    private boolean front, back;

    public OmniDirectionalMovement(UpdatingPosition position, Vector vector, float acc, float maxVel) {
        super(position, vector);
        this.acc = acc;
        this.maxVel = maxVel;
        front = false;
        back = false;
    }

    public OmniDirectionalMovement(UpdatingPosition position, Vector vector, DataStorage storage){
        super(position, vector);
        acc = storage.getFloat(0);
        maxVel = storage.getFloat(1);
        front = false;
        back = false;
    }

    @Override
    public void tick() {
        float accX, accY;
        if(front){
            accX = (float) (Math.cos(position.getRotation())*acc);
            accY = (float) (Math.sin(position.getRotation())*acc);
        }
        else if(back){
            accX = (float) (-Math.cos(position.getRotation())*acc);
            accY = (float) (-Math.sin(position.getRotation())*acc);
        }
        else {
            float velocity = vector.velocity();
            accX = acc*vector.getVelX()/velocity;
            accY = acc*vector.getVelY()/velocity;
        }
        vector.setVelX(vector.getVelX()+accX);
        vector.setVelY(vector.getVelY()+accY);
        float velRatio = maxVel/vector.velocity();
        if(velRatio<1){
            vector.setVelX(vector.getVelX()*velRatio);
            vector.setVelY(vector.getVelY()*velRatio);
        }
    }

    public OmniDirectionalMovement setFront(boolean front) {
        this.front = front;
        return this;
    }

    public OmniDirectionalMovement setBack(boolean back) {
        this.back = back;
        return this;
    }

    public boolean isBack() {
        return back;
    }

    public boolean isFront() {
        return front;
    }

    @Override
    public void hardLoad(DataStorage storage) {
        front = storage.get(0)==1;
        back = storage.get(1)==1;
    }

    @Override
    public void hardSave(DataStorage storage) {
        storage.set(0, front?1:0).set(1, back?1:0);
    }

    @Override
    public void save(DataStorage storage) {
        storage.setFloat(0, acc).setFloat(1, maxVel);
    }
}
