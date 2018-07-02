package circle_fighter.game.object.bounds;

import circle_fighter.file.DataStorage;
import circle_fighter.game.object.position.Position;
import circle_fighter.game.object.position.UpdatingPosition;

import java.awt.*;

public class PointBound extends Bound {

    public PointBound(UpdatingPosition position){
        super(position);
    }

    @Override
    public boolean intersects(CircularBound bound) {
        return position.distance(bound.getCenterPoint()) <= bound.getRadius();
    }

    @Override
    public boolean intersects(PointBound bound) {
        return false;
    }

    @Override
    public boolean intersects(PolygonBound bound) {
        return bound.intersects(this);
    }

    @Override
    public Rectangle outerBounds() {
        return new Rectangle((int)position.getX(), (int)position.getY(), 1, 1);
    }
}
