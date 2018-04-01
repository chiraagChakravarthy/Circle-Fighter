package circle_fighter.game.object.position;

public class VelAngAccMovement extends VelAccMovement {
    private final double accAng, maxVelAng;
    private boolean left, right;

    public VelAngAccMovement(Position position, Vector vector, double acc, double maxVel, double accAng, double maxVelAng) {
        super(position, vector, acc, maxVel);
        this.accAng = accAng;
        this.maxVelAng = maxVelAng;
        left = false;
        right = false;
    }

    @Override
    public void tick() {
        super.tick();
        vector.setVelAng(right?Math.min(vector.getVelAng()+accAng, maxVelAng):vector.getVelAng()>0?Math.max(vector.getVelAng()-accAng, 0):vector.getVelAng());
        vector.setVelAng(left?Math.max(vector.getVelAng()-accAng, -maxVelAng):vector.getVelAng()<0?Math.min(vector.getVelAng()+accAng, 0):vector.getVelAng());

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
}