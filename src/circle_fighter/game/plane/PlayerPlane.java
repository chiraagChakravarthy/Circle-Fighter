package circle_fighter.game.plane;

import circle_fighter.game.object.position.Position;
import circle_fighter.game.object.objects.turret.Player;
import circle_fighter.game.plane.bounds.PlaneBounds;

import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class PlayerPlane extends Plane {
    private Player player;
    public PlayerPlane(Position playerPosition, PlaneBounds bounds){
        super(bounds);
        player = new Player(playerPosition, this);
    }

    @Override
    public void tick() {
        super.tick();
        Position offset = bounds.getPositionOffset(player.getPosition());
        Position.setXOffset(offset.getX());
        Position.setYOffset(offset.getY());
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public void render(Graphics2D g) {
        super.render(g);
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
        player.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        player.mouseReleased(e);
    }
}
