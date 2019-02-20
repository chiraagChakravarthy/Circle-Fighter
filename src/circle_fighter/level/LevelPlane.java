package circle_fighter.level;

import circle_fighter.engine.KeyBindManager;
import circle_fighter.game.object.position.UpdatingPosition;
import circle_fighter.game.plane.PlayerPlane;
import circle_fighter.game.plane.bounds.BoundedBounds;
import circle_fighter.gameState.LevelState;
import circle_fighter.user.UserManager;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public abstract class LevelPlane extends PlayerPlane {
    protected LevelState state;
    protected KeyBindManager keyBinds;
    public LevelPlane(int width, int height, LevelState state) {
        super(new UpdatingPosition(0, 0), new BoundedBounds(-width / 2, -height / 2, width, height), state.getKeyBinds(), state.getGsm().getUsers());
        keyBinds = state.getKeyBinds();
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
        if(k==keyBinds.get(KeyBindManager.PAUSE)){
            state.setState(LevelState.SubState.MENU);
            state.setMenu(3);
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
        state.advanceHighestLevel(state.getLevel());
    }

    protected void loose(){
        state.setState(LevelState.SubState.MENU);
        state.setMenu(1);
    }
}
