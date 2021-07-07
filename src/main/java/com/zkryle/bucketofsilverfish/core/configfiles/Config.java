package com.zkryle.bucketofsilverfish.core.configfiles;

import net.minecraftforge.common.ForgeConfigSpec;

public final class Config{
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Boolean> endermite_escaping;

    static{
        BUILDER.push( "Bucket of Silverfish Config" );

        endermite_escaping = BUILDER.comment( "Determines if the endermite should escape or not. Default value is true" )
                .define( "Endermite Escaping", true );

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
