package com.fluffyluffs.slayplayer.controller.util;

import com.gluonhq.attach.storage.StorageService;
import com.gluonhq.attach.util.Services;
import java.util.function.Function;

/**
 *
 * Utils
 */
public class Utils {
    
    public static <T> T getStorage(Function<StorageService, T> storageService) {
        return Services.get(StorageService.class).map(storageService::apply).orElse(null);
    }
    
}
