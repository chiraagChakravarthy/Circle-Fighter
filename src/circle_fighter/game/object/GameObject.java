package circle_fighter.game.object;

import circle_fighter.file.DataStorage;
import circle_fighter.functionaliy.HardSavable;
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
import circle_fighter.game.plane.Plane;

public abstract class GameObject implements Renderable, Updatable, Bounded, Polarized, Savable, HardSavable {
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

    public GameObject(Plane plane, BoundExitAction action, DataStorage storage){
        this.team = 0;
        this.plane = plane;
        this.action = action;
        movement = MovementRegistry.fromID(storage.get(0), storage.getSubStorage(0), new UpdatingPosition(0, 0), new Vector(0, 0, 0));
        position = movement.getPosition();
        vector = movement.getVector();
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

    @Override
    public void hardSave(DataStorage storage) {
        position.hardSave(storage.getSubStorage(0));
        vector.hardSave(storage.getSubStorage(1));
        movement.hardSave(storage.getSubStorage(2));
        storage.set(0, team);
    }

    @Override
    public void hardLoad(DataStorage storage) {
        team = storage.get(0);
        position.hardLoad(storage.getSubStorage(0));
        vector.hardLoad(storage.getSubStorage(1));
        movement.hardLoad(storage.getSubStorage(2));
    }
}