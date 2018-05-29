package circle_fighter.game.object.bounds;

import circle_fighter.game.object.position.Position;

import java.awt.*;
import java.util.Arrays;

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
        Position[] vertices = bound.getVertices();
        return inside(vertices[0])||inside(vertices[1])||inside(vertices[2]);
    }

    private boolean inside(Position position){
        Position point = position.clone().move(position, false);
        Position[] vertices = getVertices();
        double[] slopes = generateSlopes(vertices);
        boolean s1 = onCorrectSide(onCorrectSide(true, slopes[0], vertices[0], vertices[2]), slopes[0], vertices[0], point),
                s2 = onCorrectSide(onCorrectSide(true, slopes[1], vertices[1], vertices[0]), slopes[1], vertices[1], point),
                s3 = onCorrectSide(onCorrectSide(true, slopes[2], vertices[2], vertices[1]), slopes[2], vertices[2], point);
        return s1&&s2&&s3;
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
        System.out.println("Base: " + basePoint.toString());
        System.out.println("Comparison: " + point.toString());
        System.out.println("Slope: " + slope);
        System.out.println("Assess Positive: " + positive);
        if(Double.isInfinite(slope)) {
            return positive ? point.getX() < basePoint.getX() : point.getX() > basePoint.getX();
        }
        if(positive) {
            boolean out = point.getY() > (point.getX() - basePoint.getX()) * slope + basePoint.getY();
            System.out.println("Positive: " + out + "\n_______________________");
            return out;
        }
        boolean out = point.getY()<(point.getX()-basePoint.getX())*slope+basePoint.getY();
        System.out.println("Positive: " + !out + "\n_______________________");
        return out;
    }
}