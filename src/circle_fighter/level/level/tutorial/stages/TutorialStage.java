package circle_fighter.level.level.tutorial.stages;

import circle_fighter.functionaliy.Renderable;
import circle_fighter.functionaliy.Updatable;
import circle_fighter.functionaliy.UserInputListener;
import circle_fighter.level.level.tutorial.Tutorial;

public abstract class TutorialStage implements Renderable, Updatable, UserInputListener {
    protected Tutorial tutorial;
    public TutorialStage(Tutorial tutorial){
        this.tutorial = tutorial;
    }

    public abstract void reset();
}
