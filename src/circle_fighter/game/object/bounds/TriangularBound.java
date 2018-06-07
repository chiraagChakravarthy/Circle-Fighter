package circle_fighter.game.object.bounds;

import circle_fighter.game.object.position.Position;

import java.awt.*;

public class TriangularBound extends Bound {
    private Position position;
    private Position[] points;

    public TriangularBound(Position p1, Position p2, Position p3, Position position){
        points = new Position[]{
                p1, p2, p3
        };
        this.position = position;
    }

    @Override
    public boolean intersects(CircularBound bound) {
        Position[] vertices = getVertices();
        return false;
    }

    @Override
    public boolean intersects(LineSegmentBound bound) {
        return false;
    }

    @Override
    public boolean intersects(PointBound bound) {
        return inside(bound.getPosition());
    }

    @Override
    public boolean intersects(TriangularBound bound) {
        Position[] boundVertices = bound.getVertices();
        if(inside(boundVertices[0])||inside(boundVertices[1])||inside(boundVertices[2]))
            return true;
        Position[] vertices = getVertices();
        if(bound.inside(vertices[0])||bound.inside(vertices[1])||bound.inside(vertices[2]))
            return true;

        return false;
    }

    /*
    //TODO
        Listnening Position
            * Accepting listeners for changes in position or rotation
            * Triangle bounds will only accept listener positions
            * Only when called upon to do anything does it update its stuff
     */

    private boolean inside(Position position){
        Position point = position.clone();
        Position[] vertices = getVertices();
        double[] slopes = generateSlopes(vertices);
        return onCorrectSide(onCorrectSide(true, slopes[0], vertices[0], vertices[2]), slopes[0], vertices[0], point)&&
                onCorrectSide(onCorrectSide(true, slopes[1], vertices[1], vertices[0]), slopes[1], vertices[1], point)&&
                onCorrectSide(onCorrectSide(true, slopes[2], vertices[2], vertices[1]), slopes[2], vertices[2], point);
    }

    @Override
    public Rectangle outerBounds() {
        return null;
    }

    public Position[] getVertices(){
        return new Position[]{
                new Position(position).move(points[0], true),
                new Position(position).move(points[1], true),
                new Position(position).move(points[2], true)
        };
    }

    private double[] generateSlopes(Position[] absolutes){
        return new double[]{
                (absolutes[0].getY()-absolutes[1].getY())/(absolutes[0].getX()-absolutes[1].getX()),
                (absolutes[1].getY()-absolutes[2].getY())/(absolutes[1].getX()-absolutes[2].getX()),
                (absolutes[2].getY()-absolutes[0].getY())/(absolutes[2].getX()-absolutes[0].getX())
        };
    }

    private boolean onCorrectSide(boolean positive, double slope, Position basePoint, Position point){
        if(Double.isInfinite(slope))
            return positive ? point.getX() < basePoint.getX() : point.getX() > basePoint.getX();
        if(positive)
            return point.getY() > (point.getX() - basePoint.getX()) * slope + basePoint.getY();
        return point.getY()<(point.getX()-basePoint.getX())*slope+basePoint.getY();
    }
}