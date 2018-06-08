package circle_fighter.level.level.tutorial;

import circle_fighter.color.DynamicColor;
import circle_fighter.engine.Game;
import circle_fighter.functionaliy.Renderable;
import circle_fighter.functionaliy.Updatable;
import circle_fighter.text.Text;
import circle_fighter.text.TextBuilder;

import java.awt.*;

public class TextHint implements Updatable, Renderable {
    private final DynamicColor color;
    private final long duration;
    private long lastTime;
    private Text text;

    public TextHint(String text, Font font, long duration, DynamicColor color, int y){
        this.duration = duration;
        this.color = color;
        this.text = new TextBuilder().setCenter(true).setShowBorder(false).setText(text).setFont(font).setY(y).setColor(color).setWidth(Game.getInstance().getGameWidth()).setTextAlign(Text.Alignment.CENTER).get();
        reset();
    }

    @Override
    public void render(Graphics2D g) {
        text.render(g);
    }

    @Override
    public void tick() {
        text.tick();
        if(lastTime==0)
            lastTime = System.currentTimeMillis();
        else {
            color.setO((float) (1.0-(System.currentTimeMillis()-lastTime)/duration));
        }
    }

    public void reset() {
        lastTime = 0;
    }

    public boolean isFinished(){
        return System.currentTimeMillis()-lastTime>duration;
    }
}
