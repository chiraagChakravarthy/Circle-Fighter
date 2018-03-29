package game.base;

import background.Background;
import game.base.object.GameObject;
import game.base.object.ObjectManager;
import interfaces.Renderable;
import interfaces.Updatable;
import interfaces.UserInputListener;

import java.awt.*;

public abstract class Plane implements Updatable, Renderable, UserInputListener {
    protected Background background;
    protected ObjectManager manager;
    public Plane(){
        background = new Background.PlainBackground();
        manager = new ObjectManager();
    }

    @Override
    public void tick() {
        background.tick();
        manager.tick();
    }

    @Override
    public void render(Graphics2D g) {
        background.render(g);
        manager.render(g);
    }

    public ObjectManager getManager() {
        return manager;
    }

    public abstract boolean inBounds(GameObject object);
}
