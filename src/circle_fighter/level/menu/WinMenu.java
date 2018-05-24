package circle_fighter.level.menu;

import circle_fighter.gameState.LevelState;
import circle_fighter.menu.base.Menu;

public class WinMenu extends Menu {
    private LevelState state;
    public WinMenu(LevelState state) {
        super("You Win!");
        this.state = state;
    }

    @Override
    protected void onSelect(int selectedOption) {

    }

    @Override
    protected void onExit(int selectedOption) {

    }

    @Override
    protected void onOpen(int selectedOption) {

    }
}
