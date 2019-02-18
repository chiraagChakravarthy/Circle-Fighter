package circle_fighter.game.object.wrapper;

import circle_fighter.functionaliy.Renderable;
import circle_fighter.functionaliy.Updatable;
import circle_fighter.game.object.position.Position;
import circle_fighter.game.ui.ProgressBar;
import circle_fighter.gfx.color.DynamicColor;
import circle_fighter.gfx.color.SolidColor;
import circle_fighter.user.element.UserHealth;

import java.awt.*;

public class Health implements Renderable, Updatable {
    private final float maxHealth, invulnerabilityMultiplier, regenerationRate;
    private int verticalOffset;

    private Position position;
    private ProgressBar healthBar;

    private long damagedTime, invulnerabilityTime;
    private float health;
    private boolean visible;

    public Health(float maxHealth, float regenerationRate, Position position, int width, int height, int verticalOffset, float invulnerabilityMultiplier, DynamicColor borderColor, DynamicColor barColor){
        this.maxHealth = maxHealth;
        this.regenerationRate = regenerationRate;
        health = maxHealth;
        this.position = position;
        healthBar = new ProgressBar(0, 0, width, height, borderColor, barColor);
        this.verticalOffset = verticalOffset;
        this.invulnerabilityMultiplier = invulnerabilityMultiplier;
        damagedTime = 0;
        visible = true;
    }

    public Health(Position position, UserHealth health){
        this.position = position;
        maxHealth = health.getFunctions()[UserHealth.MAX_HEALTH].perform(health.get(UserHealth.MAX_HEALTH));
        invulnerabilityMultiplier = health.getFunctions()[UserHealth.INVULNERABILITY_MULTIPLIER].perform(health.get(UserHealth.INVULNERABILITY_MULTIPLIER));
        regenerationRate = health.getFunctions()[UserHealth.REGENERATION_RATE].perform(health.get(UserHealth.REGENERATION_RATE));
        verticalOffset = -50;
        this.health = maxHealth;
        healthBar = new ProgressBar(0, 0, 50, 10, new SolidColor(0, 128, 0), new SolidColor(0, 255, 0));
    }

    public void tick(){
        health = Math.min(maxHealth, health+regenerationRate/60);
        healthBar.setProgress(health/ maxHealth);
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
}
