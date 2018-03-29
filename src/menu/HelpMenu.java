package menu;

import gameState.MenuState;
import menu.base.Menu;
import menu.base.StateMenu;
import menu.base.component.Option;

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
