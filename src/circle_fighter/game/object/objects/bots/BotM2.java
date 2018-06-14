package circle_fighter.game.object.objects.bots;

import circle_fighter.color.SolidColor;
import circle_fighter.game.object.GameObject;
import circle_fighter.game.object.bounds.Bound;
import circle_fighter.game.object.functionality.Damageable;
import circle_fighter.game.object.functionality.Damaging;
import circle_fighter.game.object.implementations.CharacterObject;
import circle_fighter.game.object.implementations.DamageableObject;
import circle_fighter.game.object.implementations.DamagingObject;
import circle_fighter.game.object.implementations.RenderableObject;
import circle_fighter.game.object.position.Position;
import circle_fighter.game.object.position.Vector;
import circle_fighter.game.object.position.OmniDirectionalMovement;
import circle_fighter.game.object.bounds.renderBase.CircularBase;
import circle_fighter.game.object.wrapper.Health;
import circle_fighter.game.plane.PlayerPlane;

import java.awt.*;

@DamageableObject
@DamagingObject
@RenderableObject
@CharacterObject
public class BotM2 extends GameObject implements Damaging, Damageable{
    private static final float RADIUS = 12;

    private OmniDirectionalMovement movement;
    private PlayerPlane plane;
    private Health health;
    private CircularBase base;

    public BotM2(Position position, PlayerPlane plane, int team) {
        super(position, plane, BoundExitAction.BOUND, team);
        vector = new Vector(0, 0, 0);
        movement = new OmniDirectionalMovement(position, vector, 0.1, 1.5);
        movement.setFront(true);
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
        movement.tick();
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
    public float damage() {
        return 2;
    }

    @Override
    public long invulnerabilityTime() {
        return 1000;
    }
}
