package game.base.object.objects;

import game.base.Plane;
import game.base.object.GameObject;
import game.base.object.MovementVector;
import game.base.object.Position;
import game.base.object.objects.shooting.Cannon;

import java.awt.*;

public abstract class ShootingObject extends GameObject {
    protected Cannon mainCannon;
    protected MovementVector movement;
    public ShootingObject(Position position, double acc, double accAng, double maxVel, double velMaxAng, Plane plane) {
        super(position, plane);
        movement = new MovementVector(maxVel, velMaxAng, acc, accAng, position, vector);
        mainCannon = new Cannon(this, Math.PI/9, 40, 5, 60);
    }

    @Override
    public void render(Graphics2D g) {
        mainCannon.render(g);
    }

    @Override
    public void tick() {
        super.tick();
        movement.tick();
        mainCannon.tick();
    }
}
