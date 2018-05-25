package circle_fighter.level;

import circle_fighter.game.object.GameObject;
import circle_fighter.game.object.position.Position;
import circle_fighter.game.plane.PlayerPlane;
import circle_fighter.game.plane.bounds.BoundedBounds;

import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class LevelPlane extends PlayerPlane {
    public LevelPlane(int width, int height) {
        super(new Position(0, 0), new BoundedBounds(-width/2, -height/2, width, height));
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public void render(Graphics2D g) {
        super.render(g);
    }

    @Override
    public void keyPressed(int k) {
        super.keyPressed(k);
    }

    @Override
    public void keyReleased(int k) {
        super.keyReleased(k);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
    }

    protected <T extends GameObject, Damageable>void addEnemy(T enemy){
        objectManager.add(enemy);
    }
}
