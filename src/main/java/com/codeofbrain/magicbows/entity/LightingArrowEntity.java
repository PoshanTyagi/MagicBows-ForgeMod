package com.codeofbrain.magicbows.entity;

import com.codeofbrain.magicbows.MagicBows;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class LightingArrowEntity extends AbstractArrow {
    public LightingArrowEntity(EntityType<? extends AbstractArrow> entityType, Level level) {
        super(entityType, level);
    }

    public LightingArrowEntity(double x, double y, double z, Level level) {
        this(EntityType.ARROW, level);
        this.setPos(x, y, z);
    }

    public LightingArrowEntity(LivingEntity livingEntity, Level level) {
        this(livingEntity.getX(), livingEntity.getEyeY() - (double) 0.1F, livingEntity.getZ(), level);
        this.setOwner(livingEntity);
        if (livingEntity instanceof Player) {
            this.pickup = AbstractArrow.Pickup.ALLOWED;
        }
    }

    @Override
    protected void onHit(HitResult hitResult) {
        super.onHit(hitResult);

        MagicBows.LOGGER.info("LightingArrowEntity Client");

        SoundEvent soundevent = SoundEvents.TRIDENT_HIT;

        if (!this.level.isClientSide()) {
            Vec3 blockPos = hitResult.getLocation();
            LightningBolt lightningbolt = EntityType.LIGHTNING_BOLT.create(this.level);
            lightningbolt.moveTo(blockPos);
            lightningbolt.setCause(this.getOwner() instanceof ServerPlayer ? (ServerPlayer) this.getOwner() : null);
            this.level.addFreshEntity(lightningbolt);
            soundevent = SoundEvents.TRIDENT_THUNDER;
        }

        this.playSound(soundevent, 1.0F, 1.0F);
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(Items.ARROW);
    }
}
