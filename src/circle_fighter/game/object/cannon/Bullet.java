package circle_fighter.game.object.cannon;

import circle_fighter.game.plane.Plane;
import circle_fighter.game.object.GameObject;
import circle_fighter.game.object.position.Position;

import java.awt.*;

public class Bullet extends GameObject {
    private static final double LENGTH = 6;
    private Rectangle bounds;
    public Bullet(Position position, Plane plane, double velocity, double error, int team) {
        super(position, plane, BoundExitAction.DESPAWN, team);
        position.setRotation(position.getRotation()+Math.toRadians((Math.random()-0.5)*2*error));
        vector.setVelX(Math.cos(position.getRotation())*velocity);
        vector.setVelY(Math.sin(position.getRotation())*velocity);
        bounds = new Rectangle((int)position.getX(), (int)position.getY(), 1, 1);
    }

    @Override
    public void tick() {
        super.tick();
        position.apply(vector);
        bounds.setLocation((int)position.getX(), (int)position.getY());
    }

    @Override
    public void render(Graphics2D g) {
        g.setStroke(new BasicStroke(1));
        g.setColor(Color.ORANGE);
        g.drawLine((int)position.getScrX(), (int)position.getScrY(), (int)(position.getScrX()-Math.cos(position.getRotation())*LENGTH), (int) (position.getScrY()-Math.sin(position.getRotation())*LENGTH));
    }

    @Override
    public Rectangle getBounds() {
        return bounds;
    }
}
