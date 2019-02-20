package circle_fighter.game.object.objects;

import circle_fighter.engine.Game;
import circle_fighter.engine.KeyBindManager;
import circle_fighter.engine.notification.NotificationManager;
import circle_fighter.functionaliy.UserInputListener;
import circle_fighter.game.object.GameObject;
import circle_fighter.game.object.bounds.Bound;
import circle_fighter.game.object.bounds.render_base.CircularBase;
import circle_fighter.game.object.functionality.Damageable;
import circle_fighter.game.object.functionality.Damaging;
import circle_fighter.game.object.functionality.DirectlyDamaging;
import circle_fighter.game.object.implementations.CharacterObject;
import circle_fighter.game.object.implementations.DamageableObject;
import circle_fighter.game.object.implementations.RenderableObject;
import circle_fighter.game.object.position.Position;
import circle_fighter.game.object.position.UpdatingPosition;
import circle_fighter.game.object.position.Vector;
import circle_fighter.game.object.position.movement.VelAngAccMovement;
import circle_fighter.game.object.turret.TurretManager;
import circle_fighter.game.object.wrapper.Health;
import circle_fighter.game.plane.PlayerPlane;
import circle_fighter.user.User;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

@CharacterObject
@DamageableObject
@RenderableObject
public class Player extends GameObject implements UserInputListener, Damageable, Damaging {
    private Health health;
    private TurretManager turrets;
    private CircularBase base;
    private KeyBindManager keyBinds;
    private User user;
    public Player(UpdatingPosition position, PlayerPlane plane, User user){
        super(plane, BoundExitAction.BOUND, new VelAngAccMovement(position, new Vector(0, 0, 0), user.getMovement()), 0);
        keyBinds = plane.getKeyBinds();
        health = new Health(this.position, user.getHealth());
        turrets = new TurretManager(user.getTurrets(), this, 25);
        base = new CircularBase(this.position, user.getBase());
        this.user = user;
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
            turrets.trigger();
        }
    }

    @Override
    public void keyReleased(int k) {
        VelAngAccMovement movement = (VelAngAccMovement)this.movement;
        if(k==keyBinds.get(KeyBindManager.PLAYER_FORWARD)){
            movement.setFront(false);
        }
        else if(k==keyBinds.get(KeyBindManager.PLAYER_BACKWARD)){
            movement.setBack(false);
        }
        else if(k==keyBinds.get(KeyBindManager.ROTATE_CLOCKWISE)){
            movement.setRight(false);
        }
        else if(k==keyBinds.get(KeyBindManager.ROTATE_COUNTER_CLOCKWISE)){
            movement.setLeft(false);
        }
        else if(k==keyBinds.get(KeyBindManager.SHOOT)){
            turrets.stop();
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
    public boolean damage(DirectlyDamaging damagingObject) {
        if(damagingObject.getBound().intersects(base)&&damagingObject.getTeam()!=getTeam()){
            health.damage(damagingObject.damage(), damagingObject.invulnerabilityTime());
            if(health.get()<=0){
                despawn();
                damagingObject.onKill(this);
            }
            return true;
        }
        return false;
    }

    @Override
    public float expOnDeath() {
        return 0;
    }


    @Override
    public void onKill(Damageable damageable) {
        if(user.getLevel().addExp(damageable.expOnDeath())){
            NotificationManager.addNotification("Level Up to " + user.getLevel().getLevel() + "! +1 Upgrade Point.");
        }
    }
}