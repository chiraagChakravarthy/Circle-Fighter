package circle_fighter.game.object.functionality;

public interface Damaging extends Polarized {
    void onKill(Damageable damageable);
}