package circle_fighter.game.object.bounds;

import circle_fighter.file.DataStorage;
import circle_fighter.game.object.position.OnPositionChanged;
import circle_fighter.game.object.position.Position;
import circle_fighter.game.object.position.UpdatingPosition;

import java.awt.*;

public class PolygonBound extends Bound implements OnPositionChanged {
    protected Position[] absolute;
    protected Position[] relative;
    private float[] slopes;
    private boolean changed;
    private float[] lineRanges;
    private Rectangle outerBound;

    public PolygonBound(UpdatingPosition position, Position[] relative){
        super(position);
        position.addListener(this);
        this.relative = relative;
        absolute = new Position[relative.length];
        lineRanges = new float[absolute.length*4];
        slopes = new float[absolute.length];
    }

    public PolygonBound(UpdatingPosition position, DataStorage storage){
        super(position);
        position.addListener(this);
        int vertices = storage.get(0);
        relative = new Position[vertices];
        for (int i = 0; i < vertices; i++) {
            relative[i] = new Position();
            relative[i].hardLoad(storage.getSubStorage(i));
        }
    }

    //TODO finish this method
    @Override
    public boolean intersects(CircularBound bound) {
        if(changed)
            update();
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
            for (int j = 0; j < boundSlopes.length; j++) {
                int n = j*4;
                Position intersection = intersection(slopes[i], boundSlopes[j], absolute[i].getX(), absolute[i].getY(), boundAbsolute[j].getX(), boundAbsolute[j].getY());
                if(Float.isInfinite(boundSlopes[j])) {
                    if(intersection.getY() >= lineRanges[m+2]&&intersection.getY()>=boundRanges[n+2]&&intersection.getY()<=lineRanges[m+3]&&intersection.getY()<=boundRanges[n+3]) {
                        return true;
                    }
                }
                else if(intersection.getX() >= lineRanges[m]&&intersection.getX()>=boundRanges[n]&&intersection.getX()<=lineRanges[m+1]&&intersection.getX()<=boundRanges[n+1]) {

                    return true;
                }
            }
        }
        for(Position position : absolute){
            if(bound.inside(position)) {
                return true;
            }
        }
        for (Position position : boundAbsolute) {
            if (inside(position)) {
                return true;
            }
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
            slopes[i] = (int)absolute[i].getX()==(int)absolute[n].getX()?Float.POSITIVE_INFINITY:(absolute[i].getY()-absolute[n].getY())/(absolute[i].getX()-absolute[n].getX());
            lineRanges[m] = Math.min(absolute[i].getX(), absolute[n].getX());
            lineRanges[m+1] = Math.max(absolute[i].getX(), absolute[n].getX());
            lineRanges[m+2] = Math.min(absolute[i].getY(), absolute[n].getY());
            lineRanges[m+3] = Math.max(absolute[i].getY(), absolute[n].getY());
        }
        changed = false;
    }

    protected boolean inside(Position position){
        if(changed)
            update();
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

    private boolean intersects(float x, float y, int i){
        int m = i*4;
        Position intersection = intersection(0, slopes[i], x, y, absolute[i].getX(), absolute[i].getY());
        return (Float.isInfinite(slopes[i])?intersection.getY()>=lineRanges[m+2]&&intersection.getY()<=lineRanges[m+3]:
                intersection.getX()>=lineRanges[m]&&intersection.getX()<=lineRanges[m+1])&&intersection.getX()>=x;
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

    @Override
    public void save(DataStorage storage) {
        storage.set(0, relative.length);
        for (int i = 0; i < relative.length; i++) {
            relative[i].hardLoad(storage.getSubStorage(i));
        }
    }
}