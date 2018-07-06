package circle_fighter.menu.base.component;

import circle_fighter.gfx.color.DynamicColor;
import circle_fighter.engine.Game;
import circle_fighter.gfx.text.Text;
import circle_fighter.gfx.text.TextBuilder;
import circle_fighter.menu.base.MenuProgression;

import java.awt.*;

public class TextBox extends MenuComponent {
    private Text text;
    private int x;
    private String message;
    public TextBox(String message, int y, int width, DynamicColor color, boolean showBorder, boolean minimizeWidth){
        super();
        text = new TextBuilder()
                .setTextAlign(Text.Alignment.CENTER)
                .setText(message)
                .setWidth(width)
                .setY(y)
                .setColor(color)
                .setShowBorder(showBorder)
                .setCenter(true)
                .setMinimizeWidth(minimizeWidth)
                .get();
        this.x  = text.getX();
        this.message = message;
        text.setX(x+transitionOffset);
    }

    public TextBox(String message, int x, int y, int width, DynamicColor color, boolean showBorder){
        super();
        text = new TextBuilder()
                .setTextAlign(Text.Alignment.CENTER)
                .setX(x)
                .setY(y)
                .setText(message)
                .setWidth(width)
                .setColor(color)
                .setShowBorder(showBorder)
                .get();
        this.x = x;
        text.setX(x+transitionOffset);
        this.message = message;
    }

    public TextBox(String message, int y, int width, DynamicColor color){
        this(message, y, width, color, true, true);
    }

    public void tick() {
        super.tick();
        text.setX(x+transitionOffset);
    }

    public void render(Graphics2D g){
       text.render(g);
    }

    @Override
    public Rectangle getArea(boolean onScreen) {
        return new Rectangle(text.getX(), text.getY() + (onScreen ? scrollingOffset : 0), text.getWidth(), text.getHeight());
    }

    public boolean setMessage(String message) {
        if(getProgression().equals(MenuProgression.DEFAULT)) {
            text.setText(message);
            x = text.getX();
            this.message = message;
            return true;
        }
        return false;
    }

    public String getMessage(){
        return message;
    }
}
