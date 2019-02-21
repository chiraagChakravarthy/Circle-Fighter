package circle_fighter.menu.options;

import circle_fighter.engine.Game;
import circle_fighter.engine.Window;
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
        if(selectedOption==2){
            Window window = Game.getInstance().getWindow();
            window.setFullScreen(!window.isFullScreen());
        }
        else {
            exit();
        }
    }

    @Override
    protected void onExit(int selectedOption) {
        switch (selectedOption){
            case 0:
                break;
            case 1:
                break;
            case 3:
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
        addComponent(new ListOption("Toggle Fullscreen", getLowestY() + Menu.COMPONENT_SPACING, this));
        addComponent(new ListOption("Back", getLowestY() + Menu.COMPONENT_SPACING, this));
    }

    @Override
    protected String getTitle() {
        return "Options";
    }
}