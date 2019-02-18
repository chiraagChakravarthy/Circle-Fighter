package circle_fighter.game.object.functionality;

public interface DirectlyDamaging extends Damaging, Bounded{
    float damage();
    long invulnerabilityTime();
}
