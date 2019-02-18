package circle_fighter.level.menu;

import circle_fighter.gfx.color.SolidColor;
import circle_fighter.gameState.LevelState;
import circle_fighter.menu.base.Menu;
import circle_fighter.menu.base.component.ListOption;
import circle_fighter.menu.base.component.Option;

import java.util.ArrayList;

public class LevelMenu extends Menu {
    private LevelState state;
    private int levels;
    public LevelMenu(LevelState state, int levels) {
        super(state.getKeyBinds(), false);
        this.state = state;
        this.levels = levels;
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    protected void onSelect(int selectedOption) {
        exit();
    }

    @Override
    protected void onExit(int selectedOption) {
        if(selectedOption==levels+1){
            state.getGsm().setGameState(2);
        }
        else {
            state.setState(LevelState.SubState.LEVEL);
            state.setLevel(selectedOption);
        }
    }

    @Override
    protected void onOpen(int selectedOption) {
        super.onOpen(selectedOption);
        ArrayList<Option> options = getOptions();
        for (int i = 0; i < options.size(); i++) {
            if(i<state.getHighestLevel()) {
                options.get(i).setEnabled(true);
                ((ListOption)options.get(i)).setColor(new SolidColor(255, 255, 255));
            }
            else if(i==state.getHighestLevel()){
                options.get(i).setEnabled(true);
                ((ListOption)options.get(i)).setColor(new SolidColor(255, 0, 0));
            }
            else {
                ((ListOption)options.get(i)).setColor(new SolidColor(255, 255, 255));
                options.get(i).setEnabled(i==options.size()-1);
            }
        }
        ((ListOption)options.get(options.size()-1)).setColor(new SolidColor(255, 255, 255));
    }

    @Override
    protected void constructMenu() {
        addComponent(new ListOption("Tutorial", getLowestY()+Menu.COMPONENT_SPACING, this));
        for (int i = 0; i < levels; i++) {
            addComponent(new ListOption("Level " + (i+1), getLowestY()+Menu.COMPONENT_SPACING, this));
        }
        addComponent(new ListOption("Back", getLowestY()+COMPONENT_SPACING, this));
    }

    @Override
    protected String getTitle() {
        return "Level Select";
    }
}