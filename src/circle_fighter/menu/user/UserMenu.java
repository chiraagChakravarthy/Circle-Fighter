package circle_fighter.menu.user;

import circle_fighter.gameState.MenuState;
import circle_fighter.menu.base.Menu;
import circle_fighter.menu.base.StateMenu;
import circle_fighter.menu.base.component.Option;

public class UserMenu extends StateMenu {

    public UserMenu(MenuState state) {
        super(state, "User");

        addComponent(new Option("New", getLowestY() + Menu.COMPONENT_SPACING, this));
        addComponent(new Option("Back", getLowestY()+Menu.COMPONENT_SPACING, this));
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
    }

    @Override
    protected void onOpen(int selectedOption) {

    }
}
