package circle_fighter.engine.notification;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;

public class Notification {
    public static final int FONT_SIZE = 20,
            HORIZONTAL_PADDING = 10,
            TEXT_WIDTH = 300,
            WIDTH = HORIZONTAL_PADDING * 2 + TEXT_WIDTH,
            LINE_SPACING = 10,
            ARCH = 10;
    public static final FontMetrics metrics = new FontMetrics(new Font("Arial", Font.PLAIN, FONT_SIZE)) {};

    private int height, lifeTime, maxLifeTime;
    private ArrayList<String> lines;
    private float opacity;

    public Notification(String message){
        lines = new ArrayList<>();
        String[] words = message.split(" ");
        maxLifeTime = 60 + words.length*36;
        generateLines(words);
        lifeTime = 0;
        height = lines.size() * (FONT_SIZE + LINE_SPACING) + LINE_SPACING;
        opacity = 1;
    }

    private void generateLines(String[] words){
        String line = "";
        for (int i = 0; i < words.length; i++) {
            if(metrics.getStringBounds(line + " " + words[i], null).getWidth() > TEXT_WIDTH){
                lines.add(line);
                line = words[i];
            }
            else line += " " + words[i];
        }
        lines.add(line);
    }

    public void tick() {
        lifeTime++;
        if(lifeTime >= maxLifeTime) NotificationManager.next();
        if(maxLifeTime - lifeTime < 20) opacity = (maxLifeTime - lifeTime)/20.0f;
        else opacity = 1;
    }

    public void render(Graphics2D g){
        RoundRectangle2D rectangle = new RoundRectangle2D.Float(0, 0, WIDTH, height, ARCH, ARCH);
        g.setColor(new Color(0, 0, 0, getOpacity()));
        g.fill(rectangle);
        g.setColor(new Color(1, 1, 1, getOpacity()));
        g.draw(rectangle);
        g.setFont(new Font("Arial", Font.PLAIN, FONT_SIZE));
        for (int i = 0; i < lines.size(); i++) {
            g.drawString(lines.get(i), HORIZONTAL_PADDING, (i+1)*(LINE_SPACING + FONT_SIZE));
        }
    }

    private float getOpacity(){
        return opacity < 0 ? 0 : opacity > 1 ? 1 : opacity;
    }
}