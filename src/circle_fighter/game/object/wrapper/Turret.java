package circle_fighter.game.object.wrapper;

import circle_fighter.color.DynamicColor;
import circle_fighter.functionaliy.Renderable;
import circle_fighter.functionaliy.Updatable;
import circle_fighter.game.object.GameObject;
import circle_fighter.game.object.objects.Bullet;
import circle_fighter.game.object.position.Vector;

import java.awt.*;

public class Turret implements Updatable, Renderable {
    private GameObject object;
    private final float maximumAng, length, girth, shootRate;
    private float relativeAng;
    private long lastTime;
    private boolean firing;
    private DynamicColor color;

    public Turret(GameObject object, float maximumAng, float length, float girth, float shootRate, DynamicColor color){
        this.object = object;
        this.maximumAng = maximumAng;
        this.length = length;
        this.girth = girth;
        this.shootRate = shootRate;
        this.color = color;
        relativeAng = 0;
    }

    public float getRelativeAng() {
        return relativeAng;
    }

    public void setRelativeAng(float relativeAng) {
        this.relativeAng = Math.max(Math.min(relativeAng, maximumAng), -maximumAng);
    }

    public float getMaximumAng() {
        return maximumAng;
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(color.get());
        float angle = relativeAng + object.getPosition().getRotation();
        float x = object.getPosition().getScrX(), y = object.getPosition().getScrY();
        g.setStroke(new BasicStroke(girth));
        g.drawLine((int)x, (int)y, (int)(x+Math.cos(angle)*length),(int)(y+Math.sin(angle)*length));
    }

    @Override
    public void tick() {
        color.tick();
        long now = System.nanoTime();
        float angle = relativeAng+object.getPosition().getRotation();
        if((now-lastTime)/1.0e9>1/shootRate&&firing){
            lastTime = now;
            new Bullet(object.getPosition().clone().apply(new Vector(Math.cos(angle)*length, Math.sin(angle)*length, 0)), object.getPlane(), 10, 2, object.getTeam());
        }
    }

    public void setFiring(boolean firing) {
        this.firing = firing;
    }

    public boolean isFiring() {
        return firing;
    }
}
