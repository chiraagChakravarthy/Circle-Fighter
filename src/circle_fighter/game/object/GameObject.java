package circle_fighter.game.object;

import circle_fighter.file.DataStorage;
import circle_fighter.functionaliy.Renderable;
import circle_fighter.functionaliy.Savable;
import circle_fighter.functionaliy.Updatable;
import circle_fighter.game.object.functionality.Bounded;
import circle_fighter.game.object.functionality.Polarized;
import circle_fighter.game.object.position.UpdatingPosition;
import circle_fighter.game.object.position.movement.MovementRegistry;
import circle_fighter.game.object.position.movement.MovementVector;
import circle_fighter.game.object.position.Position;
import circle_fighter.game.object.position.Vector;
import circle_fighter.game.object.position.movement.VelAngAccMovement;
import circle_fighter.game.plane.Plane;
import circle_fighter.user.element.UserMovement;

public abstract class GameObject implements Renderable, Updatable, Bounded, Polarized, Savable {
    protected UpdatingPosition position;
    protected Vector vector;
    protected MovementVector movement;
    protected Plane plane;
    private BoundExitAction action;
    private int team;

    public GameObject(Plane plane, BoundExitAction action, MovementVector movement, int team){
        vector = movement.getVector();
        this.position = movement.getPosition();
        this.plane = plane;
        this.action = action;
        this.team = team;
        this.movement = movement;
        plane.getObjectManager().add(this);
    }

    @Override
    public void tick() {
        movement.tick();
        position.apply(vector);
        float top = plane.getBounds().exceedsTopBy(this),
                bottom = plane.getBounds().exceedsBottomBy(this),
                left = plane.getBounds().exceedsLeftBy(this),
                right = plane.getBounds().exceedsRightBy(this);
        switch (action){
            case BOUND:
                position.setX(position.getX()-right-left);
                position.setY(position.getY()-top-bottom);
                break;
            case DESPAWN:
                if(top!=0||bottom!=0||left!=0||right!=0)
                    despawn();
                break;
        }
    }

    protected void despawn(){
        plane.getObjectManager().remove(this);
    }

    public int getTeam() {
        return team;
    }

    public UpdatingPosition getPosition() {
        return position;
    }

    public Vector getVector() {
        return vector;
    }

    public Plane getPlane() {
        return plane;
    }

    public enum BoundExitAction {
        BOUND,
        DESPAWN;
    }

    @Override
    public void save(DataStorage storage) {
        storage.set(0, MovementRegistry.toID(movement.getClass()));
        movement.save(storage.getSubStorage(0));
    }
}