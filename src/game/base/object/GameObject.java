package game.base.object;

import game.base.Plane;
import interfaces.Renderable;
import interfaces.Updatable;

public abstract class GameObject implements Renderable, Updatable {
    protected Position position;
    protected Vector vector;
    protected Plane plane;
    public GameObject(Position position, Plane plane){
        vector = new Vector(0, 0, 0);
        this.position = position;
        this.plane = plane;
        plane.getManager().addObject(this);
    }

    @Override
    public void tick() {
        if(!plane.inBounds(this))
            plane.getManager().removeObject(this);
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
}