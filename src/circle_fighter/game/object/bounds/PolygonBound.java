package circle_fighter.game.object.bounds;

import circle_fighter.game.object.position.OnPositionChanged;
import circle_fighter.game.object.position.Position;
import circle_fighter.game.object.position.UpdatingPosition;

import java.awt.*;

public class PolygonBound extends Bound implements OnPositionChanged {
    protected Position position;
    protected Position[] relative, absolute;
    protected float[] slopes;
    protected boolean changed;
    protected float[] lineRanges;
    protected Rectangle outerBound;

    public PolygonBound(UpdatingPosition position, Position[] relative){
        this.position = position;
        position.addListener(this);
        this.relative = relative;
        absolute = new Position[relative.length];
        lineRanges = new float[absolute.length*4];
        slopes = new float[absolute.length];
    }

    @Override
    public boolean intersects(CircularBound bound) {
        return false;
    }

    @Override
    public boolean intersects(PointBound bound) {
        if(changed)
            update();
        return inside(bound.getPosition());
    }

    @Override
    public boolean intersects(PolygonBound bound) {
        if(changed)
            update();
        Position[] boundAbsolute = bound.getAbsolute();
        float[] boundSlopes = bound.getSlopes(),
                boundRanges = bound.getLineRanges();
        for (int i = 0; i < slopes.length; i++) {
            int m = i*4;
            if(bound.inside(absolute[i]))
                return true;
            for (int j = 0; j < boundSlopes.length; j++) {
                int n = j*4;
                Position intersection = intersection(slopes[i], boundSlopes[j], absolute[i].getX(), absolute[i].getY(), boundAbsolute[j].getX(), boundAbsolute[j].getY());
                if(intersection.getX() > Math.max(lineRanges[m], boundRanges[n])&&intersection.getX() < Math.min(lineRanges[m+1], boundRanges[m+1]))
                    return true;
            }
        }
        for (Position absolute : boundAbsolute) {
            if (inside(absolute))
                return true;
        }
        return false;
    }

    @Override
    public Rectangle outerBounds() {
        return outerBound;
    }

    //TODO optimize finding odd/even intersects between horizontal line from point and edge of polygon
    protected void update(){
        float maxX=0, maxY=0, minX=0, minY=0;
        for (int i = 0; i < relative.length; i++) {
            Position absolute = new Position(position).move(relative[i], true);
            this.absolute[i] = absolute;
            if(i==0){
                maxX = absolute.getX();
                minX = absolute.getX();
                maxY = absolute.getY();
                minY = absolute.getY();
            }
            maxX = Math.max(maxX, absolute.getX());
            maxY = Math.max(maxY, absolute.getY());
            minX = Math.min(minX, absolute.getX());
            minY = Math.min(minY, absolute.getY());
        }
        outerBound = new Rectangle((int)minX, (int)minY, (int)(maxX-minX), (int)(maxY-minY));
        for (int i = 0; i < relative.length; i++) {
            int n = (i+1)%relative.length, m=4*i;
            slopes[i] = (relative[i].getY()-relative[n].getY())/(relative[i].getX()-relative[n].getX());
            lineRanges[m] = Math.min(absolute[i].getX(), absolute[n].getX());
            lineRanges[m+1] = Math.max(absolute[i].getX(), absolute[n].getX());
            lineRanges[m+2] = Math.min(absolute[i].getY(), absolute[n].getY());
            lineRanges[m+3] = Math.max(absolute[i].getY(), absolute[n].getY());
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

    private boolean intersects(float x, float y, int index){
        int m = index*4;
        Position intersection = intersection(0, slopes[index], x, y, absolute[index].getX(), absolute[index].getY());
        return (Float.isInfinite(slopes[index])?intersection.getY()>lineRanges[m+2]&&intersection.getY()<lineRanges[m+3]:
                intersection.getX()>lineRanges[m]&&intersection.getX()<lineRanges[m+1])&&intersection.getX()>x;
    }

    private boolean inRange(float min, float max, float val){
        return val > min && val < max;
    }

    public Position[] getAbsolute() {
        if(changed)
            update();
        return absolute;
    }

    public float[] getSlopes(){
        if(changed)
            update();
        return slopes;
    }

    public float[] getLineRanges(){
        if(changed)
            update();
        return lineRanges;
    }


}
