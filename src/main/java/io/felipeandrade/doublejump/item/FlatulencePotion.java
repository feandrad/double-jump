package io.felipeandrade.doublejump.item;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.potion.Potion;

import static io.felipeandrade.doublejump.DoubleJumpMod.FLATULENCE_EFFECT;


public class FlatulencePotion extends Potion {

    public static final Potion FLATULENCE_POTION = new FlatulencePotion(3600, 0);
    public static final Potion LONG_FLATULENCE_POTION = new FlatulencePotion(9600, 0);

    public FlatulencePotion(int duration, int amplifier) {
        super(new StatusEffectInstance(FLATULENCE_EFFECT, duration, amplifier));
    }
}
