package circle_fighter.game.object.bounds;

import circle_fighter.game.object.position.Position;

import java.awt.*;

public class LineSegmentBound extends Bound {
    private Position position;
    private double h, v;
    public LineSegmentBound(Position position, double h, double v){
        this.position = position;
        this.h = h;
        this.v = v;
    }

    //Local
    //TODO fix this algorithm
    @Override
    @Deprecated
    public boolean intersects(CircularBound bound) {
        double cx = bound.getCenterPoint().getX(),
                cy = bound.getCenterPoint().getY(),
                r = bound.getRadius(),
                x = position.getX(),
                y = position.getY();
        double d1 = Math.sqrt(x*x+y*y),
               d2 = bound.getCenterPoint().distance(position),
               d3 = Position.distance(position.getX()+x, position.getY()+y, bound.getCenterPoint().getX(), bound.getCenterPoint().getY());
        if(d1<r||d3<r)
            return true;
        double angle = Math.acos(d2*d2-d3*d3-d1*d1/(-2*d3*d1));
        double verticalDistance = Math.sin(angle)*d2;
        if(verticalDistance<r){
            double horizontalDistance = Math.cos(angle)*d2;
            return Position.distance(y/d1*horizontalDistance+x, y/d1*horizontalDistance+y, cx, cy)<r;
        }
        return false;
    }

    //Local
    @Override
    public boolean intersects(LineSegmentBound bound) { if(h==0)
            return clp(bound.position.getX(), bound.position.getX()+bound.h, position.getX())&&clp(bound.position.getY(), bound.position.getY()+bound.v, position.getY());
        else if(bound.h==0){
            return clp(position.getX(), position.getX()+h, bound.position.getX())&&clp(position.getY(), position.getY()+h, bound.position.getY());
        }
        double s = v / h, s1 = bound.v /bound.h;
        if(s==s1)
            return false;
         double intX = (s*position.getX()-position.getY()-s1*bound.position.getX()+bound.position.getY())/(s-s1);
         return clp(position.getX(), position.getX()+h, intX)&&clp(bound.position.getX(), bound.position.getX()+bound.h, intX);
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
        return new Rectangle((int) h, (int) v);
    }

    public void setH(double h) {
        this.h = h;
    }

    public void setV(double v) {
        this.v = v;
    }
}
