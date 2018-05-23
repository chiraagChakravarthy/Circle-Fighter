package circle_fighter.game.object.bounds;

import circle_fighter.game.object.position.Position;

import java.awt.*;

public class PointBound extends Bound {
    private Position position;
    public PointBound(Position position){
        this.position = position;
    }
    @Override
    public boolean intersects(CircularBound bound) {
        return position.distance(bound.getCenterPoint()) <= bound.getRadius();
    }

    @Override
    public boolean intersects(RectangularBound bound) {
        return position.getX()>bound.getPosition().getX()&&position.getX()<bound.getPosition().getX()+bound.getWidth()
                && position.getY()>bound.getPosition().getY()&&position.getY()<bound.getPosition().getY()+bound.getHeight();
    }

    @Override
    public boolean intersects(LineSegmentBound bound) {
        return false;
    }

    @Override
    public boolean intersects(PointBound bound) {
        return false;
    }

    @Override
    public Rectangle outerBounds() {
        return new Rectangle((int)position.getX(), (int)position.getY(), 1, 1);
    }
}
