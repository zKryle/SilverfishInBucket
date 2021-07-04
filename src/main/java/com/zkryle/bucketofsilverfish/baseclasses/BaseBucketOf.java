package com.zkryle.bucketofsilverfish.baseclasses;

import com.zkryle.bucketofsilverfish.core.ItemInit;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.SilverfishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceContext;
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
        BlockPos pos = getPlayerPOVHitResult( level , player , RayTraceContext.FluidMode.NONE ).getBlockPos();
        if (player.getMainHandItem().getItem() != Items.BUCKET.getItem() && !player.getMainHandItem().isEmpty()){
            switch(type){
                case "silverfish":
                    if(player.getMainHandItem().getItem() == ItemInit.BUCKET_OF_SILVERFISH.get()){
                        SilverfishEntity entity = new SilverfishEntity( EntityType.SILVERFISH , level );
                        entity.setPos( pos.getX() - 0.2f , pos.getY() + 1 , pos.getZ() - 0.2f );
                        level.addFreshEntity( entity );
                    }
            }
        }
        return ActionResult.sidedSuccess( !player.abilities.instabuild ? new ItemStack( Items.BUCKET ) : new ItemStack( ItemInit.BUCKET_OF_SILVERFISH.get() ) , level.isClientSide() );
    }
}
