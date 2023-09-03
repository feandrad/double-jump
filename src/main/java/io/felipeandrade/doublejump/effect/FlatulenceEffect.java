package io.felipeandrade.doublejump.effect;

import io.felipeandrade.doublejump.DoubleJumpMod;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;

import java.util.Random;

public class FlatulenceEffect extends StatusEffect {

    private Random random = new Random();

    public FlatulenceEffect() {
        super(StatusEffectCategory.NEUTRAL, 0x660066); // Set the particle tint to a darker purple (hex code: #660066)
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (entity instanceof PlayerEntity && entity.isSneaking()) {
            playFartSound(entity);
        } else {
            // 10% chance to call playFartSound(entity);
            if (random.nextDouble() <= 0.1) {
                playFartSound(entity);
            }
        }
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        int i = random.nextInt(25, 31) >> amplifier;
        if (i > 0) {
            return duration % i == 0;
        } else {
            return true;
        }
    }

    private void playFartSound(LivingEntity entity) {
        if (entity.getWorld().isClient) return;

        entity.getWorld().playSound(null, entity.getBlockPos(), DoubleJumpMod.FART_SOUND_EVENT, SoundCategory.NEUTRAL, 1.0F, random.nextFloat(0.3F, 1.3F));
    }
}
