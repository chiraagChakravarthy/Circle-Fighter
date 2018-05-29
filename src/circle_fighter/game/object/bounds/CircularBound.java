package circle_fighter.game.object.bounds;

import circle_fighter.game.object.position.Position;

import java.awt.*;

public class CircularBound extends Bound {
    private final Position centerPoint;
    private final double radius;
    public CircularBound(Position centerPoint, double radius){
        this.centerPoint = centerPoint;
        this.radius = radius;
    }
    //Local
    @Override
    public boolean intersects(CircularBound bound) {
        return bound.centerPoint.distance(centerPoint)<=bound.radius+radius;
    }

    @Override
    public boolean intersects(LineSegmentBound bound) {
        return bound.intersects(this);
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
        return new Rectangle((int)(centerPoint.getX()-radius), (int)(centerPoint.getY()-radius), (int)(radius*2), (int)(radius*2));
    }

    public Position getCenterPoint() {
        return centerPoint;
    }

    public double getRadius() {
        return radius;
    }
}
