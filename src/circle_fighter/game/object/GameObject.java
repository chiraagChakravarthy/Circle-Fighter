package circle_fighter.game.object;

import circle_fighter.game.object.position.Position;
import circle_fighter.game.object.position.Vector;
import circle_fighter.game.plane.Plane;
import circle_fighter.functionaliy.Renderable;
import circle_fighter.functionaliy.Updatable;

import java.awt.*;

public abstract class GameObject implements Renderable, Updatable {
    protected Position position;
    protected Vector vector;
    protected Plane plane;
    private BoundExitAction action;
    private final int team;
    public GameObject(Position position, Plane plane, BoundExitAction action, int team){
        vector = new Vector(0, 0, 0);
        this.position = position;
        this.plane = plane;
        plane.getObjectManager().getUpdatableObjects().add(this);
        this.action = action;
        this.team = team;
    }

    @Override
    public void tick() {
        double top = plane.getBounds().exceedsTopBy(this),
                bottom = plane.getBounds().exceedsBottomBy(this),
                left = plane.getBounds().exceedsLeftBy(this),
                right = plane.getBounds().exceedsRightBy(this);

        switch (action){
            case BOUND:
                position.setX(position.getX()+right-left);
                position.setY(position.getY()+top-bottom);
                break;
            case DESPAWN:
                if(top!=0||bottom!=0||left!=0||right!=0)
                    plane.getObjectManager().getUpdatableObjects().remove(this);
                break;
        }
    }

    public int getTeam() {
        return team;
    }

    public Position getPosition() {
        return position;
    }

    public Vector getVector() {
        return vector;
    }

    public Plane getPlane() {
        return plane;
    }

    public abstract Rectangle getBounds();

    public enum BoundExitAction {
        BOUND,
        DESPAWN;
    }
}