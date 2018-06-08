package circle_fighter.engine;

import circle_fighter.color.Rainbow;
import circle_fighter.color.SolidColor;
import circle_fighter.functionaliy.Renderable;
import circle_fighter.functionaliy.Updatable;
import circle_fighter.functionaliy.UserInputListener;
import circle_fighter.game.object.bounds.CircularBound;
import circle_fighter.game.object.bounds.PointBound;
import circle_fighter.game.object.bounds.PolygonBound;
import circle_fighter.game.object.bounds.renderBase.PolygonBase;
import circle_fighter.game.object.position.Position;
import circle_fighter.game.object.position.UpdatingPosition;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class Test implements Updatable, Renderable, UserInputListener {
    private PolygonBase triangle;
    private CircularBound mouseBound;
    private Position mousePosition;
    private boolean intersect;

    public Test(){
        UpdatingPosition position = new UpdatingPosition(0, 0);
        Position p1 = new Position(0, 0),
                p2 = new Position(Game.getInstance().getGameWidth()/2, Game.getInstance().getGameHeight()),
                p3 = new Position(Game.getInstance().getGameWidth()/2, 0);
        triangle = new PolygonBase(new SolidColor(0, 0, 255), position, new Position[]{p1, p2, p3});
        mousePosition = new Position(0, 0);
        mouseBound = new CircularBound(mousePosition, 10);
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, Game.getInstance().getGameWidth(), Game.getInstance().getGameHeight());
        triangle.setColor(intersect?new Rainbow(1, 10):new SolidColor(0, 0, 0));
        triangle.render(g);
        g.setColor(Color.RED);
        g.fillOval((int)mousePosition.getX()-10, (int)mousePosition.getY()-10, 20, 20);
    }

    @Override
    public void tick() {
        Position mousePosition = new Position(Game.getInstance().mouseLocation());
        this.mousePosition.setX(mousePosition.getX());
        this.mousePosition.setY(mousePosition.getY());
        intersect = triangle.intersects(new PointBound(new Position(Game.getInstance().mouseLocation())));
        triangle.tick();
    }

    @Override
    public void keyPressed(int k) {

    }

    @Override
    public void keyReleased(int k) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseScrolled(MouseWheelEvent e) {

    }
}