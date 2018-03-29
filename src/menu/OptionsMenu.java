package menu;

import gameState.MenuState;
import menu.base.Menu;
import menu.base.StateMenu;
import menu.base.component.Option;

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