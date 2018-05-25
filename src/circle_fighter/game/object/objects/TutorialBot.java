package circle_fighter.game.object.objects;

import circle_fighter.color.SolidColor;
import circle_fighter.game.object.GameObject;
import circle_fighter.game.object.bounds.Bound;
import circle_fighter.game.object.bounds.CircularBound;
import circle_fighter.game.object.functionality.Damageable;
import circle_fighter.game.object.functionality.Damaging;
import circle_fighter.game.object.implementations.DamageableObject;
import circle_fighter.game.object.implementations.RenderableObject;
import circle_fighter.game.object.position.Position;
import circle_fighter.game.object.wrapper.Health;
import circle_fighter.game.plane.Plane;

import java.awt.*;
@RenderableObject
@DamageableObject

public class TutorialBot extends GameObject implements Damageable {
    private CircularBound bound;
    private Health health;

    public TutorialBot(Position position, Plane plane, int team) {
        super(position, plane, BoundExitAction.BOUND, team);
        bound = new CircularBound(position, 10);
        health = new Health(1, this.position, 50, 10, 50, 0, new SolidColor(128, 0, 0), new SolidColor(255, 0, 0));
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillOval((int)(position.getScrX()-10), (int)(position.getScrY()-10), 20, 20);
        health.render(g);
    }

    @Override
    public void tick() {
        super.tick();
        health.tick();
        if(health.get()<=0)
            despawn();
    }

    @Override
    public Bound getBound() {
        return bound;
    }

    @Override
    public boolean damage(Damaging damagingObject) {
        if(damagingObject.getBound().intersects(bound)){
            health.damage(damagingObject.damage());
            return true;
        }
        return false;
    }

    public Health getHealth() {
        return health;
    }
}
