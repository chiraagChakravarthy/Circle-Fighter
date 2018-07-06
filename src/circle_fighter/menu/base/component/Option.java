package circle_fighter.menu.base.component;

public interface Option {
    void select();
    void setEnabled(boolean enabled);
    boolean isEnabled();
}
