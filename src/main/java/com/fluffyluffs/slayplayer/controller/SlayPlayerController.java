package com.fluffyluffs.slayplayer.controller;

import com.fluffyluffs.slayplayer.controller.station.Station;
import com.fluffyluffs.slayplayer.controller.station.StationListCell;
import com.fluffyluffs.slayplayer.controller.station.Stations;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Comparator;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.MediaPlayer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * FXML Controller class
 *
 * @author cl011157
 */
public class SlayPlayerController implements Initializable {

  private static final Logger LOG = LogManager.getLogger(SlayPlayerController.class);
  private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

  @FXML private StackPane stackPane;
  @FXML private AnchorPane playerPane;
  @FXML private ListView<Station> stations;

  /** Initializes the controller class. */
  @Override
  public void initialize(URL url, ResourceBundle rb) {

    stations.setCellFactory((ListView<Station> p) -> new StationListCell());

    stations
        .getItems()
        .addAll(
            Stream.of(Stations.values())
                .map(this::createStation)
                .sorted(Comparator.comparing(Station::getStationName))
                .collect(Collectors.toList()));
    stations
        .getSelectionModel()
        .selectedItemProperty()
        .addListener(
            (ObservableValue<? extends Station> ov, Station t, Station t1) -> {
              Optional.ofNullable(t)
                  .ifPresent(
                      c -> {
                        pauseStation(t);
//                        t.setNowPlaying("");
                      });

              playStation(t1);

//              scheduler.scheduleWithFixedDelay(
//                  () -> updateNowPlaying(t1), 500, 1000, TimeUnit.MILLISECONDS);
            });
  }

//  private void updateNowPlaying(Station station) {
//    Platform.runLater(
//        () ->
//            nowPlaying.setValue(
//                new ReadMetaData()
//                    .setUrl(station.getStationURL().toExternalForm())
//                    .parseMetaData()
//                    .getMetaData()));
//  }

  private Station createStation(Stations station) {
    try {
      return new Station(station.getName(), new URL(station.getUrl()), station.getImg());

    } catch (MalformedURLException ex) {
      LOG.always().log(ex.getMessage());
    }

    return null;
  }

  private void playStation(Station station) {
    MediaPlayer mediaPlayer = station.startMedia();

    switch (mediaPlayer.getStatus()) {
      case PAUSED:
      case HALTED:
      case STOPPED:
        mediaPlayer.play();
        LOG.always().log(String.format("Starting ", station.getStationName()));
        break;
    }
  }

  private void pauseStation(Station station) {
    Optional.ofNullable(station)
        .map(Station::getMediaPlayer)
        .filter(player -> player.getStatus().equals(MediaPlayer.Status.PLAYING))
        .orElseThrow(() -> new IllegalStateException("There was no Media Player"))
        .stop();
  }

  private void stopStation(Station station) {
    Optional.ofNullable(station).ifPresent(Station::stopMedia);
  }
}
