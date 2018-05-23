package circle_fighter.game.object.objects.bot;

import circle_fighter.game.object.GameObject;
import circle_fighter.game.object.bounds.Bound;
import circle_fighter.game.object.bounds.CircularBound;
import circle_fighter.game.object.functionality.Damageable;
import circle_fighter.game.object.functionality.Damaging;
import circle_fighter.game.object.position.Position;
import circle_fighter.game.object.position.VelAccMovement;
import circle_fighter.game.object.properties.DamageableObject;
import circle_fighter.game.object.properties.DamagingObject;
import circle_fighter.game.object.properties.RenderableObject;
import circle_fighter.game.plane.PlayerPlane;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

@RenderableObject
@DamageableObject
@DamagingObject
public class TestBot extends GameObject implements Damaging, Damageable {
    private static final double RADIUS = 10;

    private CircularBound bound;

    private PlayerPlane plane;

    private VelAccMovement movement;
    private double health;

    public TestBot(Position position, PlayerPlane plane, int team) {
        super(position, plane, BoundExitAction.BOUND, team);
        bound = new CircularBound(position, RADIUS);
        this.plane = plane;
        movement = new VelAccMovement(position, vector, 0.1, 3);
        movement.setFront(true);
        health=1;
    }

    @Override
    public void tick() {
        super.tick();
        Position playerPosition = plane.getPlayer().getPosition();
        double angle = position.angleTo(playerPosition);
        position.setRotation(angle);
        movement.tick();
        if(health<=0)
            plane.getObjectManager().remove(this);
    }

    @Override
    public Bound getBound() {
        return bound;
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillOval((int)(position.getScrX()-10), (int) (position.getScrY()-10), 20, 20);
     }

    @Override
    public boolean damage(Damaging damagingObject) {
        if(damagingObject.getBound().intersects(bound)&&damagingObject.getTeam()!=getTeam()){
            health-=damagingObject.damage();
            return true;
        }
        return false;
    }

    @Override
    public double damage() {
        return 1;
    }
}
