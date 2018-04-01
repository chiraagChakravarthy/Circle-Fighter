package circle_fighter.game.object.position;

public class VelAccMovement extends MovementVector {
    private final double acc, maxVel;
    private double velocity;
    private boolean front, back;
    public VelAccMovement(Position position, Vector vector, double acc, double maxVel) {
        super(position, vector);
        this.acc = acc;
        this.maxVel = maxVel;
        velocity = 0;
        front = false;
        back = false;
    }

    @Override
    public void tick() {
        velocity = front?Math.min(velocity+acc, maxVel):velocity>0?Math.max(0, velocity-acc):velocity;
        velocity = back?Math.max(velocity-acc, -maxVel):velocity<0?Math.min(0, velocity+acc):velocity;

        vector.setVelX(Math.cos(position.getRotation())*velocity);
        vector.setVelY(Math.sin(position.getRotation())*velocity);
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
}
