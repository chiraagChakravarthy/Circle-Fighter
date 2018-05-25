package circle_fighter.level.level.tutorial.stages;

import circle_fighter.level.level.tutorial.Tutorial;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class Stage2 extends TutorialStage {
    private final long duration;
    private long lastTime;

    public Stage2(Tutorial tutorial) {
        super(tutorial);
        duration = 5000;
        reset();
    }

    @Override
    public void reset() {
        lastTime = 0;
    }

    @Override
    public void render(Graphics2D g) {

    }

    @Override
    public void tick() {
        if(lastTime==0)
            lastTime = System.currentTimeMillis();
        else if((System.currentTimeMillis()-lastTime)>= duration){
            tutorial.advance();
        }
    }

    @Override
    public void keyPressed(int k) {

    }

    @Override
    public void keyReleased(int k) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseScrolled(MouseWheelEvent e) {

    }
}
