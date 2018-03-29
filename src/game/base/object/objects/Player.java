package game.base.object.objects;

import game.base.Plane;
import game.base.object.Position;
import interfaces.UserInputListener;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Player extends ShootingObject implements UserInputListener {

    public Player(Position position, Plane plane) {
        super(position, 0.1, 0.05*Math.PI/180, 3, 3*Math.PI/180, plane);
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.RED);
        g.fillOval((int)position.getX()-25, (int)position.getY()-25, 50, 50);
        super.render(g);
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public void keyPressed(int k) {
        switch (k){
            case KeyEvent.VK_W:
                movement.setFront(true);
                break;
            case KeyEvent.VK_S:
                movement.setBack(true);
                break;
            case KeyEvent.VK_A:
                movement.setLeft(true);
                break;
            case KeyEvent.VK_D:
                movement.setRight(true);
                break;
            case KeyEvent.VK_SPACE:
                mainCannon.setFiring(true);
                break;
        }
    }

    @Override
    public void keyReleased(int k) {
        switch (k){
            case KeyEvent.VK_W:
                movement.setFront(false);
                break;
            case KeyEvent.VK_S:
                movement.setBack(false);
                break;
            case KeyEvent.VK_A:
                movement.setLeft(false);
                break;
            case KeyEvent.VK_D:
                movement.setRight(false);
                break;
            case KeyEvent.VK_SPACE:
                mainCannon.setFiring(false);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }
}
