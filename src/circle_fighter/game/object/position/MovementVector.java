package circle_fighter.game.object.position;

import circle_fighter.functionaliy.Savable;

public abstract class MovementVector implements Savable{
    protected Position position;
    protected Vector vector;
    public MovementVector(Position position, Vector vector){
        this.position = position;
        this.vector = vector;
    }
    public abstract void tick();
}