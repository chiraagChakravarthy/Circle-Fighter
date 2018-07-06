package circle_fighter.user_lobby;

import circle_fighter.engine.Game;
import circle_fighter.gameState.UserLobbyState;
import circle_fighter.gfx.color.DynamicColor;
import circle_fighter.gfx.color.SolidColor;
import circle_fighter.menu.base.Menu;
import circle_fighter.menu.base.component.ListOption;
import circle_fighter.menu.base.component.ProgressBarComponent;
import circle_fighter.menu.base.component.TextBox;

public abstract class UpgradeMenu extends Menu{
    protected static final int MAXED = 0, INCOMPLETE = 1, INSUFFICIENT = 2;

    protected UserLobbyState gameState;
    protected TextBox levelDisplay;
    protected ProgressBarComponent levels;
    private DynamicColor baseColor;
    private String title;
    private ListOption buyButton;
    private int transactionState;

    public UpgradeMenu(UserLobbyState gameState, String title, DynamicColor baseColor){
        super(gameState.getKeyBinds(), true);
        this.gameState = gameState;
        this.baseColor = baseColor;
        this.title = title;
    }

    @Override
    protected void onSelect(int selectedOption) {
        System.out.println(selectedOption);
        switch (selectedOption){
            case 0:
                if(transactionState == INCOMPLETE) {
                    onUpgrade();
                    transactionState = getTransactionState();
                    levels.setProgress(progress());
                    buyButton.setEnabled(transactionState !=MAXED);
                    buyButton.setColor(transactionState == INSUFFICIENT?new SolidColor(255, 0, 0):new SolidColor(255, 255, 255));
                    levelDisplay.setMessage("Level: " + levels());
                }
                break;
            case 1:
                exit();
                break;
        }
    }

    protected abstract int getTransactionState();

    protected abstract void onUpgrade();

    public abstract float progress();

    public abstract int levels();

    @Override
    protected void onOpen(int selectedOption) {
        super.onOpen(selectedOption);
        transactionState = getTransactionState();
        buyButton.setEnabled(transactionState !=MAXED);
        buyButton.setColor(transactionState == INSUFFICIENT?new SolidColor(255, 0, 0):new SolidColor(255, 255, 255));
        levels.setProgress(progress());
    }

    @Override
    protected void constructMenu() {
        levelDisplay = new TextBox("Level: " + levels(), getLowestY()+Menu.COMPONENT_SPACING, 300, new SolidColor(255, 255, 255), true, false);
        addComponent(levelDisplay);
        levels = new ProgressBarComponent(getLowestY()+Menu.COMPONENT_SPACING, Game.getInstance().getGameWidth()/4, Game.getInstance().getGameWidth()/16, baseColor.clone(), baseColor.clone().setBrightness(0.5f));
        addComponent(levels);
        buyButton = new ListOption("Upgrade", getLowestY()+Menu.COMPONENT_SPACING, this);
        addComponent(buyButton);
        addComponent(new ListOption("Back", getLowestY()+Menu.COMPONENT_SPACING, this));
    }

    @Override
    protected String getTitle() {
        return title;
    }
}