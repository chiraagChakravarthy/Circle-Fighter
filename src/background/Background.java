package background;

import engine.Game;
import interfaces.Renderable;
import interfaces.Updatable;
import interfaces.UserInputListener;

import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class Background implements Renderable, Updatable, UserInputListener {
    public static class PlainBackground extends Background {
        private int time;
        @Override
        public void render(Graphics2D g) {
            g.setColor(Color.GRAY);
            g.fillRect(0, 0, Game.getInstance().getGameWidth(), Game.getInstance().getGameHeight());
        }

        @Override
        public void tick() {
            time++;
        }

        @Override
        public void keyPressed(int k) {

        }

        @Override
        public void keyReleased(int k) {

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }
    }
}