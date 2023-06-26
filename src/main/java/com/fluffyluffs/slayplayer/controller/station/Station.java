package com.fluffyluffs.slayplayer.controller.station;

import java.net.URL;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/** Station */
public class Station {

  private final String stationName;
  private final URL stationURL;
  private final String image;
  private MediaPlayer mediaPlayer = null;

  public Station(String stationName, URL stationURL, String image) {
    this.stationName = stationName;
    this.stationURL = stationURL;
    this.image = image;
  }

  public String getStationName() {

    return stationName;
  }

  public URL getStationURL() {

    return stationURL;
  }

  public Image getImage() {

    return new Image(Station.class.getResourceAsStream("/images/" + this.image + ".png"));
  }

  /**
   * Create the Media Player
   *
   * @return {@link MediaPlayer}
   */
  public MediaPlayer startMedia() {
    mediaPlayer = new MediaPlayer(new Media(stationURL.toExternalForm()));
    mediaPlayer.setAutoPlay(true);

    return mediaPlayer;
  }

  /**
   * Get the Media Player, may be null
   *
   * @return {@link MediaPlayer}
   */
  public MediaPlayer getMediaPlayer() {

    return mediaPlayer;
  }

  /** Destroys the Media Player */
  public void stopMedia() {
    mediaPlayer.dispose();
    mediaPlayer = null;
  }
}
