package circle_fighter.game.object.objects.bots;

import circle_fighter.game.object.GameObject;
import circle_fighter.game.object.bounds.Bound;
import circle_fighter.game.object.bounds.render_base.CircularBase;
import circle_fighter.game.object.functionality.Damageable;
import circle_fighter.game.object.functionality.Damaging;
import circle_fighter.game.object.implementations.CharacterObject;
import circle_fighter.game.object.implementations.DamageableObject;
import circle_fighter.game.object.implementations.DamagingObject;
import circle_fighter.game.object.implementations.RenderableObject;
import circle_fighter.game.object.position.UpdatingPosition;
import circle_fighter.game.object.position.Vector;
import circle_fighter.game.object.position.movement.OmniDirectionalMovement;
import circle_fighter.game.object.wrapper.Health;
import circle_fighter.game.plane.PlayerPlane;
import circle_fighter.gfx.color.SolidColor;

import java.awt.*;

@DamageableObject
@DamagingObject
@RenderableObject
@CharacterObject
public class BotM2 extends GameObject implements Damaging, Damageable{
    private static final float RADIUS = 12;

    private PlayerPlane plane;
    private Health health;
    private CircularBase base;

    public BotM2(UpdatingPosition position, PlayerPlane plane, int team) {
        super(plane, BoundExitAction.BOUND, new OmniDirectionalMovement(position, new Vector(0, 0, 0), 0.1f, 1.5f), team);
        ((OmniDirectionalMovement)movement).setFront(true);
        health = new Health(2, position, 50, 10, 50, 0, new SolidColor(128, 0, 0), new SolidColor(255, 0, 0));
        base = new CircularBase(position, RADIUS, new SolidColor(255, 0, 0), new SolidColor(128, 0, 0));
        this.plane = plane;
    }

    @Override
    public void tick() {
        super.tick();
        position.setRotation(position.angleTo(plane.getPlayer().getPosition()));
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
    public boolean damage(Damaging damagingObject) {
        if(damagingObject.getBound().intersects(base)&&damagingObject.getTeam()!=getTeam()){
            health.set(health.get()-damagingObject.damage());
            return true;
        }
        return false;
    }

    @Override
    public float expOnDeath() {
        return 2;
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
