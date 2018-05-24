package circle_fighter.level.menu;

import circle_fighter.gameState.LevelState;
import circle_fighter.menu.base.Menu;

public class DeathMenu extends Menu {
    private LevelState state;
    public DeathMenu(LevelState state) {
        super("You Died!");
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
