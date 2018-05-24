package circle_fighter.game.object.wrapper;

import circle_fighter.color.DynamicColor;
import circle_fighter.functionaliy.Renderable;
import circle_fighter.functionaliy.Updatable;
import circle_fighter.game.object.position.Position;
import circle_fighter.game.ui.ProgressBar;

import java.awt.*;

public class Health implements Renderable, Updatable{
    private final double initialHealth;
    private final int verticalOffset;
    private final long invulnerabilityTime;

    private Position position;
    private ProgressBar healthBar;

    private long damagedTime;
    private double health;
    private boolean visible;

    public Health(double initialHealth, Position position, int width, int height, int verticalOffset, long invulnerabilityTime, DynamicColor borderColor, DynamicColor barColor){
        this.initialHealth = initialHealth;
        health = initialHealth;
        this.position = position;
        healthBar = new ProgressBar(0, 0, width, height, 1, borderColor, barColor);
        this.verticalOffset = verticalOffset;
        this.invulnerabilityTime = invulnerabilityTime;
        damagedTime = 0;
        visible = true;
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

    public void damage(double amount){
        long now = System.currentTimeMillis();
        if(now - damagedTime > invulnerabilityTime) {
            health -= amount;
            damagedTime = now;
        }
    }

    public double get() {
        return health;
    }

    public void set(double health) {
        this.health = health;
    }
}
