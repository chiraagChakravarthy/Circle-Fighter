package circle_fighter.game.object.objects;

import circle_fighter.game.object.GameObject;
import circle_fighter.game.object.bounds.Bound;
import circle_fighter.game.object.bounds.PointBound;
import circle_fighter.game.object.functionality.Damaging;
import circle_fighter.game.object.position.Position;
import circle_fighter.game.object.implementations.DamagingObject;
import circle_fighter.game.object.implementations.RenderableObject;
import circle_fighter.game.plane.Plane;

import java.awt.*;
@RenderableObject
@DamagingObject
public class Bullet extends GameObject implements Damaging{

    private static final double LENGTH = 10;
    private PointBound bound;
    private double damage;
    public Bullet(Position position, Plane plane, double velocity, double error, int team) {
        super(position, plane, BoundExitAction.DESPAWN, team);
        position.setRotation(position.getRotation()+Math.toRadians((Math.random()-0.5)*2*error));
        vector.setVelX(Math.cos(position.getRotation())*velocity);
        vector.setVelY(Math.sin(position.getRotation())*velocity);
        bound = new PointBound(position);
        damage = 1;
    }

    @Override
    public void tick() {
        super.tick();
        position.apply(vector);
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
    public double damage() {
        if(damage>0) {
            damage--;
            if(damage<=0)
                despawn();
            return 1;
        }
        return 0;
    }
}
