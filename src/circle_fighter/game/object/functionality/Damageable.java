package circle_fighter.game.object.functionality;

import circle_fighter.game.object.GameObject;

public interface Damageable{
    <T extends GameObject & Damaging> boolean damage(T damagingObject);
}