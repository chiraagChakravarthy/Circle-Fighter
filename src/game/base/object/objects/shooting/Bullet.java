package game.base.object.objects.shooting;

import game.base.Plane;
import game.base.object.GameObject;
import game.base.object.Position;

import java.awt.*;

public class Bullet extends GameObject {
    private static final double LENGTH = 6;
    public Bullet(Position position, Plane plane, double velocity, double error) {
        super(position, plane);
        position.setRotation(position.getRotation()+Math.toRadians((Math.random()-0.5)*2*error));
        vector.setVelX(Math.cos(position.getRotation())*velocity);
        vector.setVelY(Math.sin(position.getRotation())*velocity);
    }

    @Override
    public void tick() {
        super.tick();
        position.apply(vector);
    }

    @Override
    public void render(Graphics2D g) {
        g.setStroke(new BasicStroke(1));
        g.setColor(Color.ORANGE);
        g.drawLine((int)position.getX(), (int)position.getY(), (int)(position.getX()-Math.cos(position.getRotation())*LENGTH), (int) (position.getY()-Math.sin(position.getRotation())*LENGTH));
    }
}
