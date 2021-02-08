package com.luff.slayplayer.controller.metadata;

/**
 * Stores meta data.
 */
public class MetaData {
    private final String metaData;
    private final String error;

    public MetaData(String metaData, String error) {
        this.metaData = metaData;
        this.error = error;
    }

    public String getMetaData() {
        return metaData;
    }
    
    public String getErrorMsg() {
        return error;
    }
    
}
