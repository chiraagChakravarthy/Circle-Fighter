package circle_fighter.menu.base.component;

import circle_fighter.engine.Game;
import circle_fighter.menu.base.Menu;

import java.awt.*;

public class Option extends MenuComponent {
    public static final int HORIZONTAL_PADDING = 50,
            VERTICAL_PADDING = 2,
            INCLINE_WIDTH = 5,
            SELECTED_EXTENSION = 60,
            SELECTED_VELOCITY = 10,
            FONT_SIZE = 40;

    public static final FontMetrics metrics = new FontMetrics(new Font("Arial", Font.PLAIN, FONT_SIZE)) {};
    public static final Color BACKING_COLOR = new Color(0, 0, 0, 0.5f);

    private boolean selected;
    private int selectedOffset, textX, textY, topY, bottomY, topWidth, bottomWidth, x;
    private String message;
    private Color color;

    public Option(String message, int y, Menu menu, Color color) {
        super();
        this.color = color;
        setMessage(message);
        this.topY = y;
        this.bottomY = (int) (topY + VERTICAL_PADDING*2 + FONT_SIZE*1.25);
        this.textX = HORIZONTAL_PADDING + SELECTED_EXTENSION;
        textY = y + metrics.getHeight() + VERTICAL_PADDING;
        menu.addOption(this);
    }

    public Option(String message, int y, Menu menu) {
        this(message, y, menu, Color.WHITE);
    }

    @Override
    public void tick() {
        selectedOffset = selected ? selectedOffset >= SELECTED_EXTENSION ? SELECTED_EXTENSION : selectedOffset + SELECTED_VELOCITY :
                selectedOffset <= 0 ? 0 : selectedOffset - SELECTED_VELOCITY;
        super.tick();
        selected = false;
    }

    @Override
    public void render(Graphics2D g){
        x = selectedOffset + transitionOffset - SELECTED_EXTENSION-1;
        if(selected) {
            g.setColor(BACKING_COLOR);
            g.fillRect(0, topY + scrollingOffset, Game.getInstance().getGameWidth(), bottomY-topY);
        }

        g.setColor(color);
        g.setFont(new Font("Arial", Font.PLAIN, FONT_SIZE));

        g.drawLine(x, topY + scrollingOffset, x + topWidth, topY + scrollingOffset);
        g.drawLine(x, bottomY + scrollingOffset, x + bottomWidth, bottomY + scrollingOffset);
        g.drawLine(x + topWidth, topY + scrollingOffset, x + bottomWidth, bottomY + scrollingOffset);
        g.drawLine(x, topY + scrollingOffset, x, bottomY + scrollingOffset);

        g.drawString(message, textX + x, textY + scrollingOffset);
    }

    @Override
    public Rectangle getArea(boolean onScreen) {
        return new Rectangle(0, topY + (onScreen ? scrollingOffset : 0), Game.getInstance().getGameWidth(), bottomY-topY);
    }

    public void select(){
        selected = true;
    }

    public void setMessage(String message){
        if(this.message!=message || !this.message.equals(message)) {
            this.message = message;
            int stringWidth = (int) metrics.getStringBounds(message, null).getWidth();
            bottomWidth = textX + stringWidth + HORIZONTAL_PADDING*2 + INCLINE_WIDTH + SELECTED_EXTENSION;
            topWidth = bottomWidth - INCLINE_WIDTH + SELECTED_EXTENSION+HORIZONTAL_PADDING;
        }
    }
}