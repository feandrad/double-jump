package io.felipeandrade.doublejump.item;

import io.felipeandrade.doublejump.DoubleJumpMod;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class Cabbage extends Item {
    public Cabbage() {
        super(new Item.Settings()
                .group(ItemGroup.FOOD)
                .food(new FoodComponent.Builder()
                        .hunger(3) // Adjust the hunger value as desired
                        .saturationModifier(0.6F)
                        .statusEffect(new StatusEffectInstance(DoubleJumpMod.FLATULENCE_EFFECT, 600, 0), 1.0F)
                        .build()));
    }
}