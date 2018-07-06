package circle_fighter.menu.base.component;

import circle_fighter.gfx.color.DynamicColor;

import java.awt.*;

public abstract class Button extends MenuComponent implements Option {
    private DynamicColor[] lighter, darker;
    public Button(int x, int y, DynamicColor[] lighter, DynamicColor[] darker) {
        for(DynamicColor color : lighter){
            color.setBrightness(1);
        }
        for(DynamicColor color : darker){
            color.setBrightness(0.5f);
        }
    }

    @Override
    public void render(Graphics2D g) {

    }

    @Override
    public void select() {

    }

    @Override
    public void setEnabled(boolean enabled) {

    }
}
