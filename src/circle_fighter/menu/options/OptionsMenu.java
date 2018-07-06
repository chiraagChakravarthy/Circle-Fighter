package circle_fighter.menu.options;

import circle_fighter.gameState.MenuState;
import circle_fighter.menu.base.Menu;
import circle_fighter.menu.base.StateMenu;
import circle_fighter.menu.base.component.ListOption;

public class OptionsMenu extends StateMenu {
    public OptionsMenu(MenuState state) {
        super(state, false);
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
        super.onOpen(selectedOption);
    }

    @Override
    protected void constructMenu() {
        addComponent(new ListOption("Controls",  getLowestY() + Menu.COMPONENT_SPACING, this));
        addComponent(new ListOption("Help", getLowestY() + Menu.COMPONENT_SPACING, this));
        addComponent(new ListOption("Back", getLowestY() + Menu.COMPONENT_SPACING, this));
    }

    @Override
    protected String getTitle() {
        return "Options";
    }
}