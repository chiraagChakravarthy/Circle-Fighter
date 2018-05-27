package circle_fighter.game.object.position;

public class OmniDirectionalMovement extends MovementVector {
    private final double acc, maxVel;
    private boolean front, back;

    public OmniDirectionalMovement(Position position, Vector vector, double acc, double maxVel) {
        super(position, vector);
        this.acc = acc;
        this.maxVel = maxVel;
        front = false;
        back = false;
    }

    @Override
    public void tick() {
        double accX, accY;
        if(front){
            accX = Math.cos(position.getRotation())*acc;
            accY = Math.sin(position.getRotation())*acc;
        }
        else if(back){
            accX = -Math.cos(position.getRotation())*acc;
            accY = -Math.sin(position.getRotation())*acc;
        }
        else {
            double velocity = vector.velocity();
            accX = acc*vector.getVelX()/velocity;
            accY = acc*vector.getVelY()/velocity;
        }
        vector.setVelX(vector.getVelX()+accX);
        vector.setVelY(vector.getVelY()+accY);
        double velRatio = maxVel/vector.velocity();
        if(velRatio<1){
            vector.setVelX(vector.getVelX()*velRatio);
            vector.setVelY(vector.getVelY()*velRatio);
        }

        position.apply(vector);
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
}
