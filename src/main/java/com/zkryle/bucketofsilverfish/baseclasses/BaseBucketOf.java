package com.zkryle.bucketofsilverfish.baseclasses;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.SilverfishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class BaseBucketOf extends Item{

    private final int burntime;
    private final String type;

    public BaseBucketOf( Properties properties , int burntime , String type ){
        super( properties );
        this.burntime = burntime;
        this.type = type;
    }

    @Override
    public boolean hasContainerItem( ItemStack stack ){
        return true;
    }

    @Override
    public int getBurnTime( ItemStack itemStack ){
        return this.burntime;
    }

    @Override
    public ItemStack getContainerItem( ItemStack itemStack ){
        return Items.BUCKET.getDefaultInstance();
    }

    @Override
    public ActionResult <ItemStack> use( World level , PlayerEntity player , Hand hand ){
        RayTraceResult rtresult = getPlayerPOVHitResult( level , player , RayTraceContext.FluidMode.NONE );
        BlockRayTraceResult brtresult = (BlockRayTraceResult) rtresult;
        BlockPos pos = brtresult.getBlockPos();
        Direction direction = brtresult.getDirection();
        BlockPos pos1 = pos.relative(direction);
        ItemStack itemstack = player.getItemInHand(hand);
        if (level.mayInteract(player, pos) && player.getItemInHand( hand ) != Items.BUCKET.getDefaultInstance() && player.mayUseItemAt( pos1, direction, itemstack )){
            switch(type){
                case "silverfish":
                    SilverfishEntity entity = new SilverfishEntity( EntityType.SILVERFISH, level);
                    entity.setPos( pos.getX()+0.2f, pos.getY()+1, pos.getZ()+0.2f);
                    if (!level.isClientSide()){
                        level.addFreshEntity( entity );
                        return ActionResult.success( Items.BUCKET.getDefaultInstance() );
                    }
                    break;
            }
        }
        return ActionResult.pass( itemstack );
    }
}
