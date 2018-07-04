package circle_fighter.game.object.objects;

import circle_fighter.engine.Game;
import circle_fighter.engine.KeyBindManager;
import circle_fighter.file.DataStorage;
import circle_fighter.functionaliy.Savable;
import circle_fighter.functionaliy.UserInputListener;
import circle_fighter.game.object.GameObject;
import circle_fighter.game.object.bounds.Bound;
import circle_fighter.game.object.bounds.render_base.CircularBase;
import circle_fighter.game.object.functionality.Damageable;
import circle_fighter.game.object.functionality.Damaging;
import circle_fighter.game.object.implementations.CharacterObject;
import circle_fighter.game.object.implementations.DamageableObject;
import circle_fighter.game.object.implementations.RenderableObject;
import circle_fighter.game.object.position.Position;
import circle_fighter.game.object.position.UpdatingPosition;
import circle_fighter.game.object.position.Vector;
import circle_fighter.game.object.position.movement.VelAngAccMovement;
import circle_fighter.game.object.turret.BasicTurret;
import circle_fighter.game.object.turret.TurretManager;
import circle_fighter.game.object.wrapper.Health;
import circle_fighter.game.plane.PlayerPlane;
import circle_fighter.gfx.color.SolidColor;
import circle_fighter.user.User;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

@CharacterObject
@DamageableObject
@RenderableObject
public class Player extends GameObject implements UserInputListener, Damageable {
    private static final float RADIUS = 25;
    private Health health;
    private TurretManager turrets;
    private CircularBase base;
    private KeyBindManager keyBinds;


    public Player(UpdatingPosition position, PlayerPlane plane) {
        super(plane, BoundExitAction.BOUND, new VelAngAccMovement(position, new Vector(0, 0, 0), 0.1f, 3, (float)Math.toRadians(.05), (float)Math.toRadians(3)), 0);
        this.keyBinds = plane.getKeyBinds();
        health = new Health(5, position, 50, 10, -50, 1, new SolidColor(0, 128, 0), new SolidColor(0, 255, 0));
        turrets = new TurretManager(this, new BasicTurret(this));
        base = new CircularBase(position, RADIUS, new SolidColor(255, 0, 0), new SolidColor(255, 0, 0));
    }

    public Player(UpdatingPosition position, PlayerPlane plane, User user){
        super(plane, BoundExitAction.BOUND, new VelAngAccMovement(position, new Vector(0, 0, 0), user.getMovement()), 0);
        keyBinds = plane.getKeyBinds();
        health = new Health(this.position, user.getHealth());
        turrets = new TurretManager(user.getTurrets(), this);
        base = new CircularBase(this.position, user.getBase());
    }

    @Override
    public Bound getBound() {
        return base;
    }

    @Override
    public void render(Graphics2D g) {
        base.render(g);
        turrets.render(g);
        health.render(g);
    }

    @Override
    public void tick() {
        health.tick();
        turrets.tick();
        base.tick();
        turrets.setTarget(new Position(Game.getInstance().mouseLocation()).absPosition());
        if(health.get()<=0)
            despawn();
        super.tick();
    }

    @Override
    public void keyPressed(int k) {
        VelAngAccMovement movement = (VelAngAccMovement)this.movement;
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

        }
    }

    @Override
    public void keyReleased(int k) {
        VelAngAccMovement movement = (VelAngAccMovement)this.movement;
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
            health.damage(damagingObject.damage(), damagingObject.invulnerabilityTime());
            return true;
        }
        return false;
    }
}