package circle_fighter.game.object.objects.bots;

import circle_fighter.color.SolidColor;
import circle_fighter.game.object.GameObject;
import circle_fighter.game.object.bounds.Bound;
import circle_fighter.game.object.bounds.CircularBound;
import circle_fighter.game.object.functionality.Damageable;
import circle_fighter.game.object.functionality.Damaging;
import circle_fighter.game.object.implementations.DamageableObject;
import circle_fighter.game.object.implementations.DamagingObject;
import circle_fighter.game.object.implementations.CharacterObject;
import circle_fighter.game.object.implementations.RenderableObject;
import circle_fighter.game.object.position.Position;
import circle_fighter.game.object.position.Vector;
import circle_fighter.game.object.position.OmniDirectionalMovement;
import circle_fighter.game.object.wrapper.CircularBase;
import circle_fighter.game.object.wrapper.Health;
import circle_fighter.game.plane.PlayerPlane;

import java.awt.*;
@DamageableObject
@DamagingObject
@RenderableObject
@CharacterObject
public class BotM1 extends GameObject implements Damaging, Damageable{
    private static final double RADIUS = 10;

    private CircularBound bound;
    private OmniDirectionalMovement movement;
    private PlayerPlane plane;
    private Health health;
    private CircularBase base;

    public BotM1(Position position, PlayerPlane plane, int team) {
        super(position, plane, BoundExitAction.BOUND, team);
        bound = new CircularBound(position, RADIUS);
        vector = new Vector(0, 0, 0);
        movement = new OmniDirectionalMovement(position, vector, 0.1, 1);
        movement.setFront(true);
        health = new Health(1, position, 50, 10, 50, 0, new SolidColor(128, 0, 0), new SolidColor(255, 0, 0));
        base = new CircularBase(RADIUS, new SolidColor(128, 0, 0), new SolidColor(0, 0, 0), position);
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
        return bound;
    }

    @Override
    public boolean damage(Damaging damagingObject) {
        if(damagingObject.getBound().intersects(bound)&&damagingObject.getTeam()!=getTeam()){
            health.set(health.get()-damagingObject.damage());
            return true;
        }
        return false;
    }

    @Override
    public double damage() {
        return 1;
    }
}
