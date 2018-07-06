package circle_fighter.menu.base.component;

import circle_fighter.engine.Game;
import circle_fighter.game.ui.ProgressBar;
import circle_fighter.gfx.color.DynamicColor;

import java.awt.*;

public class ProgressBarComponent extends MenuComponent {
    private ProgressBar bar;
    private int x, y;

    public ProgressBarComponent(int x, int y, int width, int height, DynamicColor borderColor, DynamicColor barColor){
        this.x = x;
        this.y = y;
        bar = new ProgressBar(x+super.transitionOffset, y, width, height, barColor, borderColor);
    }

    public ProgressBarComponent(int y, int width, int height, DynamicColor borderColor, DynamicColor barColor){
        this((Game.getInstance().getGameWidth()-width)/2, y, width, height, borderColor, barColor);
    }

    @Override
    public void tick() {
        super.tick();
        bar.tick();
        bar.setX(x+transitionOffset);
        bar.setY(y+scrollingOffset);
    }

    @Override
    public void render(Graphics2D g) {
        bar.render(g);
    }

    public void setProgress(float progress){
        bar.setProgress(progress);
    }

    @Override
    public Rectangle getArea(boolean onScreen) {
        return new Rectangle(x, onScreen?y+scrollingOffset:y, (int) bar.getWidth(), (int) bar.getHeight());
    }
}
