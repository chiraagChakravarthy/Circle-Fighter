package circle_fighter.game.object.functionality;

public interface Damaging extends Bounded, Polarized {
    float damage();
    long invulnerabilityTime();
    void onKill(Damageable damageable);
}