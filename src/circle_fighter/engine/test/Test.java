package circle_fighter.engine.test;

import circle_fighter.engine.Game;
import circle_fighter.functionaliy.Renderable;
import circle_fighter.functionaliy.Updatable;
import circle_fighter.functionaliy.UserInputListener;
import circle_fighter.game.object.bounds.render_base.CircularBase;
import circle_fighter.game.object.bounds.render_base.PolygonBase;
import circle_fighter.game.object.position.Position;
import circle_fighter.game.object.position.UpdatingPosition;
import circle_fighter.gfx.color.Rainbow;
import circle_fighter.gfx.color.SolidColor;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class Test implements Updatable, Renderable, UserInputListener {
    private PolygonBase base;
    private CircularBase mouse;
    public Test(){
        Position[] relative = new Position[3];
        float pi = (float)Math.PI;
        for (int i = 0; i < 3; i++) {
            float radians = i/3.0f*pi*2;
            relative[i] = Position.fromPolar(radians, 200);
        }
        base = new PolygonBase(new Rainbow(0.5f, 10), new UpdatingPosition(0, 0), relative);
        mouse = new CircularBase(new UpdatingPosition(0, 0), 50, new SolidColor(0, 0, 0), new SolidColor(0, 0, 0));
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, Game.getInstance().getGameWidth(), Game.getInstance().getGameHeight());
        mouse.render(g);
        base.render(g);
    }

    @Override
    public void tick() {
        UpdatingPosition.tick();
        base.tick();
        Point mouseLocation = Game.getInstance().mouseLocation();
        mouse.getCenterPoint().setX(mouseLocation.x-Game.getInstance().getGameWidth()/2);
        mouse.getCenterPoint().setY(mouseLocation.y-Game.getInstance().getGameHeight()/2);
        base.setColor(base.intersects(mouse)?new SolidColor(255, 0, 0):new SolidColor(0, 0, 0));
        base.getPosition().setRotation(base.getPosition().getRotation()+.01f);
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