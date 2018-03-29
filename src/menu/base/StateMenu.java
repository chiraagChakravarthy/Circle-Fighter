package menu.base;

import gameState.MenuState;

public abstract class StateMenu extends Menu {
    protected MenuState state;
    public StateMenu(MenuState state, String title){
        super(title);
        this.state = state;
    }
}