package gameState;

import game.TestPlane;

import java.awt.*;
import java.awt.event.MouseEvent;

public class PlayState extends GameState {
    private TestPlane plane;
    public PlayState(GameStateManager gsm) {
        super(gsm);
        plane = new TestPlane();
    }

    @Override
    public void render(Graphics2D g) {
        plane.render(g);
    }

    @Override
    public void tick() {
        plane.tick();
    }

    @Override
    public void keyPressed(int k) {
        plane.keyPressed(k);
    }

    @Override
    public void keyReleased(int k) {
        plane.keyReleased(k);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        plane.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        plane.mouseReleased(e);
    }
}
