package circle_fighter.user_lobby;

import circle_fighter.gameState.UserLobbyState;
import circle_fighter.menu.base.Menu;
import circle_fighter.menu.base.component.ListOption;

public class SinglePlayerMenu extends Menu {
    private UserLobbyState state;
    public SinglePlayerMenu(UserLobbyState state) {
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
                state.getGsm().setGameState(1);
                break;
            case 1:
                //TODO make wave state before initial release
                break;
            case 2:
                state.setMenu(11);
                break;
        }
    }

    @Override
    protected void constructMenu() {
        addComponent(new ListOption("Levels", getLowestY()+Menu.COMPONENT_SPACING, this));
        addComponent(new ListOption("Waves", getLowestY()+Menu.COMPONENT_SPACING, this));
        getOptions().get(1).setEnabled(false);
        addComponent(new ListOption("Back", getLowestY()+Menu.COMPONENT_SPACING, this));
    }

    @Override
    protected String getTitle() {
        return "SinglePlayer";
    }
}
