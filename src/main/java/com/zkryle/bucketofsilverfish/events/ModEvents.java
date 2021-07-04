package com.zkryle.bucketofsilverfish.events;

import com.zkryle.bucketofsilverfish.core.ItemInit;
import net.minecraft.entity.monster.EndermiteEntity;
import net.minecraft.entity.monster.SilverfishEntity;
import net.minecraft.item.Items;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ModEvents{

    private static boolean used = false;

    @SubscribeEvent
    public void onRightClick( PlayerInteractEvent.EntityInteract event ){
        if(event.getItemStack().getItem() == Items.BUCKET.getItem()){
            if(event.getTarget().getEntity() instanceof SilverfishEntity){
                event.getTarget().remove();
                event.getPlayer().playSound( SoundEvents.BUCKET_FILL_FISH, 1.0f, 1.0f );
                event.getPlayer().inventory.setItem( event.getPlayer().inventory.selected ,
                        ItemInit.BUCKET_OF_SILVERFISH.get().getDefaultInstance() );
            } else if (event.getTarget().getEntity() instanceof EndermiteEntity){
                event.getTarget().remove();
                event.getPlayer().playSound( SoundEvents.BUCKET_FILL_FISH, 1.0f, 1.0f );
                event.getPlayer().inventory.setItem( event.getPlayer().inventory.selected ,
                        ItemInit.BUCKET_OF_ENDERMITE.get().getDefaultInstance() );
            }
        }
    }
}
