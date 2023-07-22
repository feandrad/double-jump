package io.felipeandrade.doublejump;

import io.felipeandrade.doublejump.block.CabbageCropBlock;
import io.felipeandrade.doublejump.effect.FlatulenceEffect;
import io.felipeandrade.doublejump.item.CabbageItem;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.Block;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.potion.Potion;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class DoubleJumpMod implements ModInitializer {

    public static final String MOD_ID = "doublejump";

    public static final StatusEffect FLATULENCE_EFFECT = new FlatulenceEffect();
    public static final Potion FLATULENCE_POTION = new Potion(new StatusEffectInstance(FLATULENCE_EFFECT));

    public static final SoundEvent SOUND_FART = new SoundEvent(new Identifier(MOD_ID, "fart"));

    public static final Block CABBAGE_CROP = new CabbageCropBlock();

    public static final Item CABBAGE = new CabbageItem();
    public static final Item CABBAGE_SEEDS = new AliasedBlockItem(CABBAGE_CROP, new Item.Settings().group(ItemGroup.MISC));

    @Override
    public void onInitialize() {
        // Register the custom packet handler
        ServerPlayNetworking.registerGlobalReceiver(new Identifier(MOD_ID, "play_sound"), (server, player, handler, buf, responseSender) -> {
            server.execute(() -> {
                player.playSound(DoubleJumpMod.SOUND_FART, 1.0F, 1.0F);
            });
        });

        Registry.register(Registry.STATUS_EFFECT, FlatulenceEffect.ID, FLATULENCE_EFFECT);

        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "cabbage"), CABBAGE);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "cabbage_seed"), CABBAGE_SEEDS);

        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "cabbage_crop"), CABBAGE_CROP);

        Registry.register(Registry.POTION, new Identifier(MOD_ID, "flatulence"), FLATULENCE_POTION);

        Registry.register(Registry.SOUND_EVENT, new Identifier(MOD_ID, "fart"), SOUND_FART);
    }
}
