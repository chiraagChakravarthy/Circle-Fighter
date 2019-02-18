package circle_fighter.game.object.objects;

import circle_fighter.game.object.GameObject;
import circle_fighter.game.object.bounds.Bound;
import circle_fighter.game.object.bounds.PointBound;
import circle_fighter.game.object.functionality.Damageable;
import circle_fighter.game.object.functionality.Damaging;
import circle_fighter.game.object.functionality.DirectlyDamaging;
import circle_fighter.game.object.position.Position;
import circle_fighter.game.object.implementations.DamagingObject;
import circle_fighter.game.object.implementations.RenderableObject;
import circle_fighter.game.object.position.UpdatingPosition;
import circle_fighter.game.object.position.Vector;
import circle_fighter.game.object.position.movement.VelMovement;
import circle_fighter.game.object.turret.Turret;
import circle_fighter.game.plane.Plane;

import java.awt.*;
@RenderableObject
@DamagingObject
public class Bullet extends GameObject implements DirectlyDamaging{

    private static final double LENGTH = 10;
    private PointBound bound;
    private double damage;
    private Turret turret;
    public Bullet(UpdatingPosition position, Plane plane, float velocity, float error, int team, Turret turret) {
        super(plane, BoundExitAction.DESPAWN, new VelMovement(position, new Vector(0, 0, 0), velocity), team);
        position.setRotation((float) (position.getRotation()+Math.toRadians((Math.random()-0.5)*2*error)));
        vector.setVelX((float) (Math.cos(position.getRotation())*velocity));
        vector.setVelY((float) (Math.sin(position.getRotation())*velocity));
        this.turret = turret;
        bound = new PointBound(position);
        damage = 1;
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public Bound getBound() {
        return bound;
    }

    @Override
    public void render(Graphics2D g) {
        g.setStroke(new BasicStroke(3));
        g.setColor(Color.ORANGE);
        g.drawLine((int) position.getScrX(), (int) position.getScrY(), (int) (position.getScrX() - Math.cos(position.getRotation()) * LENGTH), (int) (position.getScrY() - Math.sin(position.getRotation()) * LENGTH));
    }

    @Override
    public float damage() {
        if(damage>0) {
            damage--;
            if(damage<=0)
                despawn();
            return 1;
        }
        return 0;
    }

    @Override
    public long invulnerabilityTime() {
        return 100;
    }

    @Override
    public void onKill(Damageable damageable) {
        turret.onKill(damageable);
    }
}
