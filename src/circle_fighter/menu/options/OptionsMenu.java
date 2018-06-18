package circle_fighter.menu.options;

import circle_fighter.gameState.MenuState;
import circle_fighter.menu.base.Menu;
import circle_fighter.menu.base.StateMenu;
import circle_fighter.menu.base.component.Option;

public class OptionsMenu extends StateMenu {
    public OptionsMenu(MenuState state) {
        super(state, "Options");
        addComponent(new Option("Controls",  getLowestY() + Menu.COMPONENT_SPACING, this));
        addComponent(new Option("Help", getLowestY() + Menu.COMPONENT_SPACING, this));
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
                state.setMenu(3);
                break;
            case 1:
                break;
            case 2:
                state.setMenu(0);
        }
    }

    @Override
    public void onOpen(int selectedOption) {

    }
}