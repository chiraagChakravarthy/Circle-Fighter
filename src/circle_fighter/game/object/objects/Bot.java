package circle_fighter.game.object.objects;

import circle_fighter.color.SolidColor;
import circle_fighter.game.object.GameObject;
import circle_fighter.game.object.bounds.Bound;
import circle_fighter.game.object.bounds.CircularBound;
import circle_fighter.game.object.functionality.Damageable;
import circle_fighter.game.object.functionality.Damaging;
import circle_fighter.game.object.implementations.DamageableObject;
import circle_fighter.game.object.implementations.DamagingObject;
import circle_fighter.game.object.implementations.RenderableObject;
import circle_fighter.game.object.position.Position;
import circle_fighter.game.object.position.Vector;
import circle_fighter.game.object.position.VelAccMovement;
import circle_fighter.game.object.wrapper.Health;
import circle_fighter.game.plane.PlayerPlane;

import java.awt.*;
@DamageableObject
@DamagingObject
@RenderableObject
public class Bot extends GameObject implements Damaging, Damageable{
    private CircularBound bound;
    private VelAccMovement movement;
    private Vector vector;
    private PlayerPlane plane;
    private Health health;
    public Bot(Position position, PlayerPlane plane, int team) {
        super(position, plane, BoundExitAction.BOUND, team);
        bound = new CircularBound(position, 10);
        vector = new Vector(0, 0, 0);
        movement = new VelAccMovement(position, vector, 0.1, 3);
        movement.setFront(true);
        health = new Health(1, position, 50, 10, 50, 0, new SolidColor(128, 0, 0), new SolidColor(255, 0, 0));
        this.plane = plane;
    }

    @Override
    public void tick() {
        super.tick();
        position.setRotation(position.angleTo(plane.getPlayer().getPosition()));
        health.tick();
        if(health.get()<=0)
            plane.getObjectManager().remove(this);
        movement.tick();
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillOval((int)(position.getScrX()-10), (int)(position.getScrY()-10), 20, 20);
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
