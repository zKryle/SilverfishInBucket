package com.zkryle.bucketofsilverfish.core;

import com.zkryle.bucketofsilverfish.BucketOfSilverfish;
import com.zkryle.bucketofsilverfish.baseclasses.BaseBucketOf;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit{
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create( ForgeRegistries.ITEMS, BucketOfSilverfish.MOD_ID );

    public static final RegistryObject<Item> BUCKET_OF_SILVERFISH = ITEMS.register( "bucket_of_silverfish" ,
            () -> new BaseBucketOf( new Item.Properties().tab( ItemGroup.TAB_MISC ).stacksTo( 1 ), 600, "silverfish" ) );

    public static final RegistryObject<Item> BUCKET_OF_ENDERMITE = ITEMS.register( "bucket_of_endermite" ,
            () -> new BaseBucketOf( new Item.Properties().tab( ItemGroup.TAB_MISC ).stacksTo( 1 ), 1200, "endermite" ) );
}
