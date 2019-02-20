package circle_fighter.user_lobby.shop.turret;

import circle_fighter.gameState.UserLobbyState;
import circle_fighter.gfx.color.SolidColor;
import circle_fighter.user.User;
import circle_fighter.user.element.turret.BasicUserTurret;
import circle_fighter.user_lobby.shop.UpgradeMenu;

public class SwivelAngleShop extends UpgradeMenu {
    private static final int INDEX = BasicUserTurret.MAX_ANG;
    public SwivelAngleShop(UserLobbyState state) {
        super(state, "Swivel Angle", new SolidColor(0, 255, 255));
    }

    @Override
    protected int getTransactionState() {
        return gameState.getUser().getUpgradePoints()<=0?INSUFFICIENT:gameState.getUser().getTurrets().getTurretAt(0).getValue(INDEX).maxed()?MAXED:INCOMPLETE;
    }

    @Override
    protected void onUpgrade() {
        User user = gameState.getUser();
        user.getTurrets().getTurretAt(0).set(INDEX, user.getTurrets().getTurretAt(0).get(INDEX)+1);
        user.setUpgradePoints(user.getUpgradePoints()-1);
    }

    @Override
    public float progress() {
        User user = gameState.getUser();
        return user.getTurrets().getTurretAt(0).get(INDEX)/user.getTurrets().getTurretAt(0).getValue(INDEX).getMax();
    }

    @Override
    public int levels() {
        return Math.round(gameState.getUser().getTurrets().getTurretAt(0).get(INDEX));
    }

    @Override
    protected void onExit(int selectedOption) {
        gameState.setMenu(13);
    }
}
