package circle_fighter.menu;

import circle_fighter.gfx.color.SolidColor;
import circle_fighter.engine.Game;
import circle_fighter.gameState.MenuState;
import circle_fighter.menu.base.Menu;
import circle_fighter.menu.base.component.ListOption;
import circle_fighter.menu.base.component.TextBox;

public class CreditsMenu extends Menu {
    private MenuState state;
    public CreditsMenu(MenuState state) {
        super(state.getKeyBinds(), false);
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
        super.onOpen(selectedOption);
    }

    @Override
    protected void constructMenu() {
        addComponent(new TextBox("All credit for the elements and concepts present in \nthis game go to Chiraag Chakravarthy",
                getLowestY()+Menu.COMPONENT_SPACING, Game.getInstance().getGameWidth()*2/3, new SolidColor(255, 255, 255), false, true));
        addComponent(new ListOption("Back", getLowestY()+Menu.COMPONENT_SPACING, this));
    }

    @Override
    protected String getTitle() {
        return "Credits";
    }
}
