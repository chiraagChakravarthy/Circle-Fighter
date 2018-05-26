package circle_fighter.menu.base.component;

import circle_fighter.color.DynamicColor;
import circle_fighter.engine.Game;
import circle_fighter.text.Text;
import circle_fighter.text.TextBuilder;

import java.awt.*;

public class TextBox extends MenuComponent {
    private Text text;
    private int x;
    //TODO fix glitch where this appears in unopened menus for a frame
    public TextBox(String message, int y, int width, DynamicColor color, boolean showBorder){
        super();
        x = (Game.getInstance().getGameWidth()-width)/2;
        text = new TextBuilder()
                .setTextAlign(Text.Alignment.CENTER)
                .setText(message)
                .setWidth(width)
                .setY(y)
                .setColor(color)
                .setX((Game.getInstance().getGameWidth()-width)/2)
                .setShowBorder(showBorder).get();
    }

    public TextBox(String message, int y, int width, DynamicColor color){
        this(message, y, width, color, true);
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

    public void setMessage(String message) {
        text.setText(message);
    }
}
