package circle_fighter.game.object.position;

public class MultiDirectionalMovement extends MovementVector {
    private boolean[] direction;
    private double[] velocities;
    private double acc, dcc, maxVel;

    public MultiDirectionalMovement(Position position, Vector vector, double acc, double dcc, double maxVel, int directions){
        super(position, vector);
        this.direction = new boolean[directions];
        this.velocities = new double[directions];
        this.acc = acc;
        this.dcc = dcc;
        this.maxVel = maxVel;
    }

    public void setDirection(int direction, boolean move){
        this.direction[direction] = move;
    }

    @Override
    public void tick() {
        vector.setVelX(0);
        vector.setVelY(0);
        for (int i = 0; i < direction.length; i++) {
            velocities[i] = direction[i]?Math.min(maxVel, velocities[i]+acc):Math.max(0, velocities[i]-dcc);
            vector.setVelX(vector.getVelX()+Math.cos(Math.PI*2*i/direction.length)*velocities[i]);
            vector.setVelY(vector.getVelY()+Math.sin(Math.PI*2*i/direction.length)*velocities[i]);
        }
        position.apply(vector);
    }

    public void clearDirections(){
        direction = new boolean[direction.length];
    }
}
