package com.fluffyluffs.slayplayer.controller.station;

import com.luff.slayplayer.controller.metadata.MetaDataThread;
import java.net.URL;
import javafx.beans.property.StringProperty;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/** Station */
public class StationInstance {

  private MediaPlayer mediaPlayer = null;
  private final MetaDataThread metaDataThread;
  private final URL url;

  public StationInstance(URL url) {
    this.url = url;
    this.metaDataThread = new MetaDataThread(url.toExternalForm());
  }

  /**
   * Create the Media Player
   *
   * @return {@link MediaPlayer}
   */
  public MediaPlayer startMedia() {
    mediaPlayer = new MediaPlayer(new Media(url.toExternalForm()));
    mediaPlayer.setAutoPlay(true);
    metaDataThread.startMetaDataThread();

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
    metaDataThread.stopMetaDataThread();
    metaDataThread.setNowPlaying("");
  }

  public StringProperty getNowPlaying() {

    return metaDataThread.getNowPlaying();
  }
}
