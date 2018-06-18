package circle_fighter.game.object.wrapper;

import circle_fighter.functionaliy.HardSavable;
import circle_fighter.gfx.color.DynamicColor;
import circle_fighter.file.DataStorage;
import circle_fighter.functionaliy.Renderable;
import circle_fighter.functionaliy.Savable;
import circle_fighter.functionaliy.Updatable;
import circle_fighter.game.object.position.Position;
import circle_fighter.game.ui.ProgressBar;

import java.awt.*;

public class Health implements Renderable, Updatable, Savable, HardSavable {
    private float initialHealth, invulnerabilityMultiplier;
    private int verticalOffset;

    private Position position;
    private ProgressBar healthBar;

    private long damagedTime, invulnerabilityTime;
    private float health;
    private boolean visible;

    public Health(float initialHealth, Position position, int width, int height, int verticalOffset, float invulnerabilityMultiplier, DynamicColor borderColor, DynamicColor barColor){
        this.initialHealth = initialHealth;
        health = initialHealth;
        this.position = position;
        healthBar = new ProgressBar(0, 0, width, height, borderColor, barColor);
        this.verticalOffset = verticalOffset;
        this.invulnerabilityMultiplier = invulnerabilityMultiplier;
        damagedTime = 0;
        visible = true;
    }

    public Health(Position position, DataStorage storage){
        this.position = position;
        initialHealth = storage.getFloat(0);
        invulnerabilityMultiplier = storage.getFloat(1);
        verticalOffset = storage.get(2);
        health = initialHealth;
        healthBar = new ProgressBar(storage.getSubStorage(0));
    }

    public void tick(){
        healthBar.setProgress(health/initialHealth);
        healthBar.tick();
        healthBar.setX(position.getScrX()-healthBar.getWidth()/2);
        healthBar.setY(position.getScrY()+verticalOffset);
        visible = System.currentTimeMillis() - damagedTime > invulnerabilityTime || !visible;
    }

    public void render(Graphics2D g){
        if(visible)
            healthBar.render(g);
    }

    public void damage(float amount, long time){
        long now = System.currentTimeMillis();
        System.out.println(now-damagedTime);
        if(now - damagedTime > invulnerabilityTime) {
            health -= amount;
            damagedTime = now;
            invulnerabilityTime  = (long)(time*invulnerabilityMultiplier);
        }
    }

    public float get() {
        return health;
    }

    public void set(float health) {
        this.health = health;
    }

    @Override
    public void save(DataStorage storage) {
        storage.setFloat(0, initialHealth).setFloat(1, invulnerabilityMultiplier).set(2, verticalOffset);
        healthBar.save(storage.getSubStorage(0));
    }

    @Override
    public void hardLoad(DataStorage storage) {

    }

    @Override
    public void hardSave(DataStorage storage) {

    }
}
