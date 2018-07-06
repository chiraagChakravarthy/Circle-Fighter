package circle_fighter.menu.base.component;

import circle_fighter.menu.base.MenuProgression;

import java.awt.*;


public abstract class MenuComponent {

    public static final int TRANSITION_VELOCITY = 200,
    TRANSITION_DISTANCE = 2000;

    protected int transitionOffset, scrollingOffset;

    private MenuProgression progression;

    public MenuComponent(){
        progression = MenuProgression.EXITING;
        transitionOffset = TRANSITION_DISTANCE;
        scrollingOffset = 0;
    }

    public void tick() {
        if(progression.equals(MenuProgression.ENTERING)){
            transitionOffset += TRANSITION_VELOCITY;
            if(transitionOffset>=0){
                transitionOffset = 0;
                advanceState();
            }
        }
        else if(progression.equals(MenuProgression.EXITING)){
            if(transitionOffset < TRANSITION_DISTANCE)transitionOffset += TRANSITION_VELOCITY;
            else transitionOffset = TRANSITION_DISTANCE;
        }
    }

    public void advanceState(){
        switch (progression){
            case ENTERING:
                progression = MenuProgression.DEFAULT;
                transitionOffset = 0;
                break;
            case DEFAULT:
                progression = MenuProgression.EXITING;
                break;
            case EXITING:
                progression = MenuProgression.ENTERING;
                transitionOffset = -TRANSITION_DISTANCE;
                break;
        }
    }

    public abstract void render(Graphics2D g);

    public boolean isFinishedEntering(){
        return progression.equals(MenuProgression.DEFAULT);
    }

    public boolean isFinishedExiting(){
        return progression.equals(MenuProgression.EXITING) && transitionOffset >= TRANSITION_DISTANCE;
    }

    public MenuProgression getProgression() {
        return progression;
    }

    public abstract Rectangle getArea(boolean onScreen);

    public void setScrollingOffset(int scrollingOffset) {
        this.scrollingOffset = scrollingOffset;
    }

}