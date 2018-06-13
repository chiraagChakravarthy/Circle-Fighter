package circle_fighter.level;

import circle_fighter.game.object.position.Position;
import circle_fighter.game.plane.PlayerPlane;
import circle_fighter.game.plane.bounds.BoundedBounds;
import circle_fighter.gameState.LevelState;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public abstract class LevelPlane extends PlayerPlane {
    protected LevelState state;
    public LevelPlane(int width, int height, LevelState state) {
        super(new Position(0, 0), new BoundedBounds(-width/2, -height/2, width, height), state.getKeyBinds());
        this.state = state;
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
        switch (k){
            case KeyEvent.VK_ESCAPE:
                state.setState(LevelState.SubState.MENU);
                state.setMenu(3);
                break;
        }
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

    protected void win() {
        state.setState(LevelState.SubState.MENU);
        state.setMenu(2);
        state.setHighestLevel(state.getLevel()+1);
    }

    protected void loose(){
        state.setState(LevelState.SubState.MENU);
        state.setMenu(1);
    }
}
