package circle_fighter.game.object.position;

public abstract class MovementVector {
    protected Position position;
    protected Vector vector;
    public MovementVector(Position position, Vector vector){
        this.position = position;
        this.vector = vector;
    }
    public abstract void tick();
}