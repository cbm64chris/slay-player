package com.luff.slayplayer.controller.metadata;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/** MetaDataThread */
public class MetaDataThread {

  private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

  private final StringProperty nowPlayingProperty = new SimpleStringProperty("");
  private final ReadMetaData readMetaData;
  private final String url;

  public MetaDataThread(String url) {
    this.url = url;
    this.readMetaData = new ReadMetaData().setUrl(url);
  }

  public void setNowPlaying() {
    nowPlayingProperty.set(readMetaData.parseMetaData().getMetaData());
  }

  public void setNowPlaying(String str) {
    nowPlayingProperty.set(str);
  }

  public void startMetaDataThread() {
    scheduler.scheduleWithFixedDelay(this::setNowPlaying, 500, 1000, TimeUnit.MILLISECONDS);
  }

  public void stopMetaDataThread() {
    scheduler.shutdown();
  }

  public StringProperty getNowPlaying() {

    return nowPlayingProperty;
  }

  public String getUrl() {
    return url;
  }
}
