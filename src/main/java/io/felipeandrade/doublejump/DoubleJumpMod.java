package io.felipeandrade.doublejump;

import io.felipeandrade.doublejump.block.CabbageCropBlock;
import io.felipeandrade.doublejump.effect.FlatulenceEffect;
import io.felipeandrade.doublejump.item.Cabbage;
import io.felipeandrade.doublejump.mixin.BrewingRecipeRegistryMixin;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.Block;
import net.minecraft.block.ComposterBlock;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.potion.Potions;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static io.felipeandrade.doublejump.item.FlatulencePotion.FLATULENCE_POTION;
import static io.felipeandrade.doublejump.item.FlatulencePotion.LONG_FLATULENCE_POTION;

public class DoubleJumpMod implements ModInitializer {

    public static final String MOD_ID = "doublejump";

    public static final StatusEffect FLATULENCE_EFFECT = new FlatulenceEffect();
    public static final Identifier FART_SOUND_ID = new Identifier(MOD_ID, "fart");
    public static SoundEvent FART_SOUND_EVENT = new SoundEvent(FART_SOUND_ID);

    public static final Block CABBAGE_CROP = new CabbageCropBlock();

    public static final Item CABBAGE = new Cabbage();
    public static final Item CABBAGE_SEEDS = new AliasedBlockItem(CABBAGE_CROP, new Item.Settings().group(ItemGroup.MISC));

    @Override
    public void onInitialize() {
        Registry.register(Registry.SOUND_EVENT, FART_SOUND_ID, FART_SOUND_EVENT);

        Registry.register(Registry.STATUS_EFFECT, new Identifier(DoubleJumpMod.MOD_ID, "flatulence"), FLATULENCE_EFFECT);

        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "cabbage"), CABBAGE);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "cabbage_seeds"), CABBAGE_SEEDS);

        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "cabbage_crop"), CABBAGE_CROP);

        Registry.register(Registry.POTION, new Identifier(MOD_ID, "flatulence"), FLATULENCE_POTION);
        Registry.register(Registry.POTION, new Identifier(MOD_ID, "long_flatulence"), LONG_FLATULENCE_POTION);

        BrewingRecipeRegistryMixin.register(Potions.AWKWARD, CABBAGE, FLATULENCE_POTION);
        BrewingRecipeRegistryMixin.register(FLATULENCE_POTION, Items.REDSTONE, LONG_FLATULENCE_POTION);

        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(CABBAGE_SEEDS, 0.3F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(CABBAGE, 0.65F);
    }
}

