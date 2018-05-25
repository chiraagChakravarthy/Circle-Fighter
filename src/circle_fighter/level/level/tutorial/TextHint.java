package circle_fighter.level.level.tutorial;

import circle_fighter.color.DynamicColor;
import circle_fighter.engine.Game;
import circle_fighter.functionaliy.Renderable;
import circle_fighter.functionaliy.Updatable;

import java.awt.*;

public class TextHint implements Updatable, Renderable {
    private final DynamicColor color;
    private final long duration;
    private final String text;
    private final Font font;
    private final int x, y;
    private long lastTime;

    public TextHint(String text, Font font, long duration, DynamicColor color, int y){
        this.text = text;
        this.font = font;
        this.duration = duration;
        this.color = color;
        int width = (int) new FontMetrics(font){}.getStringBounds(text, null).getWidth();
        x = (Game.getInstance().getGameWidth()-width)/2;
        this.y=y;
        reset();
    }

    @Override
    public void render(Graphics2D g) {
        g.setFont(font);
        g.setColor(color.get());
        g.drawString(text, x, y);
    }

    @Override
    public void tick() {
        color.tick();
        if(lastTime==0)
            lastTime = System.currentTimeMillis();
        else {
            color.setO(1.0-(double)(System.currentTimeMillis()-lastTime)/duration);
        }
    }

    public void reset(){
        lastTime = 0;
    }

    public boolean isFinished(){
        return System.currentTimeMillis()-lastTime>duration;
    }
}
