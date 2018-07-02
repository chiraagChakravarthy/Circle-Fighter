package circle_fighter.game.object.bounds;

import circle_fighter.game.object.position.Position;
import circle_fighter.game.object.position.UpdatingPosition;
import circle_fighter.user.element.UserBase;

import java.awt.*;

public class CircularBound extends Bound {
    protected float radius;
    public CircularBound(UpdatingPosition position, float radius){
        super(position);
        this.radius = radius;
    }

    public CircularBound(UpdatingPosition position, UserBase base){
        super(position);
        radius = base.getFunctions()[UserBase.RADIUS].perform(base.get(UserBase.RADIUS));
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
}
