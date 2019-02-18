package circle_fighter.game.object.objects.bots;

import circle_fighter.game.object.functionality.DirectlyDamaging;
import circle_fighter.game.object.position.UpdatingPosition;
import circle_fighter.game.object.position.Vector;
import circle_fighter.game.object.position.movement.MotionlessMovement;
import circle_fighter.gfx.color.SolidColor;
import circle_fighter.game.object.GameObject;
import circle_fighter.game.object.bounds.Bound;
import circle_fighter.game.object.functionality.Damageable;
import circle_fighter.game.object.functionality.Damaging;
import circle_fighter.game.object.implementations.CharacterObject;
import circle_fighter.game.object.implementations.DamageableObject;
import circle_fighter.game.object.implementations.RenderableObject;
import circle_fighter.game.object.bounds.render_base.CircularBase;
import circle_fighter.game.object.wrapper.Health;
import circle_fighter.game.plane.Plane;

import java.awt.*;
@RenderableObject
@DamageableObject
@CharacterObject
public class TutorialBot extends GameObject implements Damageable {
    private static final int RADIUS = 10;

    private Health health;
    private CircularBase base;

    public TutorialBot(UpdatingPosition position, Plane plane, int team) {
        super(plane, BoundExitAction.BOUND, new MotionlessMovement(position, new Vector(0, 0, 0)), team);
        health = new Health(1, this.position, 50, 10, 50, 0, new SolidColor(128, 0, 0), new SolidColor(255, 0, 0));
        base = new CircularBase(position, RADIUS, new SolidColor(0, 0, 0), new SolidColor(0, 0, 0));
    }

    @Override
    public void render(Graphics2D g) {
        base.render(g);
        health.render(g);
    }

    @Override
    public void tick() {
        super.tick();
        health.tick();
        base.tick();
        if(health.get()<=0)
            despawn();
    }

    @Override
    public Bound getBound() {
        return base;
    }

    @Override
    public boolean damage(DirectlyDamaging damagingObject) {
        if(damagingObject.getBound().intersects(base)){
            health.set(health.get()-damagingObject.damage());
            return true;
        }
        return false;
    }

    @Override
    public float expOnDeath() {
        return 0;
    }

    public Health getHealth() {
        return health;
    }
}
