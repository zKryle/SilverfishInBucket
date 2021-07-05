package com.zkryle.bucketofsilverfish.events;

import com.zkryle.bucketofsilverfish.baseclasses.BaseBucketOf;
import com.zkryle.bucketofsilverfish.core.ItemInit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EndermiteEntity;
import net.minecraft.entity.monster.SilverfishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ModEvents{

    private void fillBucket( ItemStack stack , PlayerEntity player , Entity entity, World level, Hand hand ){
        entity.remove();
        player.playSound( SoundEvents.BUCKET_FILL_FISH , 1.0f , 1.0f );
        player.setItemInHand( hand, stack );
    }

    @SubscribeEvent
    public void onRightClick( PlayerInteractEvent.EntityInteract event ){
        if(event.getPlayer().getItemInHand( event.getHand() ).getItem() == Items.BUCKET.getItem()){
            if(event.getTarget().getEntity() instanceof SilverfishEntity){
                    fillBucket( ItemInit.BUCKET_OF_SILVERFISH.get().getDefaultInstance() , event.getPlayer() , event.getTarget(), event.getWorld(), event.getHand() );
                event.setCanceled( true );
            }else if(event.getTarget().getEntity() instanceof EndermiteEntity){
                fillBucket( ItemInit.BUCKET_OF_ENDERMITE.get().getDefaultInstance() , event.getPlayer() , event.getTarget(), event.getWorld(), event.getHand());
                event.setCanceled( true );
            }
        }
    }
}
