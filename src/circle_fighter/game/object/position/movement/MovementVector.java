package circle_fighter.game.object.position.movement;

import circle_fighter.functionaliy.HardSavable;
import circle_fighter.functionaliy.Savable;
import circle_fighter.game.object.position.Position;
import circle_fighter.game.object.position.UpdatingPosition;
import circle_fighter.game.object.position.Vector;

public abstract class MovementVector implements Savable, HardSavable {
    protected UpdatingPosition position;
    protected Vector vector;
    public MovementVector(UpdatingPosition position, Vector vector){
        this.position = position;
        this.vector = vector;
    }
    public abstract void tick();

    public Vector getVector() {
        return vector;
    }

    public UpdatingPosition getPosition() {
        return position;
    }
}