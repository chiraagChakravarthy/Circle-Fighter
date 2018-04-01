package circle_fighter.game.object.objects.turret;

import circle_fighter.game.object.GameObject;
import circle_fighter.game.object.cannon.Cannon;
import circle_fighter.game.object.position.Position;
import circle_fighter.game.object.position.VelAngAccMovement;
import circle_fighter.game.plane.Plane;

import java.awt.*;

public abstract class TurretGameObject extends GameObject {
    protected Cannon mainCannon;
    protected VelAngAccMovement movement;
    protected double health;

    public TurretGameObject(Position position, double acc, double accAng, double maxVel, double velMaxAng, double health, int team, Plane plane) {
        super(position, plane, BoundExitAction.BOUND, team);
        movement = new VelAngAccMovement(position, vector, acc, maxVel, accAng, velMaxAng);
        mainCannon = new Cannon(this, Math.PI/9, 40, 5, 10);
        this.health = health;
    }

    @Override
    public void render(Graphics2D g) {
        mainCannon.render(g);
    }

    @Override
    public void tick() {
        super.tick();
        movement.tick();
        position.apply(vector);
        mainCannon.tick();
    }

    public double getHealth() {
        return health;
    }

    public void damage(){
        health--;
    }
}
