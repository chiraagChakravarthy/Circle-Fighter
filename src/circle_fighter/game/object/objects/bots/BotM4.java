package circle_fighter.game.object.objects.bots;

import circle_fighter.game.object.functionality.DirectlyDamaging;
import circle_fighter.game.object.position.UpdatingPosition;
import circle_fighter.gfx.color.SolidColor;
import circle_fighter.game.object.GameObject;
import circle_fighter.game.object.bounds.Bound;
import circle_fighter.game.object.functionality.Damageable;
import circle_fighter.game.object.functionality.Damaging;
import circle_fighter.game.object.implementations.CharacterObject;
import circle_fighter.game.object.implementations.DamageableObject;
import circle_fighter.game.object.implementations.DamagingObject;
import circle_fighter.game.object.implementations.RenderableObject;
import circle_fighter.game.object.position.movement.MultiDirectionalMovement;
import circle_fighter.game.object.position.Position;
import circle_fighter.game.object.position.Vector;
import circle_fighter.game.object.bounds.render_base.CircularBase;
import circle_fighter.game.object.wrapper.Health;
import circle_fighter.game.plane.PlayerPlane;

import java.awt.*;

@DamageableObject
@DamagingObject
@RenderableObject
@CharacterObject
public class BotM4 extends GameObject implements DirectlyDamaging, Damageable{
    private static final float RADIUS = 19;

    private PlayerPlane plane;
    private Health health;
    private CircularBase base;
    private MultiDirectionalMovement movement;

    public BotM4(UpdatingPosition position, PlayerPlane plane, int team) {
        super(plane, BoundExitAction.BOUND, new MultiDirectionalMovement(position, new Vector(0, 0, 0), .03f, .01f, 100, 4), team);
        movement = (MultiDirectionalMovement)super.movement;
        health = new Health(10, 0, position, 50, 10, 50, 0, new SolidColor(128, 0, 0), new SolidColor(255, 0, 0));
        base = new CircularBase(position, RADIUS, new SolidColor(0, 0, 255), new SolidColor(0, 0, 128));
        this.plane = plane;
    }

    @Override
    public void tick() {
        super.tick();
        movement.clearDirections();
        Position playerPosition = plane.getPlayer().getPosition();
        double relX = position.getX()-playerPosition.getX(), relY = position.getY()-playerPosition.getY();
        movement.setDirection(relX<0?0:2, true);
        movement.setDirection(relY<0?1:3, true);
        health.tick();
        base.tick();
        if(health.get()<=0)
            despawn();
    }

    @Override
    public void render(Graphics2D g) {
        base.render(g);
        health.render(g);
    }

    @Override
    public Bound getBound() {
        return base;
    }

    @Override
    public boolean damage(DirectlyDamaging damagingObject) {
        if(health.get()>0) {
            if (damagingObject.getBound().intersects(base) && damagingObject.getTeam() != getTeam()) {
                health.set(health.get() - damagingObject.damage());
                if (health.get() <= 0) {
                    despawn();
                    damagingObject.onKill(this);
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public float expOnDeath() {
        return 20;
    }

    @Override
    public float damage() {
        return 2;
    }

    @Override
    public long invulnerabilityTime() {
        return 1000;
    }

    @Override
    public void onKill(Damageable damageable) {
        //ignored
    }
}
