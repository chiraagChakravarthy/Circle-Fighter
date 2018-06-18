package circle_fighter.game.object.turret;

import circle_fighter.functionaliy.HardSavable;
import circle_fighter.gfx.color.DynamicColor;
import circle_fighter.file.DataStorage;
import circle_fighter.functionaliy.Renderable;
import circle_fighter.functionaliy.Savable;
import circle_fighter.functionaliy.Updatable;
import circle_fighter.game.object.GameObject;
import circle_fighter.game.object.objects.Bullet;
import circle_fighter.game.object.position.Vector;
import circle_fighter.gfx.color.ColorRegistry;

import java.awt.*;

public class Turret implements Updatable, Renderable, Savable, HardSavable {
    private GameObject object;
    private float maximumAng, length, girth, shootRate;
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

    public Turret(DataStorage storage, GameObject object){
        this.object = object;
        color = ColorRegistry.fromID(storage.get(0), storage.getSubStorage(0));
        maximumAng = storage.getFloat(1);
        length = storage.getFloat(2);
        girth = storage.get(3);
        shootRate = storage.get(4);
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
            new Bullet(object.getPosition().clone().apply(new Vector((float)Math.cos(angle)*length, (float)Math.sin(angle)*length, 0)), object.getPlane(), 10, 2, object.getTeam());
        }
    }

    public void setFiring(boolean firing) {
        this.firing = firing;
    }

    public boolean isFiring() {
        return firing;
    }

    @Override
    public void hardLoad(DataStorage storage) {
        color.hardLoad(storage.getSubStorage(0));
        firing = storage.get(0)==1;
        relativeAng = storage.getFloat(1);
        lastTime = (long) (System.nanoTime()-(System.nanoTime()%(1e9f/shootRate))+storage.get(2));
    }

    @Override
    public void hardSave(DataStorage storage) {
        color.hardSave(storage.getSubStorage(0));
        storage.set(0, firing?1:0);
        storage.setFloat(1, relativeAng);
        storage.set(2, (int) (System.nanoTime()-lastTime));
    }

    @Override
    public void save(DataStorage storage) {
        storage.set(0, ColorRegistry.toID(color.getClass()));
        color.save(storage.getSubStorage(0));
        storage.setFloat(1, maximumAng).setFloat(2, length).setFloat(3, girth).setFloat(4, shootRate);
    }
}
