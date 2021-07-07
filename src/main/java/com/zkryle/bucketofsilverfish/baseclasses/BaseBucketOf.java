package com.zkryle.bucketofsilverfish.baseclasses;

import com.zkryle.bucketofsilverfish.core.configfiles.Config;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.EndermiteEntity;
import net.minecraft.entity.monster.SilverfishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.Items;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.*;
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
    public ActionResultType onItemUseFirst( ItemStack stack , ItemUseContext context ){
        World level = context.getLevel();
        PlayerEntity player = context.getPlayer();
        Hand hand = context.getHand();
        RayTraceResult rtresult = getPlayerPOVHitResult( level , player , RayTraceContext.FluidMode.NONE );
        BlockRayTraceResult brtresult = (BlockRayTraceResult) rtresult;
        BlockPos pos = brtresult.getBlockPos();
        Direction direction = brtresult.getDirection();
        BlockPos pos1 = pos.relative( direction );
        ItemStack itemstack = player.getItemInHand( hand );
        if(level.mayInteract( player , pos ) && player.getItemInHand( hand ) != Items.BUCKET.getDefaultInstance() && player.mayUseItemAt( pos1 , direction , itemstack )){
            switch(type){
                case "silverfish":
                    SilverfishEntity entity = new SilverfishEntity( EntityType.SILVERFISH , level );
                    entity.setPos( pos.getX() + 0.5f , pos.getY() + 1 , pos.getZ() + 0.5f );
                    if(!player.isCreative()){
                        player.setItemInHand( hand , Items.BUCKET.getDefaultInstance() );
                    }
                    player.playSound( SoundEvents.BUCKET_EMPTY , 1.0f , 1.0f );
                    if(!level.isClientSide()){
                        level.addFreshEntity( entity );
                        return ActionResultType.SUCCESS;
                    }
                    break;
                case "endermite":
                    int rand = (int) (Math.random() * 5);
                    EndermiteEntity entity2 = new EndermiteEntity( EntityType.ENDERMITE , level );
                    entity2.setPos( pos.getX() + 0.5f , pos.getY() + 1 , pos.getZ() + 0.5f );
                    if(!player.isCreative()){
                        if(rand == 3 || !Config.endermite_escaping.get()){
                            player.setItemInHand( hand , Items.BUCKET.getDefaultInstance() );
                            player.playSound( SoundEvents.BUCKET_EMPTY , 1.0f , 1.0f );
                            if(!level.isClientSide()){
                                level.addFreshEntity( entity2 );
                                return ActionResultType.SUCCESS;
                            }
                        }else{
                            level.playSound( null , pos , SoundEvents.ENDERMAN_TELEPORT , SoundCategory.HOSTILE , 1.0f , 1.0f );
                            level.addParticle( ParticleTypes.PORTAL , pos.getX() + 0.5f , pos.getY() + 1 , pos.getZ() + 0.5f , 0 , 0 , 0 );
                            player.setItemInHand( hand , Items.BUCKET.getDefaultInstance() );
                        }
                    } else {
                        player.playSound( SoundEvents.BUCKET_EMPTY , 1.0f , 1.0f );
                        if(!level.isClientSide()){
                            level.addFreshEntity( entity2 );
                            return ActionResultType.SUCCESS;
                        }
                    }
                    break;
            }
        }
        return ActionResultType.PASS;
    }
}
