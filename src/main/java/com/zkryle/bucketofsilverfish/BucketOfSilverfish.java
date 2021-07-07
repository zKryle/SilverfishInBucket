package com.zkryle.bucketofsilverfish;

import com.zkryle.bucketofsilverfish.core.ItemInit;
import com.zkryle.bucketofsilverfish.core.configfiles.Config;
import com.zkryle.bucketofsilverfish.events.ModEvents;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("bucketofsilverfish")
public class BucketOfSilverfish{

    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "bucketofsilverfish";

    public BucketOfSilverfish() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        ItemInit.ITEMS.register(bus);

        ModLoadingContext.get().registerConfig( ModConfig.Type.COMMON, Config.SPEC, "bucket-of-silverfish-common.toml" );

        MinecraftForge.EVENT_BUS.register(new ModEvents());
        MinecraftForge.EVENT_BUS.register(this);
    }
}
