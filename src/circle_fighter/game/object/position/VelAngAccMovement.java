package circle_fighter.game.object.position;

import circle_fighter.file.DataStorage;

public class VelAngAccMovement extends VelAccMovement{
    private float accAng, maxVelAng;
    private boolean left, right;

    public VelAngAccMovement(Position position, Vector vector, float acc, float maxVel, float accAng, float maxVelAng) {
        super(position, vector, acc, maxVel);
        this.accAng = accAng;
        this.maxVelAng = maxVelAng;
        left = false;
        right = false;
    }

    public VelAngAccMovement(Position position, Vector vector){
        super(position, vector);
        accAng = 0;
        maxVelAng = 0;
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
    public void from(DataStorage storage) {
        super.from(storage.getSubStorage(0));
        accAng = Float.intBitsToFloat(storage.get(0));
        maxVelAng = Float.intBitsToFloat(storage.get(1));
    }

    @Override
    public void to(DataStorage storage) {
        super.to(storage.getSubStorage(0));
        storage.set(0, Float.floatToIntBits(accAng)).set(1, Float.floatToIntBits(maxVelAng));
    }
}