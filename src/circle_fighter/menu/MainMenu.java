package circle_fighter.menu;


import circle_fighter.engine.Game;
import circle_fighter.gameState.GameStateManager;
import circle_fighter.gameState.MenuState;
import circle_fighter.menu.base.Menu;
import circle_fighter.menu.base.StateMenu;
import circle_fighter.menu.base.component.Option;

public class MainMenu extends StateMenu {
    public MainMenu(MenuState state){
        super(state, Game.getInstance().TITLE);
        addComponent(new Option("Play", getLowestY() + Menu.COMPONENT_SPACING, this));
        addComponent(new Option("Options", getLowestY() + Menu.COMPONENT_SPACING, this));
        addComponent(new Option("Quit", getLowestY() + Menu.COMPONENT_SPACING, this));
    }

    @Override
    protected void onSelect(int selectedOption) {
        exit();
    }

    @Override
    protected void onExit(int selectedOption) {
        switch (selectedOption){
            case 0:
                state.getGsm().setGameState(GameStateManager.PLAY_STATE);
                break;
            case 1:
                state.setMenu(1);
                break;
            case 2:
                Game.getInstance().stop();
                System.exit(0);
                break;
        }
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public void onOpen(int selectedOption) {

    }
}
