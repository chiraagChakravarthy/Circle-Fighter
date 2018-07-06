package circle_fighter.user_lobby.health;

import circle_fighter.gameState.UserLobbyState;
import circle_fighter.gfx.color.SolidColor;
import circle_fighter.user.User;
import circle_fighter.user.element.UserHealth;
import circle_fighter.user_lobby.UpgradeMenu;

public class MaxHealthShop extends UpgradeMenu {
    private static final int INDEX = UserHealth.MAX_HEALTH;
    public MaxHealthShop(UserLobbyState state) {
        super(state, "Max Health", new SolidColor(255, 0, 0));
    }

    @Override
    protected int getTransactionState() {
        return gameState.getUser().getUpgradePoints()<=0?INSUFFICIENT:gameState.getUser().getMovement().getValue(INDEX).maxed()?MAXED:INCOMPLETE;
    }

    @Override
    protected void onUpgrade() {
        User user = gameState.getUser();
        user.getHealth().set(INDEX, user.getHealth().get(INDEX)+1);
        user.setUpgradePoints(user.getUpgradePoints()-1);
    }

    @Override
    public float progress() {
        User user = gameState.getUser();
        return user.getMovement().get(INDEX)/user.getMovement().getValue(INDEX).getMax();
    }

    @Override
    public int levels() {
        return Math.round(gameState.getUser().getHealth().get(INDEX));
    }

    @Override
    protected void onExit(int selectedOption) {
        gameState.setMenu(7);
    }
}
