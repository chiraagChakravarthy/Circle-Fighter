package circle_fighter.user_lobby;

import circle_fighter.gameState.UserLobbyState;
import circle_fighter.menu.base.Menu;
import circle_fighter.menu.base.component.ListOption;

public class GameModeMenu extends Menu {
    private UserLobbyState state;

    public GameModeMenu(UserLobbyState state) {
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
                state.setMenu(12);
                break;
            case 1:
                break;
            case 2:
                state.setMenu(0);
        }
    }

    @Override
    protected void constructMenu() {
        addComponent(new ListOption("SinglePlayer", getLowestY()+Menu.COMPONENT_SPACING, this));
        ListOption multiPlayerOption = new ListOption("MultiPlayer", getLowestY()+Menu.COMPONENT_SPACING, this);
        multiPlayerOption.setEnabled(false);
        addComponent(multiPlayerOption);
        addComponent(new ListOption("Back", getLowestY()+Menu.COMPONENT_SPACING, this));
    }

    @Override
    protected String getTitle() {
        return "Play";
    }

    @Override
    protected void onOpen(int selectedOption) {
        super.onOpen(selectedOption);

    }
}
