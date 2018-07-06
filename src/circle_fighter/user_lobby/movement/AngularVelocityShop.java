package circle_fighter.user_lobby.movement;

import circle_fighter.gameState.UserLobbyState;
import circle_fighter.gfx.color.SolidColor;
import circle_fighter.user.User;
import circle_fighter.user.element.UserMovement;
import circle_fighter.user_lobby.UpgradeMenu;

public class AngularVelocityShop extends UpgradeMenu {
    private static final int INDEX = UserMovement.VEL_ANG;
    public AngularVelocityShop(UserLobbyState state) {
        super(state, "Angular Velocity", new SolidColor(0, 0, 255));
    }

    @Override
    protected int getTransactionState() {
        return gameState.getUser().getUpgradePoints()<=0?INSUFFICIENT:gameState.getUser().getMovement().getValue(INDEX).maxed()?MAXED:INCOMPLETE;
    }

    @Override
    protected void onUpgrade() {
        User user = gameState.getUser();
        user.getMovement().set(INDEX, user.getMovement().get(INDEX)+1);
        user.setUpgradePoints(user.getUpgradePoints()-1);
    }

    @Override
    public float progress() {
        User user = gameState.getUser();
        return user.getMovement().get(INDEX)/user.getMovement().getValue(INDEX).getMax();
    }

    @Override
    public int levels() {
        return Math.round(gameState.getUser().getMovement().get(INDEX));
    }

    @Override
    protected void onExit(int selectedOption) {
        gameState.setMenu(2);
    }
}
