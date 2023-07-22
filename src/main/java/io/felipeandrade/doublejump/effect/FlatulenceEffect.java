package io.felipeandrade.doublejump.effect;

import io.felipeandrade.doublejump.DoubleJumpMod;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Identifier;

public class FlatulenceEffect extends StatusEffect {
    public static final Identifier ID = new Identifier(DoubleJumpMod.MOD_ID, "flatulence"); // Ensure consistent identifier

    public FlatulenceEffect() {
        super(StatusEffectCategory.NEUTRAL, 0x660066); // Set the particle tint to a darker purple (hex code: #660066)
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        // Custom effect logic for the "Flatulence" potion effect
        // For example, you can add more behavior here when the effect is active over time.
        // In this case, we'll randomly play the fart sound with a delay.
        playFartSound(entity);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        // You can add custom logic here to determine when the effect should apply an update effect.
        // For example, you can return true here to apply the effect on every tick while the potion is active.
        // In this case, we'll return false, as we are handling the effect in the applyUpdateEffect method.

        int i = 50 >> amplifier;
        if (i > 0) {
            return duration % i == 0;
        } else {
            return true;
        }
    }

    private void playFartSound(LivingEntity entity) {
        entity.world.playSound(null, entity.getX(), entity.getY(), entity.getZ(), DoubleJumpMod.SOUND_FART, SoundCategory.NEUTRAL, 1.0F, 1.0F);
    }
}
