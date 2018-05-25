package circle_fighter.game.plane;

import circle_fighter.background.Background;
import circle_fighter.color.Rainbow;
import circle_fighter.color.SolidColor;
import circle_fighter.game.object.ObjectManager;
import circle_fighter.game.object.position.Position;
import circle_fighter.game.plane.bounds.PlaneBounds;
import circle_fighter.functionaliy.Renderable;
import circle_fighter.functionaliy.Updatable;
import circle_fighter.functionaliy.UserInputListener;

import java.awt.*;
import java.awt.event.KeyEvent;

public abstract class Plane implements Updatable, Renderable, UserInputListener {
    protected final Background.CheckeredBackground background;
    protected final ObjectManager objectManager;
    protected final PlaneBounds bounds;

    public Plane(PlaneBounds bounds){
        background = new Background.CheckeredBackground(new Rainbow(0.5, 1), new SolidColor(128, 128, 128), 100, new Position(0, 0));
        objectManager = new ObjectManager();
        this.bounds = bounds;
    }

    @Override
    public void tick() {
        background.tick();
        objectManager.tick();
        background.getPosition().setX(Position.getxOffset());
        background.getPosition().setY(Position.getyOffset());
    }

    @Override
    public void render(Graphics2D g) {
        background.render(g);
        objectManager.render(g);
    }

    public ObjectManager getObjectManager() {
        return objectManager;
    }

    public PlaneBounds getBounds() {
        return bounds;
    }

    public void reset(){
        objectManager.clearAll();
    }
}
