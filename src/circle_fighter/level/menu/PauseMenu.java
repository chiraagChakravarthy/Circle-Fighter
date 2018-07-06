package circle_fighter.level.menu;

import circle_fighter.gameState.LevelState;
import circle_fighter.menu.base.Menu;
import circle_fighter.menu.base.MenuProgression;
import circle_fighter.menu.base.component.ListOption;

import java.awt.event.KeyEvent;

public class PauseMenu extends Menu {
    private LevelState state;
    public PauseMenu(LevelState state) {
        super(state.getKeyBinds(), false);
        this.state = state;
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
                break;
            case 1:
                state.getCurrentLevel().reset();
                state.setState(LevelState.SubState.LEVEL);
                break;
            case 2:
                state.setMenu(0);
                break;
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
    protected void constructMenu() {
        addComponent(new ListOption("Continue", getLowestY()+Menu.COMPONENT_SPACING, this));
        addComponent(new ListOption("Reset Level", getLowestY()+Menu.COMPONENT_SPACING, this));
        addComponent(new ListOption("Exit to Level Select", getLowestY()+Menu.COMPONENT_SPACING, this));
    }

    @Override
    protected String getTitle() {
        return "Paused";
    }

    @Override
    protected void onOpen(int selectedOption) {
        super.onOpen(selectedOption);
    }
}
