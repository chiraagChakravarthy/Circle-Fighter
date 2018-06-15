package circle_fighter.gfx.text;

import circle_fighter.gfx.color.DynamicColor;
import circle_fighter.gfx.color.SolidColor;
import circle_fighter.engine.Game;

import java.awt.*;

public class TextBuilder {
    private String text;
    private int x, y, width;
    private Font font;
    private DynamicColor color;
    private boolean showBorder, minimizeWidth, center;
    private Text.Alignment textAlign;
    public TextBuilder(){
        text = "";
        x = 0;
        y = 0;
        width = Game.getInstance().getGameWidth();
        font = new Font("Arial", Font.PLAIN, 30);
        color = new SolidColor(255, 255, 255);
        showBorder = true;
        minimizeWidth = true;
        center = true;
        textAlign = Text.Alignment.CENTER;
    }

    public TextBuilder setText(String text) {
        this.text = text;
        return this;
    }

    public TextBuilder setX(int x) {
        this.x = x;
        return this;
    }

    public TextBuilder setY(int y) {
        this.y = y;
        return this;
    }

    public TextBuilder setWidth(int width) {
        this.width = width;
        return this;
    }

    public TextBuilder setFont(Font font) {
        this.font = font;
        return this;
    }

    public TextBuilder setColor(DynamicColor color) {
        this.color = color;
        return this;
    }

    public TextBuilder setShowBorder(boolean showBorder) {
        this.showBorder = showBorder;
        return this;
    }

    public TextBuilder setTextAlign(Text.Alignment textAlign) {
        this.textAlign = textAlign;
        return this;
    }

    public TextBuilder setMinimizeWidth(boolean minimizeWidth) {
        this.minimizeWidth = minimizeWidth;
        return this;
    }

    public TextBuilder setCenter(boolean center) {
        this.center = center;
        return this;
    }

    public Text get(){
        return new Text(text, x, y, width, font, color, textAlign, showBorder, minimizeWidth, center);
    }
}
