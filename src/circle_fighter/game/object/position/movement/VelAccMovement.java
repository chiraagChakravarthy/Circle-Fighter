package circle_fighter.game.object.position.movement;

import circle_fighter.file.DataStorage;
import circle_fighter.game.object.position.Position;
import circle_fighter.game.object.position.UpdatingPosition;
import circle_fighter.game.object.position.Vector;

public class VelAccMovement extends MovementVector {
    private float acc, maxVel;
    private float velocity;
    private boolean front, back;
    public VelAccMovement(UpdatingPosition position, Vector vector, float acc, float maxVel) {
        super(position, vector);
        this.acc = acc;
        this.maxVel = maxVel;
        velocity = 0;
        front = false;
        back = false;
    }

    public VelAccMovement(UpdatingPosition position, Vector vector, DataStorage storage) {
        super(position, vector);
        this.position = position;
        this.vector = vector;
        acc = storage.getFloat(0);
        maxVel = storage.getFloat(1);
        velocity = 0;
        front = false;
        back = false;
    }

    @Override
    public void tick() {
        velocity = front?Math.min(velocity+acc, maxVel):velocity>0?Math.max(0, velocity-acc):velocity;
        velocity = back?Math.max(velocity-acc, -maxVel):velocity<0?Math.min(0, velocity+acc):velocity;

        vector.setVelX((float) (Math.cos(position.getRotation())*velocity));
        vector.setVelY((float) (Math.sin(position.getRotation())*velocity));
    }

    public VelAccMovement setFront(boolean front) {
        this.front = front;
        return this;
    }

    public VelAccMovement setBack(boolean back) {
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
        velocity = storage.getFloat(2);
    }

    @Override
    public void hardSave(DataStorage storage) {
        storage.set(0, front?1:0).set(1, back?1:0).setFloat(2, velocity);
    }

    @Override
    public void save(DataStorage storage) {
        storage.setFloat(0, acc).setFloat(1, maxVel);
    }
}