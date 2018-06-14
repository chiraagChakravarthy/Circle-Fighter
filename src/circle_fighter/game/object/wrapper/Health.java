package circle_fighter.game.object.wrapper;

import circle_fighter.color.DynamicColor;
import circle_fighter.file.DataStorage;
import circle_fighter.functionaliy.Renderable;
import circle_fighter.functionaliy.Savable;
import circle_fighter.functionaliy.Updatable;
import circle_fighter.game.object.position.Position;
import circle_fighter.game.ui.ProgressBar;

import java.awt.*;

public class Health implements Renderable, Updatable, Savable {
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
        healthBar = new ProgressBar(0, 0, width, height, 1, borderColor, barColor);
        this.verticalOffset = verticalOffset;
        this.invulnerabilityMultiplier = invulnerabilityMultiplier;
        damagedTime = 0;
        visible = true;
    }

    public Health(Position position){
        this.position = position;

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
    public void from(DataStorage storage) {

    }

    @Override
    public void to(DataStorage storage) {

    }
}
