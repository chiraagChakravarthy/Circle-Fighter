package circle_fighter.menu;

import circle_fighter.gameState.MenuState;
import circle_fighter.menu.base.Menu;
import circle_fighter.menu.base.StateMenu;
import circle_fighter.menu.base.component.ListOption;

public class HelpMenu extends StateMenu {

    public HelpMenu(MenuState state) {
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
                break;
            case 1:
                state.setMenu(1);
                break;
        }
    }

    @Override
    public void onOpen(int selectedOption) {
        super.onOpen(selectedOption);
    }

    @Override
    protected void constructMenu() {
        addComponent(new ListOption("Enemies", getLowestY() + Menu.COMPONENT_SPACING, this));
        addComponent(new ListOption("Back", getLowestY() + Menu.COMPONENT_SPACING, this));
    }

    @Override
    protected String getTitle() {
        return "Help";
    }
}
