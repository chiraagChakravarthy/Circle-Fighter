package circle_fighter.game.object.objects.turret;

import circle_fighter.game.object.position.UpdatingPosition;
import circle_fighter.game.object.position.Vector;
import circle_fighter.game.object.turret.BasicTurret;
import circle_fighter.game.object.turret.TurretManager;
import circle_fighter.gfx.color.SolidColor;
import circle_fighter.game.object.GameObject;
import circle_fighter.game.object.bounds.Bound;
import circle_fighter.game.object.functionality.Damageable;
import circle_fighter.game.object.functionality.Damaging;
import circle_fighter.game.object.implementations.CharacterObject;
import circle_fighter.game.object.implementations.DamageableObject;
import circle_fighter.game.object.implementations.RenderableObject;
import circle_fighter.game.object.objects.Player;
import circle_fighter.game.object.position.movement.VelAngAccMovement;
import circle_fighter.game.object.bounds.render_base.CircularBase;
import circle_fighter.game.object.wrapper.Health;
import circle_fighter.game.object.turret.Turret;
import circle_fighter.game.plane.PlayerPlane;

import java.awt.*;

@RenderableObject
@DamageableObject
@CharacterObject
public class TurretBotM1 extends GameObject implements Damageable {
    private static final float RADIUS = 15;

    private TurretManager turrets;
    private CircularBase base;
    private Health health;
    private PlayerPlane plane;

    public TurretBotM1(UpdatingPosition position, PlayerPlane plane, int team) {
        super(plane, BoundExitAction.BOUND, new VelAngAccMovement(position, new Vector(0, 0, 0), .05f, 1, (float)Math.toRadians(.01), (float)Math.toRadians(1)), team);
        this.plane = plane;
        turrets = new TurretManager(this, new BasicTurret(this));
        health = new Health(4, this.position, 50, 10, 50, 0, new SolidColor(128, 0, 0), new SolidColor(255, 0, 0));
        base = new CircularBase(position, RADIUS, new SolidColor(255, 0, 0), new SolidColor(255, 140, 0));
    }

    @Override
    public void tick() {
        super.tick();
        //turrets.setTarget(plane.getPlayer().getPosition());
        Player player = plane.getPlayer();
        double distance = player.getPosition().distance(position),
                relAngle = (position.angleTo(player.getPosition())-position.getRotation())%(2*Math.PI);
        VelAngAccMovement movement = (VelAngAccMovement)this.movement;
        if(distance<=300){
            if(relAngle<Math.PI/2|relAngle>3*Math.PI/2){
                movement.setFront(false).setBack(true);
            }
            else {
                movement.setBack(true).setFront(false);
            }
        }
        else {
            movement.setFront(false).setBack(false);
        }

        if(relAngle<0){
            movement.setLeft(true).setRight(false);
        }
        else {
            movement.setRight(true).setLeft(false);
        }

        turrets.tick();
        health.tick();
        if(health.get()<=0)
            despawn();
    }

    @Override
    public void render(Graphics2D g) {
        base.render(g);
        turrets.render(g);
        health.render(g);
    }

    @Override
    public Bound getBound() {
        return base;
    }

    @Override
    public boolean damage(Damaging damagingObject) {
        if(damagingObject.getBound().intersects(base)&&damagingObject.getTeam()!=getTeam()){
            health.set(health.get()-damagingObject.damage());
            return true;
        }
        return false;
    }
}
