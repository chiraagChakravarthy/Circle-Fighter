package game.base.object.objects.shooting;

import game.base.object.Vector;
import game.base.object.objects.ShootingObject;
import interfaces.Renderable;
import interfaces.Updatable;

import java.awt.*;

public class Cannon implements Updatable, Renderable {
    private ShootingObject object;
    private final double maximumAng, length, girth, shootRate;
    private double relativeAng, delta;
    private long lastTime;
    private boolean firing;
    public Cannon(ShootingObject object, double maximumAng, double length, double girth, double shootRate){
        this.object = object;
        this.maximumAng = maximumAng;
        this.length = length;
        this.girth = girth;
        this.shootRate = shootRate;
        relativeAng = 0;
    }

    public double getRelativeAng() {
        return relativeAng;
    }

    public void setRelativeAng(double relativeAng) {
        this.relativeAng = Math.max(Math.min(relativeAng, maximumAng), -maximumAng);
    }

    public double getMaximumAng() {
        return maximumAng;
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.BLUE);
        double angle = relativeAng + object.getPosition().getRotation();
        double x = object.getPosition().getX(), y = object.getPosition().getY();
        g.setStroke(new BasicStroke((float) girth));
        g.drawLine((int)x, (int)y, (int)(x+Math.cos(angle)*length),(int)(y+Math.sin(angle)*length));
    }

    @Override
    public void tick() {
        long now = System.nanoTime();
        double angle = relativeAng+object.getPosition().getRotation();
        if((now-lastTime)/1.0e9>1/shootRate&&firing){
            lastTime = now;
            object.getPlane().getManager().addObject(new Bullet(object.getPosition().clone().apply(new Vector(Math.cos(angle)*length, Math.sin(angle)*length, 0)), object.getPlane(), 10, 0));
        }
    }

    public void setFiring(boolean firing) {
        this.firing = firing;
    }

    public boolean isFiring() {
        return firing;
    }
}
