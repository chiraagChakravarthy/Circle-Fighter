package circle_fighter.background;

import circle_fighter.engine.Game;
import circle_fighter.color.DynamicColor;
import circle_fighter.color.Rainbow;
import circle_fighter.functionaliy.Renderable;
import circle_fighter.functionaliy.Updatable;
import circle_fighter.functionaliy.UserInputListener;

import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class Background implements Renderable, Updatable, UserInputListener {
    public static class PlainBackground extends Background {
        private DynamicColor color;
        public PlainBackground(){
            color = new Rainbow(0.5, 5);
        }
        @Override
        public void render(Graphics2D g) {
            g.setColor(color.get());
            g.fillRect(0, 0, Game.getInstance().getGameWidth(), Game.getInstance().getGameHeight());
        }

        @Override
        public void tick() {
            color.tick();
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