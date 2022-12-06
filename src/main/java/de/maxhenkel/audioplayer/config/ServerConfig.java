package de.maxhenkel.audioplayer.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class ServerConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<String> filebinUrl;
    public static final ForgeConfigSpec.ConfigValue<Integer> maxUploadSize;
    public static final ForgeConfigSpec.ConfigValue<Integer> uploadPermissionLevel;
    public static final ForgeConfigSpec.ConfigValue<Integer> applyToItemPermissionLevel;
    public static final ForgeConfigSpec.ConfigValue<Boolean> jukeboxHopperInteraction;
    public static final ForgeConfigSpec.ConfigValue<Boolean> jukeboxDispenserInteraction;
    public static final ForgeConfigSpec.ConfigValue<Integer> goatHornCooldown;
    public static final ForgeConfigSpec.ConfigValue<Double> musicDiscRange;
    public static final ForgeConfigSpec.ConfigValue<Double> goatHornRange;
    public static final ForgeConfigSpec.ConfigValue<Boolean> allowWavUpload;
    public static final ForgeConfigSpec.ConfigValue<Boolean> allowMp3Upload;
    public static final ForgeConfigSpec.ConfigValue<Integer> maxMusicDiscDuration;
    public static final ForgeConfigSpec.ConfigValue<Integer> maxGoatHornDuration;
    public static final ForgeConfigSpec.ConfigValue<Integer> cacheSize;

    static {
        filebinUrl = BUILDER.define("filebin_url", "https://filebin.net/");
        maxUploadSize = BUILDER.defineInRange("max_upload_size", 1000 * 1000 * 20, 1, Integer.MAX_VALUE);
        uploadPermissionLevel = BUILDER.defineInRange("upload_permission_level", 0, 0, Integer.MAX_VALUE);
        applyToItemPermissionLevel = BUILDER.defineInRange("apply_to_item_permission_level", 0, 0, Integer.MAX_VALUE);
        jukeboxHopperInteraction = BUILDER.define("jukebox_hopper_interaction", true);
        jukeboxDispenserInteraction = BUILDER.define("jukebox_dispenser_interaction", true);
        goatHornCooldown = BUILDER.defineInRange("goat_horn_cooldown", 140, 1, Short.MAX_VALUE);
        musicDiscRange = BUILDER.defineInRange("music_disc_range", 65D, 1D, Integer.MAX_VALUE);
        goatHornRange = BUILDER.defineInRange("goat_horn_range", 256D, 1D, Integer.MAX_VALUE);
        allowWavUpload = BUILDER.define("allow_wav_upload", true);
        allowMp3Upload = BUILDER.define("allow_mp3_upload", true);
        maxMusicDiscDuration = BUILDER.defineInRange("max_music_disc_duration", 60 * 5, 1, Integer.MAX_VALUE);
        maxGoatHornDuration = BUILDER.defineInRange("max_goat_horn_duration", 20, 1, Integer.MAX_VALUE);
        cacheSize = BUILDER.defineInRange("cache_size", 16, 0, Integer.MAX_VALUE);

        SPEC = BUILDER.build();
    }

}
