package menu.base.component;

import engine.Game;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class TextBox extends MenuComponent {
    public static final int FONT_SIZE = 40;
    public static final FontMetrics metrics = new FontMetrics(new Font("Arial", Font.PLAIN, FONT_SIZE)) {};
    public static final int HORIZONTAL_PADDING = 50,
            VERTICAL_PADDING = 2,
            Y = 20,
            ARCH = 10;

    public static final Color LABEL_COLOR = Color.WHITE;

    private int width, x, labelX, y, textY, bottomY;

    private String message;


    public TextBox(String message, int y){
        super();
        this.y = y;
        textY = y + VERTICAL_PADDING+FONT_SIZE;
        bottomY = (int) (y + VERTICAL_PADDING*2+FONT_SIZE*1.25);
        setMessage(message);
    }

    public void tick() {
        super.tick();
    }

    public void render(Graphics2D g){
        x = transitionOffset + labelX;
        g.setColor(LABEL_COLOR);
        g.draw(new RoundRectangle2D.Float(x, y + scrollingOffset, width, bottomY-y, ARCH, ARCH));

        g.setFont(new Font("Arial", Font.PLAIN, FONT_SIZE));
        g.drawString(message, HORIZONTAL_PADDING + x, textY + scrollingOffset);
    }

    @Override
    public Rectangle getArea(boolean onScreen) {
        return new Rectangle(0, Y + (onScreen ? scrollingOffset : 0), Game.getInstance().getGameWidth(), bottomY-y);
    }

    public void setMessage(String message){
        this.message = message;
        int stringWidth = (int) metrics.getStringBounds(message, null).getWidth();
        labelX = (Game.getInstance().getGameWidth() - stringWidth)/2 - HORIZONTAL_PADDING;
        width = stringWidth + HORIZONTAL_PADDING * 2;
    }
}
