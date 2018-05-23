package circle_fighter.game.object.objects.turret;

import circle_fighter.functionaliy.UserInputListener;
import circle_fighter.game.object.bounds.Bound;
import circle_fighter.game.object.bounds.CircularBound;
import circle_fighter.game.object.functionality.Damageable;
import circle_fighter.game.object.functionality.Damaging;
import circle_fighter.game.object.position.Position;
import circle_fighter.game.object.properties.DamageableObject;
import circle_fighter.game.object.properties.RenderableObject;
import circle_fighter.game.plane.Plane;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

@DamageableObject
@RenderableObject
public class Player extends TurretGameObject implements UserInputListener, Damageable {
    private static final double RADIUS = 25;

    private CircularBound bound;
    public Player(Position position, Plane plane) {
        super(position, 0.1, 0.05*Math.PI/180, 3, 3*Math.PI/180, 10, 0, plane);
        bound = new CircularBound(position, RADIUS);
    }

    @Override
    public Bound getBound() {
        return bound;
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
                mainTurret.setFiring(true);
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
                mainTurret.setFiring(false);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public boolean damage(Damaging damagingObject) {
        if(damagingObject.getBound().intersects(bound)&&damagingObject.getTeam()!=getTeam()){
            health-=damagingObject.damage();
            return true;
        }
        return false;
    }
}