package circle_fighter.game.object.position.movement;

import circle_fighter.file.DataStorage;
import circle_fighter.game.object.position.Position;
import circle_fighter.game.object.position.UpdatingPosition;
import circle_fighter.game.object.position.Vector;

public class VelAngAccMovement extends VelAccMovement{
    private float accAng, maxVelAng;
    private boolean left, right;

    public VelAngAccMovement(UpdatingPosition position, Vector vector, float acc, float maxVel, float accAng, float maxVelAng) {
        super(position, vector, acc, maxVel);
        this.accAng = accAng;
        this.maxVelAng = maxVelAng;
        left = false;
        right = false;
    }

    public VelAngAccMovement(UpdatingPosition position, Vector vector, DataStorage storage){
        super(position, vector, storage.getSubStorage(0));
        accAng = storage.getFloat(0);
        maxVelAng = storage.getFloat(1);
    }

    @Override
    public void tick() {
        vector.setVelAng(right?Math.min(vector.getVelAng()+accAng, maxVelAng):vector.getVelAng()>0?Math.max(vector.getVelAng()-accAng, 0):vector.getVelAng());
        vector.setVelAng(left?Math.max(vector.getVelAng()-accAng, -maxVelAng):vector.getVelAng()<0?Math.min(vector.getVelAng()+accAng, 0):vector.getVelAng());
        super.tick();
    }

    @Override
    public VelAngAccMovement setBack(boolean back) {
        return (VelAngAccMovement) super.setBack(back);
    }

    @Override
    public VelAngAccMovement setFront(boolean front) {
        return (VelAngAccMovement) super.setFront(front);
    }

    public VelAngAccMovement setLeft(boolean left) {
        this.left = left;
        return this;
    }

    public VelAngAccMovement setRight(boolean right) {
        this.right = right;
        return this;
    }

    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
    }

    @Override
    public void save(DataStorage storage) {
        super.save(storage.getSubStorage(0));
        storage.setFloat(0, accAng).setFloat(1, maxVelAng);
    }

    @Override
    public void hardLoad(DataStorage storage) {
        super.hardLoad(storage.getSubStorage(0));
        left = storage.get(0)==1;
        right = storage.get(1)==1;
    }

    @Override
    public void hardSave(DataStorage storage) {
        super.hardSave(storage.getSubStorage(0));
        storage.set(0, left?1:0).set(1, right?1:0);
    }
}