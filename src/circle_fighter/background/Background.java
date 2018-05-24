package circle_fighter.background;

import circle_fighter.engine.Game;
import circle_fighter.color.DynamicColor;
import circle_fighter.color.Rainbow;
import circle_fighter.functionaliy.Renderable;
import circle_fighter.functionaliy.Updatable;
import circle_fighter.functionaliy.UserInputListener;
import circle_fighter.game.object.position.Position;
import javafx.geometry.Pos;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.image.BufferedImage;

public abstract class Background implements Renderable, Updatable, UserInputListener {
    public static class PlainBackground extends Background {
        private DynamicColor color;
        public PlainBackground(DynamicColor color){
            this.color = color;
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

        @Override
        public void mouseScrolled(MouseWheelEvent e) {

        }
    }
    public static class CheckeredBackground extends Background{
        private DynamicColor color1, color2;
        private double unitSize;
        private Position position;
        public CheckeredBackground(DynamicColor color1, DynamicColor color2, int unitSize, Position position){
            this.color1 = color1;
            this.color2 = color2;
            this.unitSize = unitSize;
            this.position = position;
        }

        @Override
        public void render(Graphics2D g) {
            int unitX = (int) (position.getX()/unitSize),
                unitY = (int) (position.getY()/unitSize),
                xOff = (int) (position.getX()%unitSize),
                yOff = (int) (position.getY()%unitSize),
                unitWidth = (int) (Game.getInstance().getGameWidth()/unitSize)+3,
                unitHeight = (int) (Game.getInstance().getGameHeight()/unitSize)+3;
            for(int x = 0; x<unitWidth; x++){
                int xPix = (int) ((x-1)*unitSize)-xOff;
                for(int y = 0; y < unitHeight; y++){
                    int yPix = (int) ((y-1)*unitSize)-yOff;
                    g.setColor((Math.abs(unitX+x)+Math.abs(unitY+y))%2==0?color1.get():color2.get());
                    g.fillRect(xPix, yPix, (int) unitSize, (int)unitSize);
                }
            }
        }

        @Override
        public void tick() {
            color1.tick();
            color2.tick();
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

        @Override
        public void mouseScrolled(MouseWheelEvent e) {

        }

        public Position getPosition() {
            return position;
        }
    }
}