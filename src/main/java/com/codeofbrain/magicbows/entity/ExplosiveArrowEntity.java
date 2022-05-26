package com.codeofbrain.magicbows.entity;

import com.codeofbrain.magicbows.MagicBows;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;

public class ExplosiveArrowEntity extends AbstractArrow {
    private boolean isAlreadyHit = false;
    public ExplosiveArrowEntity(EntityType<? extends AbstractArrow> entityType, Level level) {
        super(entityType, level);
    }

    public ExplosiveArrowEntity(double x, double y, double z, Level level) {
        this(EntityType.ARROW, level);
        this.setPos(x, y, z);
    }

    public ExplosiveArrowEntity(LivingEntity livingEntity, Level level) {
        this(livingEntity.getX(), livingEntity.getEyeY() - (double) 0.1F, livingEntity.getZ(), level);
        this.setOwner(livingEntity);
        if (livingEntity instanceof Player) {
            this.pickup = AbstractArrow.Pickup.ALLOWED;
        }
    }

    @Override
    protected void onHit(HitResult hitResult) {
        super.onHit(hitResult);

        MagicBows.LOGGER.info("ExplosiveArrowEntity Client");

        if (!this.level.isClientSide() && !this.isAlreadyHit) {
            MagicBows.LOGGER.info("ExplosiveArrowEntity Server");

            this.level.explode(null, hitResult.getLocation().x, hitResult.getLocation().y,
                    hitResult.getLocation().z, 5f, Explosion.BlockInteraction.BREAK);
            this.isAlreadyHit = true;
        }
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(Items.ARROW);
    }
}
