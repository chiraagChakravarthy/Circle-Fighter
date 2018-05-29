package circle_fighter.game.object.bounds;

import java.awt.*;

public abstract class Bound {
    public abstract boolean intersects(CircularBound bound);

    public abstract boolean intersects(LineSegmentBound bound);

    public abstract boolean intersects(PointBound bound);

    public abstract boolean intersects(TriangularBound bound);

    public abstract Rectangle outerBounds();

    protected boolean clp(double min, double max, double val){
        return val<min&&val>max;
    }
}