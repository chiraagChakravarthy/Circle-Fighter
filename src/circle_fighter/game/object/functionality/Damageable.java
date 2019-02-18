package circle_fighter.game.object.functionality;

public interface Damageable extends Bounded, Polarized{
    boolean damage(DirectlyDamaging damagingObject);
    float expOnDeath();
}