package circle_fighter.menu;

import circle_fighter.gameState.MenuState;
import circle_fighter.menu.base.Menu;
import circle_fighter.menu.base.StateMenu;
import circle_fighter.menu.base.component.Option;

public class HelpMenu extends StateMenu {

    public HelpMenu(MenuState state) {
        super(state, "Help");
        addComponent(new Option("Enemies", getLowestY() + Menu.COMPONENT_SPACING, this));
        addComponent(new Option("Back", getLowestY() + Menu.COMPONENT_SPACING, this));
    }

    @Override
    protected void onSelect(int selectedOption) {
        exit();
    }

    @Override
    protected void onExit(int selectedOption) {

        switch (selectedOption){
            case 0:
                break;
            case 1:
                state.setMenu(1);
        }
    }

    @Override
    public void onOpen(int selectedOption) {

    }
}
