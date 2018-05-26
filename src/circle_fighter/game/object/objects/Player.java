package circle_fighter.game.object.objects;

import circle_fighter.color.DynamicColor;
import circle_fighter.color.SolidColor;
import circle_fighter.functionaliy.UserInputListener;
import circle_fighter.game.object.GameObject;
import circle_fighter.game.object.bounds.Bound;
import circle_fighter.game.object.bounds.CircularBound;
import circle_fighter.game.object.functionality.Damageable;
import circle_fighter.game.object.functionality.Damaging;
import circle_fighter.game.object.position.Position;
import circle_fighter.game.object.implementations.DamageableObject;
import circle_fighter.game.object.implementations.RenderableObject;
import circle_fighter.game.object.position.Vector;
import circle_fighter.game.object.position.VelAngAccMovement;
import circle_fighter.game.object.wrapper.Turret;
import circle_fighter.game.object.wrapper.Health;
import circle_fighter.game.plane.Plane;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

@DamageableObject
@RenderableObject
public class Player extends GameObject implements UserInputListener, Damageable {
    private static final double RADIUS = 25;
    private CircularBound bound;
    private Health health;
    private VelAngAccMovement movement;
    private Vector vector;
    private Turret mainTurret;
    private DynamicColor color;

    public Player(Position position, Plane plane) {
        super(position, plane, BoundExitAction.BOUND, 0);
        vector = new Vector(0, 0, 0);
        movement = new VelAngAccMovement(position, vector, 0.1, 3, Math.toRadians(0.05), Math.toRadians(3));
        bound = new CircularBound(position, RADIUS);
        health = new Health(10, position, 50, 10, -50, 2000, new SolidColor(0, 128, 0), new SolidColor(0, 255, 0));
        mainTurret = new Turret(this, Math.PI/9, 40, 5, 10);
        color = new SolidColor(255, 0, 0);
    }

    @Override
    public Bound getBound() {
        return bound;
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillOval((int)position.getScrX()-25, (int)position.getScrY()-25, 50, 50);
        g.setColor(color.get());
        g.fillOval((int)position.getScrX()-23, (int)position.getScrY()-23, 46, 46);
        mainTurret.render(g);
        health.render(g);
    }

    @Override
    public void tick() {
        health.tick();
        mainTurret.tick();
        movement.tick();
        if(health.get()<=0)
            despawn();
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
    public void mouseScrolled(MouseWheelEvent e) {

    }

    @Override
    public boolean damage(Damaging damagingObject) {
        if(damagingObject.getBound().intersects(bound)&&damagingObject.getTeam()!=getTeam()){
            health.damage(damagingObject.damage());
            return true;
        }
        return false;
    }
}