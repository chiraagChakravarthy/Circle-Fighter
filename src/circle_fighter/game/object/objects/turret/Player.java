package circle_fighter.game.object.objects.turret;

import circle_fighter.functionaliy.UserInputListener;
import circle_fighter.game.object.GameObject;
import circle_fighter.game.object.functionality.Damageable;
import circle_fighter.game.object.functionality.Damaging;
import circle_fighter.game.object.position.Position;
import circle_fighter.game.plane.Plane;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Area;

public class Player extends TurretGameObject implements UserInputListener, Damageable {
    private Rectangle bounds;
    public Player(Position position, Plane plane) {
        super(position, 0.1, 0.05*Math.PI/180, 3, 3*Math.PI/180, 10, 0, plane);
        bounds = new Rectangle((int)(position.getX()-25), (int) (position.getY()-25), 50, 50);
        plane.getObjectManager().getDamageableObjects().add(this);
    }

    @Override
    public Area getBounds() {
        return new Area(bounds);
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.RED);
        g.fillOval((int)position.getScrX()-25, (int)position.getScrY()-25, 50, 50);
        super.render(g);
    }

    @Override
    public void tick() {
        super.tick();
        bounds.setLocation((int)position.getX()-25, (int)position.getY()-25);
        position.setX(position.getX()-plane.getBounds().exceedsRightBy(this)+plane.getBounds().exceedsLeftBy(this));
        position.setY(position.getY()-plane.getBounds().exceedsBottomBy(this)+plane.getBounds().exceedsTopBy(this));
    }

    @Override
    public void keyPressed(int k) {
        switch (k){
            case KeyEvent.VK_W:
                movement.setFront(true);
                break;
            case KeyEvent.VK_S:
                movement.setBack(true);
                break;
            case KeyEvent.VK_A:
                movement.setLeft(true);
                break;
            case KeyEvent.VK_D:
                movement.setRight(true);
                break;
            case KeyEvent.VK_SPACE:
                mainCannon.setFiring(true);
                break;
        }
    }

    @Override
    public void keyReleased(int k) {
        switch (k){
            case KeyEvent.VK_W:
                movement.setFront(false);
                break;
            case KeyEvent.VK_S:
                movement.setBack(false);
                break;
            case KeyEvent.VK_A:
                movement.setLeft(false);
                break;
            case KeyEvent.VK_D:
                movement.setRight(false);
                break;
            case KeyEvent.VK_SPACE:
                mainCannon.setFiring(false);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public <T extends GameObject & Damaging> boolean damage(T damagingObject) {
        Area intersect = damagingObject.getBounds();
        intersect.intersect(this.getBounds());
        Rectangle intersectBounds = intersect.getBounds();
        if(intersectBounds.width==0||intersectBounds.height==0)
            return false;
        health-=damagingObject.damage();
        return true;
    }
}
