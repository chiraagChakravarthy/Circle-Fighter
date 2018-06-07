package circle_fighter.game.object.bounds;

import circle_fighter.functionaliy.Renderable;
import circle_fighter.game.object.position.OnPositionChanged;
import circle_fighter.game.object.position.Position;
import circle_fighter.game.object.position.UpdatingPosition;

import java.awt.*;

public class PolygonBound extends Bound implements OnPositionChanged {
    private Position position;
    private Position[] relative, absolute;
    private float[] slopes;
    private boolean changed;
    private int[] lineRanges;

    public PolygonBound(UpdatingPosition position, Position[] relative){
        this.position = position;
        position.addListener(this);
        changed = true;
        this.relative = relative;
        absolute = new Position[relative.length];
        lineRanges = new int[absolute.length*4];
        slopes = new float[absolute.length];
    }

    @Override
    public boolean intersects(CircularBound bound) {
        return false;
    }

    @Override
    public boolean intersects(LineSegmentBound bound) {
        return false;
    }

    @Override
    public boolean intersects(PointBound bound) {
        if(changed)
            update();
        return inside(bound.getPosition());
    }

    @Override
    public boolean intersects(TriangularBound bound) {
        return false;
    }

    @Override
    public Rectangle outerBounds() {
        return null;
    }

    //TODO optimize finding odd/even intersects between horizontal line from point and edge of polygon
    private void update(){
        for (int i = 0; i < relative.length; i++) {
            absolute[i] = new Position(position).move(relative[i], true);
        }

        for (int i = 0; i < relative.length; i++) {
            int n = (i+1)%relative.length, m=4*i;
            slopes[i] = (relative[i].getY()-relative[n].getY())/(relative[i].getX()-relative[n].getX());
            lineRanges[m] = (int) Math.min(absolute[i].getX(), absolute[n].getX());
            lineRanges[m+1] = (int) Math.max(absolute[i].getX(), absolute[n].getX());
            lineRanges[m+2] = (int) Math.min(absolute[i].getY(), absolute[n].getY());
            lineRanges[m+3] = (int) Math.max(absolute[i].getY(), absolute[n].getY());
        }
        changed = false;
    }

    private boolean inside(Position position){
        int amount = 0;
        for (int i = 0; i < absolute.length; i++) {
            if(intersects(position.getX(), position.getY(), i))
                amount++;

        }
        return amount%2==1;
    }

    @Override
    public void onPositionChanged() {
        changed = true;
    }

    private boolean intersects(double x, double y, int index){
        int m = index*4;
        Position intersection = intersection(0, slopes[index], (int)x, (int)y,(int)absolute[index].getX(), (int)absolute[index].getY());
        return (Double.isInfinite(slopes[index])?intersection.getY()>lineRanges[m+2]&&intersection.getY()<lineRanges[m+3]:
                intersection.getX()>lineRanges[m]&&intersection.getX()<lineRanges[m+1])&&intersection.getX()>x;
    }

    private boolean inRange(double min, double max, double val){
        return val > min && val < max;
    }
}
