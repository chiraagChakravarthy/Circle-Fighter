package game;

import engine.Game;
import game.base.Plane;
import game.base.object.GameObject;
import game.base.object.objects.Player;
import game.base.object.Position;

import java.awt.event.MouseEvent;

public class TestPlane extends Plane {
    private Player player;
    public TestPlane(){
        super();
        player = new Player(new Position(Game.getInstance().getGameWidth()/2, Game.getInstance().getHeight()/2), this);

    }

    @Override
    public boolean inBounds(GameObject object) {
        double x = object.getPosition().getX(), y = object.getPosition().getY();
        return x>=0&&y>=0&&x<=Game.getInstance().getWidth()&&y<=Game.getInstance().getHeight();
    }

    @Override
    public void keyPressed(int k) {
        player.keyPressed(k);
    }

    @Override
    public void keyReleased(int k) {
        player.keyReleased(k);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }
}
