package circle_fighter.engine;

import circle_fighter.functionaliy.Renderable;
import circle_fighter.functionaliy.Updatable;
import circle_fighter.game.object.bounds.PointBound;
import circle_fighter.game.object.bounds.TriangularBound;
import circle_fighter.game.object.position.Position;

import java.awt.*;

public class Test implements Updatable, Renderable {
    private TriangularBound bound;
    private Polygon triangle;
    private boolean intersect;

    public Test(){
        Position position = new Position(0, -100);
        Position p1 = new Position(0, 0),
                p2 = new Position(Game.getInstance().getGameWidth()/2, Game.getInstance().getGameHeight()),
                p3 = new Position(Game.getInstance().getGameWidth(), 0);
        bound = new TriangularBound(p1, p2, p3, position);
        triangle = new Polygon(new int[]{
                (int) (p1.getX()+position.getX()), (int) (p2.getX()+position.getX()), (int) (p3.getX()+position.getX())
        }, new int[]{
                (int) (p1.getY()+position.getY()), (int) (p2.getY()+position.getY()), (int) (p3.getY()+position.getY())
        }, 3);
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(intersect?Color.BLACK:Color.WHITE);
        g.fill(triangle);
    }

    @Override
    public void tick() {
        intersect = bound.intersects(new PointBound(new Position(Game.getInstance().mouseLocation())));
    }
}