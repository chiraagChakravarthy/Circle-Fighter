package circle_fighter.game.object.objects.bot;

import circle_fighter.game.object.GameObject;
import circle_fighter.game.object.position.Position;
import circle_fighter.game.object.position.VelAccMovement;
import circle_fighter.game.plane.PlayerPlane;

import java.awt.*;

public class TestBot extends GameObject {
    private Rectangle bounds;
    private PlayerPlane plane;
    protected VelAccMovement movement;

    public TestBot(Position position, PlayerPlane plane, int team) {
        super(position, plane, BoundExitAction.BOUND, team);
        this.plane = plane;
        movement = new VelAccMovement(position, vector, 0.1, 3);
        bounds = new Rectangle((int)(position.getX()-10), (int)(position.getY()-10), 20, 20);
        movement.setFront(true);
    }

    @Override
    public void tick() {
        super.tick();
        bounds.setLocation((int)(position.getX()-10), (int)(position.getY()-10));
        Position playerPosition = plane.getPlayer().getPosition();
        double angle = position.angleTo(playerPosition);
        position.setRotation(angle);
        movement.tick();
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillOval((int)(position.getScrX()-10), (int) (position.getScrY()-10), 20, 20);
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }
}
