package circle_fighter.game.plane.bounds;

import circle_fighter.engine.Game;
import circle_fighter.game.object.GameObject;
import circle_fighter.game.object.position.Position;

public class BoundedBounds extends PlaneBounds {
    private float x, y, width, height;
    public BoundedBounds(float x, float y, float width, float height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    @Override
    public float exceedsLeftBy(GameObject object) {
        float width = (float) object.getBound().outerBounds().getWidth();
        return Math.min(object.getPosition().getX()-width/2-x, 0);
    }

    @Override
    public float exceedsRightBy(GameObject object) {
        float width = (float) object.getBound().outerBounds().getWidth();
        return Math.max(object.getPosition().getX()+width/2-(x+this.width), 0);
    }

    @Override
    public float exceedsTopBy(GameObject object) {
        float height = (float) object.getBound().outerBounds().getHeight();
        return Math.max(object.getPosition().getY()+height/2-(y+this.height), 0);
    }

    @Override
    public float exceedsBottomBy(GameObject object) {
        float height = (float) object.getBound().outerBounds().getHeight();
        return Math.min(object.getPosition().getY()-height/2-y, 0);
    }

    @Override
    public Position getPositionOffset(Position targetOffset) {
        Position offset = targetOffset.clone(),
                min = targetOffset.clone().setX(targetOffset.getX()-Game.getInstance().getGameWidth()/2).setY(targetOffset.getY()-Game.getInstance().getGameHeight()/2);
        if(min.getX()<x){
            offset.setX(Game.getInstance().getWidth()/2+x);
        }
        if(min.getY()<y){
            offset.setY(Game.getInstance().getHeight()/2+y);
        }
        if(min.getX()+Game.getInstance().getGameWidth()>=x+width){
            offset.setX(x+width-Game.getInstance().getGameWidth()/2);
        }
        if(min.getY()+Game.getInstance().getGameHeight()>=y+height){
            offset.setY(y+height-Game.getInstance().getGameHeight()/2);
        }
        return offset;
    }
}
