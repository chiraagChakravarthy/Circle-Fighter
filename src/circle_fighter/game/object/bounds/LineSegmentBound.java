package circle_fighter.game.object.bounds;

import circle_fighter.game.object.position.Position;

import java.awt.*;

public class LineSegmentBound extends Bound {
    private Position position;
    private Position[] absolute, transformed;

    public LineSegmentBound(Position position, Position p1, Position p2){
        this.position = position;
        absolute = new Position[]{
                p1, p2
        };
        transformed = new Position[2];
    }

    //Local
    //TODO fix this algorithm
    @Override
    @Deprecated
    public boolean intersects(CircularBound bound) {
        return false;
    }

    //Local
    @Override
    public boolean intersects(LineSegmentBound bound) {
        return false;
    }

    @Override
    public boolean intersects(PointBound bound) {
        return bound.intersects(this);
    }

    @Override
    public boolean intersects(TriangularBound bound) {
        return bound.intersects(this);
    }

    @Override
    public Rectangle outerBounds() {
        return null;
    }
}
