package circle_fighter.level.menu;

import circle_fighter.gameState.LevelState;
import circle_fighter.menu.base.Menu;
import circle_fighter.menu.base.component.Option;

public class DeathMenu extends Menu {
    private LevelState state;
    public DeathMenu(LevelState state) {
        super("You Died!", state.getKeyBinds());
        addComponent(new Option("Retry Level", getLowestY()+Menu.COMPONENT_SPACING, this));
        addComponent(new Option("Exit to Level Select", getLowestY()+Menu.COMPONENT_SPACING, this));
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
                state.setLevel(state.getLevel());
                state.setState(LevelState.SubState.LEVEL);
                break;
            case 1:
                state.setMenu(0);
                break;
        }
    }

    @Override
    protected void onOpen(int selectedOption) {

    }
}
