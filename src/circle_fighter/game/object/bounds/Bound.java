package circle_fighter.game.object.bounds;

import circle_fighter.game.object.position.Position;

import java.awt.*;

public abstract class Bound {
    public abstract boolean intersects(CircularBound bound);

    public abstract boolean intersects(PointBound bound);

    public abstract boolean intersects(PolygonBound bound);


    public abstract Rectangle outerBounds();

    protected Position intersection(float s1, float s2, float x1, float y1, float x2, float y2){
        if(s1 == s2)
            return new Position(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY);
        if(Double.isInfinite(s1))
            return new Position(x1, s2*(x1-x2)+y2);
        if(Double.isInfinite(s2))
            return new Position(x2, s1*(x2-x1)+y1);
        float x = (s1*x1-y1-s2*x2+y2)/(s1-s2);
        return new Position(x, s1*(x-x1)+y1);
    }
}