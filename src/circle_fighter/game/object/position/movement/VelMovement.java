package circle_fighter.game.object.position.movement;

import circle_fighter.file.DataStorage;
import circle_fighter.game.object.position.Position;
import circle_fighter.game.object.position.UpdatingPosition;
import circle_fighter.game.object.position.Vector;

public class VelMovement extends MovementVector {
    private float velocity;
    private boolean front, back;

    public VelMovement(UpdatingPosition position, Vector vector, float velocity) {
        super(position, vector);
        this.velocity = velocity;
        front = false;
        back = false;
    }

    public VelMovement(UpdatingPosition position, Vector vector, DataStorage storage){
        super(position, vector);
        velocity = storage.getFloat(0);
        front = false;
        back = false;
    }

    @Override
    public void tick() {
        vector.setVelX((float) (Math.cos(position.getRotation())*velocity));
        vector.setVelY((float)(Math.sin(position.getRotation())*velocity));
    }

    public void setFront(boolean front) {
        this.front = front;
    }

    public boolean isFront() {
        return front;
    }

    public void setBack(boolean back) {
        this.back = back;
    }

    public boolean isBack() {
        return back;
    }

    @Override
    public void save(DataStorage storage) {
        storage.setFloat(0, velocity);
    }
}
