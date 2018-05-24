package circle_fighter.menu;

import circle_fighter.gameState.MenuState;
import circle_fighter.menu.base.Menu;
import circle_fighter.menu.base.component.Option;
import circle_fighter.menu.base.component.TextBox;

public class CreditsMenu extends Menu {
    private MenuState state;
    public CreditsMenu(MenuState state) {
        super("Credits");
        addComponent(new TextBox("All credit for the elements and concepts present in this game go to Chiraag Chakravarthy", getLowestY()+Menu.COMPONENT_SPACING));
        addComponent(new Option("Back", 400, this));
        this.state = state;
    }

    @Override
    protected void onSelect(int selectedOption) {
        exit();
    }

    @Override
    protected void onExit(int selectedOption) {
        state.setMenu(0);
    }

    @Override
    protected void onOpen(int selectedOption) {

    }
}
