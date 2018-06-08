package circle_fighter.game.object.objects.turret;

import circle_fighter.color.SolidColor;
import circle_fighter.game.object.GameObject;
import circle_fighter.game.object.bounds.Bound;
import circle_fighter.game.object.bounds.CircularBound;
import circle_fighter.game.object.functionality.Damageable;
import circle_fighter.game.object.functionality.Damaging;
import circle_fighter.game.object.implementations.CharacterObject;
import circle_fighter.game.object.implementations.DamageableObject;
import circle_fighter.game.object.implementations.RenderableObject;
import circle_fighter.game.object.objects.Player;
import circle_fighter.game.object.position.Position;
import circle_fighter.game.object.position.VelAngAccMovement;
import circle_fighter.game.object.bounds.renderBase.CircularBase;
import circle_fighter.game.object.wrapper.Health;
import circle_fighter.game.object.wrapper.Turret;
import circle_fighter.game.plane.PlayerPlane;

import java.awt.*;

@RenderableObject
@DamageableObject
@CharacterObject
public class TurretBotM1 extends GameObject implements Damageable {
    private static final double RADIUS = 15;

    private Turret turret;
    private CircularBase base;
    private VelAngAccMovement movement;
    private Health health;
    private PlayerPlane plane;

    public TurretBotM1(Position position, PlayerPlane plane, int team) {
        super(position, plane, BoundExitAction.BOUND, team);
        this.plane = plane;
        movement = new VelAngAccMovement(position, vector, 0.05, 1, Math.toRadians(0.01), Math.toRadians(1));
        turret = new Turret(this, 0, 25, 4, 3, new SolidColor(0, 0, 0));
        health = new Health(4, this.position, 50, 10, 50, 0, new SolidColor(128, 0, 0), new SolidColor(255, 0, 0));
        base = new CircularBase(position, RADIUS, new SolidColor(255, 0, 0), new SolidColor(255, 140, 0));
        turret.setFiring(true);
    }

    @Override
    public void tick() {
        super.tick();
        movement.tick();

        Player player = plane.getPlayer();
        double distance = player.getPosition().distance(position),
                relAngle = (position.angleTo(player.getPosition())-position.getRotation())%(2*Math.PI);
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

        turret.tick();
        if(health.get()<=0)
            despawn();
    }

    @Override
    public void render(Graphics2D g) {
        base.render(g);
        turret.render(g);
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
