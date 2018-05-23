package circle_fighter.game.object.turret;

import circle_fighter.game.object.position.Vector;
import circle_fighter.game.object.objects.turret.TurretGameObject;
import circle_fighter.functionaliy.Renderable;
import circle_fighter.functionaliy.Updatable;

import java.awt.*;

public class Turret implements Updatable, Renderable {
    private TurretGameObject object;
    private final double maximumAng, length, girth, shootRate;
    private double relativeAng;
    private long lastTime;
    private boolean firing;
    public Turret(TurretGameObject object, double maximumAng, double length, double girth, double shootRate){
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
        double x = object.getPosition().getScrX(), y = object.getPosition().getScrY();
        g.setStroke(new BasicStroke((float) girth));
        g.drawLine((int)x, (int)y, (int)(x+Math.cos(angle)*length),(int)(y+Math.sin(angle)*length));
    }

    @Override
    public void tick() {
        long now = System.nanoTime();
        double angle = relativeAng+object.getPosition().getRotation();
        if((now-lastTime)/1.0e9>1/shootRate&&firing){
            lastTime = now;
            new Bullet(object.getPosition().clone().apply(new Vector(Math.cos(angle)*length, Math.sin(angle)*length, 0)), object.getPlane(), 10, 5, object.getTeam());
        }
    }

    public void setFiring(boolean firing) {
        this.firing = firing;
    }

    public boolean isFiring() {
        return firing;
    }
}
