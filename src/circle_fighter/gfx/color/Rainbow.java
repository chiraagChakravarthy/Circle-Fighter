package circle_fighter.gfx.color;

import circle_fighter.file.DataStorage;

public class Rainbow extends DynamicColor {
    private int stage;
    private float rate;
    public Rainbow(float brightness, float rate) {
        super(255, 0, 0, brightness);
        this.rate = rate;
    }

    public Rainbow(DataStorage storage){
        rate = storage.getFloat(0);
    }

    public Rainbow(){
        this(1, 1);
    }

    @Override
    public void tick() {
        switch (stage){
            case 0:
                r+=rate;
                b-=rate;
                if(r>=255){
                    r=255;
                    b=0;
                    stage++;
                }
                break;
            case 1:
                g+=2*rate;
                if(g>=255){
                    g=255;
                    stage++;
                }
                break;
            case 2:
                r-=2*rate;
                if(r<0){
                    r=0;
                    stage++;
                }
                break;
            case 3:
                g-=rate;
                b+=rate;
                if(g<=0){
                    g=0;
                    b=255;
                    stage=0;
                }
        }
    }

    @Override
    public void reset() {
        r=255;
        g=0;
        b=0;
        stage = 0;
    }

    public int getStage() {
        return stage;
    }
}
