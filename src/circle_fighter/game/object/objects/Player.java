package circle_fighter.game.object.objects;

import circle_fighter.color.SolidColor;
import circle_fighter.engine.KeyBindManager;
import circle_fighter.functionaliy.UserInputListener;
import circle_fighter.game.object.GameObject;
import circle_fighter.game.object.bounds.Bound;
import circle_fighter.game.object.bounds.CircularBound;
import circle_fighter.game.object.functionality.Damageable;
import circle_fighter.game.object.functionality.Damaging;
import circle_fighter.game.object.implementations.CharacterObject;
import circle_fighter.game.object.implementations.DamageableObject;
import circle_fighter.game.object.implementations.RenderableObject;
import circle_fighter.game.object.position.Position;
import circle_fighter.game.object.position.Vector;
import circle_fighter.game.object.position.VelAngAccMovement;
import circle_fighter.game.object.bounds.renderBase.CircularBase;
import circle_fighter.game.object.wrapper.Health;
import circle_fighter.game.object.wrapper.Turret;
import circle_fighter.game.plane.Plane;
import circle_fighter.game.plane.PlayerPlane;
import circle_fighter.user.User;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

@CharacterObject
@DamageableObject
@RenderableObject
public class Player extends GameObject implements UserInputListener, Damageable {
    private static final double RADIUS = 25;
    private Health health;
    private VelAngAccMovement movement;
    private Turret mainTurret;
    private CircularBase base;
    private KeyBindManager keyBinds;

    public Player(Position position, PlayerPlane plane, User user) {
        super(position, plane, BoundExitAction.BOUND, 0);
        this.keyBinds = plane.getKeyBinds();
        vector = new Vector(0, 0, 0);
        movement = new VelAngAccMovement(position, vector, 0.1, 3, Math.toRadians(0.05), Math.toRadians(3));
        health = new Health(5, position, 50, 10, -50, 1000, new SolidColor(0, 128, 0), new SolidColor(0, 255, 0));
        mainTurret = new Turret(this, Math.PI/9, 40, 5, 10, new SolidColor(0, 0, 255));
        base = new CircularBase(position, RADIUS, new SolidColor(255, 0, 0), new SolidColor(255, 0, 0));
    }

    @Override
    public Bound getBound() {
        return base;
    }

    @Override
    public void render(Graphics2D g) {
        base.render(g);
        mainTurret.render(g);
        health.render(g);
    }

    @Override
    public void tick() {
        health.tick();
        mainTurret.tick();
        movement.tick();
        base.tick();
        if(health.get()<=0)
            despawn();
        super.tick();
    }

    @Override
    public void keyPressed(int k) {
        if(k==keyBinds.get(KeyBindManager.PLAYER_FORWARD)){
            movement.setFront(true);
        }
        else if(k==keyBinds.get(KeyBindManager.PLAYER_BACKWARD)){
            movement.setBack(true);
        }
        else if(k==keyBinds.get(KeyBindManager.ROTATE_CLOCKWISE)){
            movement.setRight(true);
        }
        else if(k==keyBinds.get(KeyBindManager.ROTATE_COUNTER_CLOCKWISE)){
            movement.setLeft(true);
        }
        else if(k==keyBinds.get(KeyBindManager.SHOOT)){
            mainTurret.setFiring(true);
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
        if(damagingObject.getBound().intersects(base)&&damagingObject.getTeam()!=getTeam()){
            health.damage(damagingObject.damage());
            return true;
        }
        return false;
    }
}