package menu.base.component;

import menu.base.MenuProgression;

import java.awt.*;


public abstract class MenuComponent {

    public static final int TRANSITION_VELOCITY = 200,
    TRANSITION_DISTANCE= 2000;

    protected int transitionOffset, scrollingOffset;

    private MenuProgression state;

    public MenuComponent(){
        state = MenuProgression.EXITING;
        transitionOffset = TRANSITION_DISTANCE;
        scrollingOffset = 0;
    }

    public void tick() {
        if(state.equals(MenuProgression.ENTERING)){
            transitionOffset += TRANSITION_VELOCITY;
            if(transitionOffset>=0) advanceState();
        }
        else if(state.equals(MenuProgression.EXITING)){
            if(transitionOffset < TRANSITION_DISTANCE)transitionOffset += TRANSITION_VELOCITY;
            else transitionOffset = TRANSITION_DISTANCE;
        }
    }

    public void advanceState(){
        switch (state){
            case ENTERING:
                state = MenuProgression.DEFAULT;
                transitionOffset = 0;
                break;
            case DEFAULT:
                state = MenuProgression.EXITING;
                break;
            case EXITING:
                state = MenuProgression.ENTERING;
                transitionOffset = -TRANSITION_DISTANCE;
                break;
        }
    }

    public abstract void render(Graphics2D g);

    public boolean isFinishedEntering(){
        return state.equals(MenuProgression.DEFAULT);
    }

    public boolean isFinishedExiting(){
        return state.equals(MenuProgression.EXITING) && transitionOffset >= TRANSITION_DISTANCE;
    }

    public MenuProgression getState() {
        return state;
    }

    public abstract Rectangle getArea(boolean onScreen);

    public void setScrollingOffset(int scrollingOffset) {
        this.scrollingOffset = scrollingOffset;
    }

}