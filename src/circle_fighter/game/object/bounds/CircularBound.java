package circle_fighter.game.object.bounds;

import circle_fighter.file.DataStorage;
import circle_fighter.functionaliy.HardSavable;
import circle_fighter.functionaliy.Savable;
import circle_fighter.game.object.position.Position;
import circle_fighter.game.object.position.UpdatingPosition;

import java.awt.*;

public class CircularBound extends Bound {
    protected float radius;
    public CircularBound(UpdatingPosition centerPoint, float radius){
        super(centerPoint);
        this.radius = radius;
    }

    public CircularBound(UpdatingPosition position, DataStorage storage){
        super(position);
        radius = storage.getFloat(0);
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
    public void save(DataStorage storage) {
        storage.setFloat(0, radius);
    }
}
