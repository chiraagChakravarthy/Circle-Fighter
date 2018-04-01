package circle_fighter.game.level;

import circle_fighter.engine.Game;
import circle_fighter.game.object.position.Position;
import circle_fighter.game.plane.PlayerPlane;
import circle_fighter.game.plane.bounds.BoundedBounds;

import java.awt.*;
import java.awt.event.MouseEvent;

public class LevelPlane extends PlayerPlane {
    public LevelPlane() {
        super(new Position(0, 0), new BoundedBounds(-Game.getInstance().getGameWidth()/2, -Game.getInstance().getGameHeight()/2, Game.getInstance().getGameWidth(), Game.getInstance().getGameHeight()));
    }

    @Override
    public void tick() {
        super.tick();

    }

    @Override
    public void render(Graphics2D g) {
        super.render(g);

    }

    @Override
    public void keyPressed(int k) {
        super.keyPressed(k);
    }

    @Override
    public void keyReleased(int k) {
        super.keyReleased(k);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
    }
}
