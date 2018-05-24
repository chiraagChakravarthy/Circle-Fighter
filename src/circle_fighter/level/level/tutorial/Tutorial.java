package circle_fighter.level.level.tutorial;

import circle_fighter.engine.Game;
import circle_fighter.game.object.position.Position;
import circle_fighter.game.plane.PlayerPlane;
import circle_fighter.game.plane.bounds.BoundedBounds;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Tutorial extends PlayerPlane {
    private ArrayList<TextHint> hints;
    public Tutorial() {
        super(new Position(0, 0), new BoundedBounds(-Game.getInstance().getGameWidth()/2, -Game.getInstance().getHeight()/2, Game.getInstance().getWidth(), Game.getInstance().getGameHeight()));
        hints = new ArrayList<>();
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public void reset() {
        super.reset();
    }

    @Override
    public void keyPressed(int k) {
        switch (k){
            case KeyEvent.VK_ESCAPE:
                break;
            default:
                super.keyPressed(k);
        }
    }
}
