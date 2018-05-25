package circle_fighter.level.menu;

import circle_fighter.gameState.LevelState;
import circle_fighter.menu.base.Menu;
import circle_fighter.menu.base.MenuProgression;
import circle_fighter.menu.base.component.Option;

import java.awt.event.KeyEvent;

public class PauseMenu extends Menu {
    private LevelState state;
    public PauseMenu(LevelState state) {
        super("Paused");
        this.state = state;
        addComponent(new Option("Continue", getLowestY()+Menu.COMPONENT_SPACING, this));
        addComponent(new Option("Reset Level", getLowestY()+Menu.COMPONENT_SPACING, this));
        addComponent(new Option("Exit to Level Select", getLowestY()+Menu.COMPONENT_SPACING, this));
    }

    @Override
    protected void onSelect(int selectedOption) {
        exit();
    }

    @Override
    protected void onExit(int selectedOption) {
        switch (selectedOption){
            case 0:
                state.setState(LevelState.SubState.LEVEL);
            case 1:
                state.getCurrentLevel().reset();
                state.setState(LevelState.SubState.LEVEL);
            case 2:
                state.setMenu(0);
        }
    }

    @Override
    public void keyPressed(int k) {
        switch (k){
            case KeyEvent.VK_ESCAPE:
                if(progression.equals(MenuProgression.DEFAULT)) {
                    setSelectedOption(0);
                    exit();
                }
        }
    }

    @Override
    protected void onOpen(int selectedOption) {

    }
}
