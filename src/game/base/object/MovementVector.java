package game.base.object;

public class MovementVector {
    private final double maxVel, maxVelAng, acc, accAng;
    private double velocity;
    private Position position;
    private Vector vector;
    private boolean left, right, front, back;
    public MovementVector(double maxVel, double maxVelAng, double acc, double angAcc, Position position, Vector vector){
        this.maxVel = maxVel;
        this.maxVelAng = maxVelAng;
        this.position = position;
        this.vector = vector;
        this.acc = acc;
        this.accAng = angAcc;
        left = false;
        right = false;
        front = false;
        back = false;
    }

    public double getMaxVel() {
        return maxVel;
    }

    public double getMaxVelAng() {
        return maxVelAng;
    }

    public MovementVector setFront(boolean front) {
        this.front = front;
        return this;
    }

    public MovementVector setBack(boolean back) {
        this.back = back;
        return this;
    }

    public MovementVector setLeft(boolean left) {
        this.left = left;
        return this;
    }

    public MovementVector setRight(boolean right) {
        this.right = right;
        return this;
    }

    public void tick() {
        velocity = front?Math.min(velocity+acc, maxVel):velocity>0?Math.max(0, velocity-acc):velocity;
        velocity = back?Math.max(velocity-acc, -maxVel):velocity<0?Math.min(0, velocity+acc):velocity;

        vector.setVelAng(right?Math.min(vector.getVelAng()+accAng, maxVelAng):vector.getVelAng()>0?Math.max(vector.getVelAng()-accAng, 0):vector.getVelAng());
        vector.setVelAng(left?Math.max(vector.getVelAng()-accAng, -maxVelAng):vector.getVelAng()<0?Math.min(vector.getVelAng()+accAng, 0):vector.getVelAng());

        vector.setVelX(Math.cos(position.getRotation())*velocity);
        vector.setVelY(Math.sin(position.getRotation())*velocity);

        position.apply(vector);
    }
}
