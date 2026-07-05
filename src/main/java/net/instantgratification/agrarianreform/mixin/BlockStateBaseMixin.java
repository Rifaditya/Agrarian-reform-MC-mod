package net.instantgratification.agrarianreform.mixin;

import net.instantgratification.agrarianreform.util.GrowthHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.world.level.block.state.BlockBehaviour;

@Mixin(BlockBehaviour.BlockStateBase.class)
public abstract class BlockStateBaseMixin {

    @Shadow
    public abstract Block getBlock();

    @Inject(method = "randomTick", at = @At("HEAD"), cancellable = true)
    private void agrarianreform$modifyRandomTickSpeed(ServerLevel level, BlockPos pos, RandomSource random, CallbackInfo ci) {
        if (GrowthHelper.handleRandomTick(this.getBlock(), (net.minecraft.world.level.block.state.BlockState) (Object) this, level, pos, random)) {
            ci.cancel();
        }
    }
}
