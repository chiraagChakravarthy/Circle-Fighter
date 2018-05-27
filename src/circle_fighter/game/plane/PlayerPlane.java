package circle_fighter.game.plane;

import circle_fighter.game.object.position.Position;
import circle_fighter.game.object.objects.Player;
import circle_fighter.game.plane.bounds.PlaneBounds;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public abstract class PlayerPlane extends Plane {
    private Player player;
    private Position initialPlayerPosition;
    public PlayerPlane(Position initialPlayerPosition, PlaneBounds bounds){
        super(bounds);
        this.initialPlayerPosition = initialPlayerPosition;
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
        if(player != null) player.keyPressed(k);
    }

    @Override
    public void keyReleased(int k) {
        if(player != null) player.keyReleased(k);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        player.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        player.mouseReleased(e);
    }

    @Override
    public void mouseScrolled(MouseWheelEvent e) {
        player.mouseScrolled(e);
    }

    @Override
    public void reset() {
        super.reset();
        player = new Player(initialPlayerPosition.clone(), this);
    }
}
