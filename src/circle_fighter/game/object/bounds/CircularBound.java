package circle_fighter.game.object.bounds;

import circle_fighter.file.DataStorage;
import circle_fighter.game.object.position.Position;

import java.awt.*;

public class CircularBound extends Bound {
    protected float radius;
    public CircularBound(Position centerPoint, float radius){
        super(centerPoint);
        this.radius = radius;
    }

    public CircularBound(Position position){
        super(position);
    }

    //Local
    @Override
    public boolean intersects(CircularBound bound) {
        return bound.position.distance(position)<=bound.radius+radius;
    }

    @Override
    public boolean intersects(PointBound bound) {
        return bound.intersects(this);
    }

    @Override
    public boolean intersects(PolygonBound bound) {
        return bound.intersects(this);
    }

    @Override
    public Rectangle outerBounds() {
        return new Rectangle((int)(position.getX()-radius), (int)(position.getY()-radius), (int)(radius*2), (int)(radius*2));
    }

    public Position getCenterPoint() {
        return position;
    }

    public float getRadius() {
        return radius;
    }

    @Override
    public void from(DataStorage storage) {
        radius = Float.intBitsToFloat(storage.get(0));
    }

    @Override
    public void to(DataStorage storage) {
        storage.set(0, Float.floatToIntBits(radius));
    }
}
