package circle_fighter.game.object.bounds;

import circle_fighter.game.object.position.Position;

import java.awt.*;

public class RectangularBound extends Bound {
    private final Position position;
    private final double width, height;
    public RectangularBound(Position position, double width, double height){
        this.position = position;
        this.width = width;
        this.height = height;
    }
    //Local
    @Override
    public boolean intersects(CircularBound bound) {
        double cx = bound.getCenterPoint().getX(),
                cy = bound.getCenterPoint().getY(),
                r = bound.getRadius(),
                x = position.getX(),
                y = position.getY(),
                d1 = Position.distance(cx, cy, x, y),
                d2 = Position.distance(cx, cy, x + width, y),
                d3 = Position.distance(cx, cy, x, y + height),
                d4 = Position.distance(cx, cy, x + width, y + height);
        boolean horClp = clp(x, x + width, cx + r) && clp(x, x + width, cx - r),
                vertClp = clp(y, y + height, cy + r) && clp(y, y + height, cy - r),
                horCClp = clp(x, x + width, cx),
                vertCClp = clp(y, y + height, cy);
        return d1 < r || d2 < r || d3 < r || d4 < r || horClp && vertClp && (horCClp || vertCClp);
    }

    //Local
    @Override
    public boolean intersects(RectangularBound bound) {
        return bound.outerBounds().intersects(outerBounds());
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
    public Rectangle outerBounds() {
        return new Rectangle((int)position.getX(), (int)position.getY(), (int)width, (int)height);
    }

    public Position getPosition() {
        return position;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
}
