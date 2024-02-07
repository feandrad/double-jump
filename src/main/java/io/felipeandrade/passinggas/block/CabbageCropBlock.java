package io.felipeandrade.passinggas.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Blocks;
import net.minecraft.block.CropBlock;
import net.minecraft.item.ItemConvertible;

import static io.felipeandrade.passinggas.DoubleJumpMod.CABBAGE_SEEDS;

public class CabbageCropBlock extends CropBlock {

    public CabbageCropBlock() {
        super(FabricBlockSettings.copy(Blocks.WHEAT).nonOpaque());
    }

    @Override
    public ItemConvertible getSeedsItem() {
        return CABBAGE_SEEDS;
    }
}
