package circle_fighter.engine;

import circle_fighter.functionaliy.Renderable;
import circle_fighter.functionaliy.Updatable;
import circle_fighter.game.object.bounds.CircularBound;
import circle_fighter.game.object.bounds.PointBound;
import circle_fighter.game.object.bounds.PolygonBound;
import circle_fighter.game.object.position.Position;
import circle_fighter.game.object.position.UpdatingPosition;

import java.awt.*;

public class Test implements Updatable, Renderable {
    private PolygonBound bound;
    private CircularBound mouseBound;
    private Polygon triangle;
    private Position mousePosition;
    private boolean intersect;

    public Test(){
        UpdatingPosition position = new UpdatingPosition(100, 100);
        Position p1 = new Position(0, 0),
                p2 = new Position(Game.getInstance().getGameWidth()/2, Game.getInstance().getGameHeight()),
                p3 = new Position(Game.getInstance().getGameWidth()/2, 0);
        bound = new PolygonBound(position, new Position[]{p1, p2, p3});
        triangle = new Polygon(new int[]{
                (int) (p1.getX()+position.getX()), (int) (p2.getX()+position.getX()), (int) (p3.getX()+position.getX())
        }, new int[]{
                (int) (p1.getY()+position.getY()), (int) (p2.getY()+position.getY()), (int) (p3.getY()+position.getY())
        }, 3);
        mousePosition = new Position(0, 0);
        mouseBound = new CircularBound(mousePosition, 10);
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, Game.getInstance().getGameWidth(), Game.getInstance().getGameHeight());
        g.setColor(intersect?Color.BLACK:Color.BLUE);
        g.fill(triangle);
        g.setColor(Color.RED);
        g.fillOval((int)mousePosition.getX()-10, (int)mousePosition.getY()-10, 20, 20);
    }

    @Override
    public void tick() {
        Position mousePosition = new Position(Game.getInstance().mouseLocation());
        this.mousePosition.setX(mousePosition.getX());
        this.mousePosition.setY(mousePosition.getY());
        intersect = bound.intersects(new PointBound(new Position(Game.getInstance().mouseLocation())));
    }
}