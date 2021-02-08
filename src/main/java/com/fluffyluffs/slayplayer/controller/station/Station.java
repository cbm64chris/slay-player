package com.fluffyluffs.slayplayer.controller.station;

import java.net.URI;
import java.net.URL;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

/**
 *
 * Station
 */
public class Station {
    private final String stationName;
    private final URL stationURL;
    private MediaPlayer mediaPlayer = null;
    
    public Station(String stationName, URL stationURL) {
        this.stationName = stationName;
        this.stationURL = stationURL;
        
        Media media = new Media(stationURL.toExternalForm());
        
        mediaPlayer = new MediaPlayer(new Media(stationURL.toExternalForm()));
    }

    public String getStationName() {
        return stationName;
    }

    public URL getStationURL() {
        return stationURL;
    }
    
    public MediaPlayer getMediaPlayer() {
        
        return mediaPlayer;
    }
}
