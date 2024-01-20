package io.felipeandrade.doublejump;

import io.felipeandrade.doublejump.block.CabbageCropBlock;
import io.felipeandrade.doublejump.effect.FlatulenceEffect;
import io.felipeandrade.doublejump.item.Cabbage;
import io.felipeandrade.doublejump.item.MilkBottle;
import io.felipeandrade.doublejump.item.SplashPotionOfCleansing;
import io.felipeandrade.doublejump.mixin.BrewingRecipeRegistryMixin;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.minecraft.block.ComposterBlock;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.potion.Potions;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

import static io.felipeandrade.doublejump.item.FlatulencePotion.FLATULENCE_POTION;
import static io.felipeandrade.doublejump.item.FlatulencePotion.LONG_FLATULENCE_POTION;

public class DoubleJumpMod implements ModInitializer {

    public static final String MOD_ID = "doublejump";

    public static final StatusEffect FLATULENCE_EFFECT = new FlatulenceEffect();
    public static final Identifier FART_SOUND_ID = new Identifier(MOD_ID, "fart");
    public static SoundEvent FART_SOUND_EVENT = SoundEvent.of(FART_SOUND_ID);

    public static final Block CABBAGE_CROP = new CabbageCropBlock();

    public static final Item MILK_BOTTLE = new MilkBottle(new Item.Settings().maxCount(16));
    public static final Item SPLASH_POTION_OF_CLEANSING = new SplashPotionOfCleansing(new Item.Settings().maxCount(16));
    public static final Item CABBAGE = new Cabbage();
    public static final Item CABBAGE_SEEDS = new AliasedBlockItem(CABBAGE_CROP, new Item.Settings());

    @Override
    public void onInitialize() {
        Registry.register(Registries.SOUND_EVENT, FART_SOUND_ID, FART_SOUND_EVENT);

        Registry.register(Registries.STATUS_EFFECT, new Identifier(DoubleJumpMod.MOD_ID, "flatulence"), FLATULENCE_EFFECT);

        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "cabbage"), CABBAGE);
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "cabbage_seeds"), CABBAGE_SEEDS);

        Registry.register(Registries.BLOCK, new Identifier(MOD_ID, "cabbage_crop"), CABBAGE_CROP);

        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "milk_bottle"), MILK_BOTTLE);
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "splash_potion_cleansing"), SPLASH_POTION_OF_CLEANSING);
        Registry.register(Registries.POTION, new Identifier(MOD_ID, "flatulence"), FLATULENCE_POTION);
        Registry.register(Registries.POTION, new Identifier(MOD_ID, "long_flatulence"), LONG_FLATULENCE_POTION);

        BrewingRecipeRegistryMixin.register(Potions.AWKWARD, CABBAGE, FLATULENCE_POTION);
        BrewingRecipeRegistryMixin.register(FLATULENCE_POTION, Items.REDSTONE, LONG_FLATULENCE_POTION);

        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(CABBAGE_SEEDS, 0.3F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(CABBAGE, 0.65F);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(content -> {
            content.add(CABBAGE_SEEDS);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(content -> {
            content.add(CABBAGE);
            content.add(MILK_BOTTLE);
            content.add(SPLASH_POTION_OF_CLEANSING);
        });
    }
}

