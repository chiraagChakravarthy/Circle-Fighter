package circle_fighter.game.plane;

import circle_fighter.background.Background;
import circle_fighter.game.object.ObjectManager;
import circle_fighter.game.plane.bounds.PlaneBounds;
import circle_fighter.functionaliy.Renderable;
import circle_fighter.functionaliy.Updatable;
import circle_fighter.functionaliy.UserInputListener;

import java.awt.*;

public abstract class Plane implements Updatable, Renderable, UserInputListener {
    protected final Background background;
    protected final ObjectManager objectManager;
    protected final PlaneBounds bounds;

    public Plane(PlaneBounds bounds){
        background = new Background.PlainBackground();
        objectManager = new ObjectManager();
        this.bounds = bounds;
    }

    @Override
    public void tick() {
        background.tick();
        objectManager.tick();
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
}
