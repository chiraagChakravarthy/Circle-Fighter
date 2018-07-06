package circle_fighter.menu.user;

import circle_fighter.gameState.MenuState;
import circle_fighter.menu.base.Menu;
import circle_fighter.menu.base.StateMenu;
import circle_fighter.menu.base.component.ListOption;
import circle_fighter.user.UserManager;

public class UserMenu extends StateMenu {
    private final UserManager users;
    public UserMenu(MenuState state) {
        super(state, true);
        users = state.getGsm().getUsers();
    }

    @Override
    protected void onSelect(int selectedOption) {
        exit();
    }

    @Override
    protected void onExit(int selectedOption) {
        if(selectedOption==options()-1){
            state.setMenu(0);
        }
        else if(selectedOption==options()-2){
            state.setMenu(4);
        }
        else {
            users.setCurrentUser(selectedOption);
            state.getGsm().setGameState(2);
        }
    }

    @Override
    protected void onOpen(int selectedOption) {
        super.onOpen(selectedOption);
    }

    @Override
    protected void constructMenu() {
        for (int i = 0; i < users.amount(); i++) {
            addComponent(new ListOption(users.getUser(i).getName(), getLowestY()+Menu.COMPONENT_SPACING, this));
        }
        addComponent(new ListOption("New User", getLowestY()+Menu.COMPONENT_SPACING, this));
        addComponent(new ListOption("Back", getLowestY()+Menu.COMPONENT_SPACING, this));
    }

    @Override
    protected String getTitle() {
        return "User";
    }
}
