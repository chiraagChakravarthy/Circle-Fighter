package circle_fighter.gfx.text;

import circle_fighter.gfx.color.DynamicColor;
import circle_fighter.engine.Game;
import circle_fighter.functionaliy.Renderable;
import circle_fighter.functionaliy.Updatable;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;

public class Text implements Updatable, Renderable {
    private static final double PADDING_PROPORTION = 0.4;
    private int x, y, width, height;
    private ArrayList<Line> lines;
    private Font font;
    private DynamicColor color;
    private boolean showBorder, minimizeWidth, center;
    private RoundRectangle2D border;
    private Alignment alignment;
    private String text;
    //TODO fix glitch where height becomes 0 when text contains only spaces
    public Text(String text, int x, int y, int width, Font font, DynamicColor color, Alignment alignment, boolean showBorder, boolean minimizeWidth, boolean center){
        this.font = font;
        this.color = color;
        this.x = x;
        this.y = y;
        this.showBorder = showBorder;
        this.width = width;
        this.alignment = alignment;
        this.minimizeWidth = minimizeWidth;
        this.center = center;
        setText(text);
    }

    private void addLine(String text, int lineWidth, int textWidth, int padding, Alignment alignment){
        lines.add(new Line(text,
                            alignment.equals(Alignment.LEFT)?padding:alignment.equals(Alignment.CENTER)?(textWidth-lineWidth)/2+padding:textWidth-lineWidth+padding,
                            (lines.size())*(font.getSize()+padding*2)+padding+font.getSize(), lineWidth));
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(color.get());
        if(showBorder)
            g.draw(border);
        g.setFont(font);
        lines.forEach(line -> {
            g.drawString(line.getText(), line.getX() + x, line.getY() + y);
        });
    }

    public void setX(int x) {
        border = new RoundRectangle2D.Double(x, y, width, height, border.getArcWidth(), border.getArcHeight());
        this.x = x;
    }

    public void setY(int y) {
        border = new RoundRectangle2D.Double(x, y, width, height, border.getArcWidth(), border.getArcHeight());
        this.y = y;
    }

    public int getWidth(){
        return (int) border.getWidth();
    }

    public int getHeight(){
        return (int)border.getHeight();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public void tick() {
        color.tick();
    }

    public void setText(String text){
        lines = new ArrayList<>();
        int padding = (int) (font.getSize()*PADDING_PROPORTION);
        int textWidth = width-padding*2, lineWidth = 0;
        FontMetrics metrics = new FontMetrics(font) {};
        StringBuilder line = new StringBuilder();
        String[] words = text.split(" ");

        for (int i = 0; i < words.length; i++) {
            String word = words[i] + " ";
            boolean nextLine = false;
            if(word.contains("\n")){
                word = word.replace("\n", "");
                nextLine = true;
            }

            int appendedLineWidth = (int) metrics.getStringBounds(line+word, null).getWidth();

            if(nextLine||appendedLineWidth>textWidth){
                addLine(line.toString(), lineWidth, textWidth, padding, alignment);
                line = new StringBuilder(word);
                lineWidth = (int) metrics.getStringBounds(line+word, null).getWidth();
            }
            else {
                line.append(word);
                lineWidth = appendedLineWidth;
            }
        }
        if(line.length()!=0)
            addLine(line.toString(), lineWidth, textWidth, padding, alignment);
        height = lines.size() * (font.getSize() + 2 * padding);
        if(lines.size()==1&&minimizeWidth) {
            width = lineWidth + padding * 2;
            textWidth=width-padding*2;
        }
        if(center){
            x = (Game.getInstance().getGameWidth()-width)/2;
            if(minimizeWidth) {
                for(Line builtLine : lines) {
                    builtLine.setX(padding+(textWidth-builtLine.getLineWidth())/2);
                }
            }
        }
        border = new RoundRectangle2D.Double(x, y, width, height, font.getSize(), font.getSize());
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public enum Alignment {
        LEFT,
        CENTER,
        RIGHT
    }
}
