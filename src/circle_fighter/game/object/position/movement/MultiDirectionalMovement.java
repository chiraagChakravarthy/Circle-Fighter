package circle_fighter.game.object.position.movement;

import circle_fighter.file.DataStorage;
import circle_fighter.game.object.position.Position;
import circle_fighter.game.object.position.UpdatingPosition;
import circle_fighter.game.object.position.Vector;

public class MultiDirectionalMovement extends MovementVector {
    private boolean[] direction;
    private float[] velocities;
    private float acc, dcc, maxVel;

    public MultiDirectionalMovement(UpdatingPosition position, Vector vector, float acc, float dcc, float maxVel, int directions){
        super(position, vector);
        this.direction = new boolean[directions];
        this.velocities = new float[directions];
        this.acc = acc;
        this.dcc = dcc;
        this.maxVel = maxVel;
    }

    public MultiDirectionalMovement(UpdatingPosition position, Vector vector, DataStorage storage){
        super(position, vector);
        direction = new boolean[storage.get(0)];
        velocities = new float[storage.get(0)];
        acc = storage.getFloat(1);
        dcc = storage.getFloat(2);
        maxVel = storage.getFloat(3);
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
    }

    public void clearDirections(){
        direction = new boolean[direction.length];
    }

    @Override
    public void hardLoad(DataStorage storage) {
        DataStorage directionStorage = storage.getSubStorage(0),
                velocityStorage = storage.getSubStorage(1);
        //TODO for this and all other boolean storage, create method which allows for an array of 32 booleans to be collected from a single integer index
        for (int i = 0; i < direction.length; i++) {
            direction[i] = directionStorage.get(i)==1;
            velocities[i] = velocityStorage.getFloat(i);
        }
    }

    @Override
    public void hardSave(DataStorage storage) {
        DataStorage directionStorage = storage.getSubStorage(0),
                velocityStorage = storage.getSubStorage(1);
        for (int i = 0; i < direction.length; i++) {
            directionStorage.set(i, direction[i]?1:0);
            velocityStorage.setFloat(i, velocities[i]);
        }
    }

    @Override
    public void save(DataStorage storage) {
        storage.set(0, direction.length).setFloat(1, acc).setFloat(2, dcc).setFloat(3, maxVel);
    }
}
