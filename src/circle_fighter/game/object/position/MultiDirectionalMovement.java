package circle_fighter.game.object.position;

import circle_fighter.file.DataStorage;

public class MultiDirectionalMovement extends MovementVector {
    private boolean[] direction;
    private float[] velocities;
    private float acc, dcc, maxVel;

    public MultiDirectionalMovement(Position position, Vector vector, float acc, float dcc, float maxVel, int directions){
        super(position, vector);
        this.direction = new boolean[directions];
        this.velocities = new float[directions];
        this.acc = acc;
        this.dcc = dcc;
        this.maxVel = maxVel;
    }

    public MultiDirectionalMovement(Position position, Vector vector){
        this(position, vector, 0, 0, 0, 1);
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
            vector.setVelX((float) (vector.getVelX()+Math.cos(Math.PI*2*i/direction.length)*velocities[i]));
            vector.setVelY((float) (vector.getVelY()+Math.sin(Math.PI*2*i/direction.length)*velocities[i]));
        }
        position.apply(vector);
    }

    public void clearDirections(){
        direction = new boolean[direction.length];
    }

    @Override
    public void from(DataStorage storage) {
        direction = new boolean[storage.get(0)];
        acc = Float.intBitsToFloat(storage.get(1));
        dcc = Float.intBitsToFloat(storage.get(2));
        maxVel = Float.intBitsToFloat(storage.get(3));
    }

    @Override
    public void to(DataStorage storage) {
        storage.set(0, direction.length)
                .set(1, Float.floatToIntBits(acc))
                .set(2, Float.floatToIntBits(dcc))
                .set(3, Float.floatToIntBits(maxVel));
    }
}
