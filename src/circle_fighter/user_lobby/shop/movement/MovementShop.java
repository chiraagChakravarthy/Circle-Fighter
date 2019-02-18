package circle_fighter.user_lobby.shop.movement;

import circle_fighter.gameState.UserLobbyState;
import circle_fighter.menu.base.Menu;
import circle_fighter.menu.base.component.ListOption;

public class MovementShop extends Menu {
    private UserLobbyState state;
    public MovementShop(UserLobbyState state) {
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
                state.setMenu(3);
                break;
            case 1:
                state.setMenu(4);
                break;
            case 2:
                state.setMenu(5);
                break;
            case 3:
                state.setMenu(6);
                break;
            case 4:
                state.setMenu(1);
                break;
        }
    }

    @Override
    protected void constructMenu() {
        addComponent(new ListOption("Velocity", getLowestY()+Menu.COMPONENT_SPACING, this));
        addComponent(new ListOption("Acceleration", getLowestY()+Menu.COMPONENT_SPACING, this));
        addComponent(new ListOption("Angular Velocity", getLowestY()+Menu.COMPONENT_SPACING, this));
        addComponent(new ListOption("Angular Acceleration", getLowestY()+Menu.COMPONENT_SPACING, this));
        addComponent(new ListOption("Back", getLowestY()+Menu.COMPONENT_SPACING, this));
    }

    @Override
    protected String getTitle() {
        return "Movement";
    }
}
