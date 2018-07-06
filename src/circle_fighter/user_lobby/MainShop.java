package circle_fighter.user_lobby;

import circle_fighter.gameState.UserLobbyState;
import circle_fighter.menu.base.Menu;
import circle_fighter.menu.base.component.ListOption;

public class MainShop extends Menu {
    private UserLobbyState state;

    public MainShop(UserLobbyState state) {
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
                state.setMenu(2);
                break;
            case 1:
                state.setMenu(7);
                break;
            case 2:
                break;
            default:
                state.setMenu(0);
                break;
        }
    }

    @Override
    protected void constructMenu() {
        addComponent(new ListOption("Movement", getLowestY()+Menu.COMPONENT_SPACING, this));
        addComponent(new ListOption("Health", getLowestY()+Menu.COMPONENT_SPACING, this));
        addComponent(new ListOption("Turret", getLowestY()+Menu.COMPONENT_SPACING, this));
        addComponent(new ListOption("Back", getLowestY()+Menu.COMPONENT_SPACING, this));
        getOptions().get(2).setEnabled(false);
    }

    @Override
    protected String getTitle() {
        return "Shop";
    }
}
