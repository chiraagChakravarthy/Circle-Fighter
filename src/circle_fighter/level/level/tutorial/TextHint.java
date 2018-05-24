package circle_fighter.level.level.tutorial;

import circle_fighter.color.DynamicColor;
import circle_fighter.engine.Game;
import circle_fighter.functionaliy.Renderable;
import circle_fighter.functionaliy.Updatable;

import java.awt.*;

public class TextHint implements Updatable, Renderable{
    private String text;
    private Font font;
    private long lastTime, duration;
    private int x, y;
    private DynamicColor textColor;
    public TextHint(Font font, String text, int y, DynamicColor color, long duration){
        this.y = y;
        this.font = font;
        this.textColor = color;
        FontMetrics metrics = new FontMetrics(font) {};
        int width = (int) metrics.getStringBounds(text, null).getWidth();
        x = (Game.getInstance().getGameWidth()-width)/2;
        this.duration = duration;
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(textColor.get());
        g.setFont(font);
        g.drawString(text, x, y);
    }

    @Override
    public void tick() {
        if(lastTime==0)
            lastTime = System.currentTimeMillis();
        textColor.setO(Math.max(0, 1-(double)(System.currentTimeMillis()-lastTime)/ duration));
    }

    public boolean finished(){
        return System.currentTimeMillis()-lastTime > duration&&lastTime!=0;
    }

    public void reset(){
        lastTime = 0;
    }
}
