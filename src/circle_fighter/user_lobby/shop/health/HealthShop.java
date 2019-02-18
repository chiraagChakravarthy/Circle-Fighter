package circle_fighter.user_lobby.shop.health;

import circle_fighter.gameState.UserLobbyState;
import circle_fighter.menu.base.Menu;
import circle_fighter.menu.base.component.ListOption;

public class HealthShop extends Menu {
    private UserLobbyState state;

    public HealthShop(UserLobbyState state) {
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
                state.setMenu(8);
                break;
            case 1:
                state.setMenu(9);
                break;
            case 2:
                state.setMenu(10);
                break;
            default:
                state.setMenu(1);
                break;
        }
    }

    @Override
    protected void constructMenu() {
        addComponent(new ListOption("Max Health", getLowestY()+Menu.COMPONENT_SPACING, this));
        addComponent(new ListOption("Regeneration", getLowestY()+Menu.COMPONENT_SPACING, this));
        addComponent(new ListOption("Invulnerability", getLowestY()+Menu.COMPONENT_SPACING, this));
        addComponent(new ListOption("Back", getLowestY()+Menu.COMPONENT_SPACING, this));
    }

    @Override
    protected String getTitle() {
        return "Health";
    }
}
