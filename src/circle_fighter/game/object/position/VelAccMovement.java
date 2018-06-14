package circle_fighter.game.object.position;

import circle_fighter.file.DataStorage;

public class VelAccMovement extends MovementVector {
    private float acc, maxVel;
    private float velocity;
    private boolean front, back;
    public VelAccMovement(Position position, Vector vector, float acc, float maxVel) {
        super(position, vector);
        this.acc = acc;
        this.maxVel = maxVel;
        velocity = 0;
        front = false;
        back = false;
    }

    public VelAccMovement(Position position, Vector vector) {
        super(position, vector);
        this.position = position;
        this.vector = vector;
        acc = 0;
        maxVel = 0;
    }

    @Override
    public void tick() {
        velocity = front?Math.min(velocity+acc, maxVel):velocity>0?Math.max(0, velocity-acc):velocity;
        velocity = back?Math.max(velocity-acc, -maxVel):velocity<0?Math.min(0, velocity+acc):velocity;

        vector.setVelX((float) (Math.cos(position.getRotation())*velocity));
        vector.setVelY((float) (Math.sin(position.getRotation())*velocity));
        position.apply(vector);
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
    public void from(DataStorage storage) {
        acc = Float.intBitsToFloat(storage.get(0));
        maxVel = Float.intBitsToFloat(storage.get(1));
    }

    @Override
    public void to(DataStorage storage) {
        storage.set(0, Float.floatToIntBits(acc))
                .set(1, Float.floatToIntBits(maxVel));
    }
}