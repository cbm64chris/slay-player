package com.fluffyluffs.slayplayer.controller;

import com.fluffyluffs.slayplayer.controller.station.StationInstance;
import com.fluffyluffs.slayplayer.controller.station.StationListCell;
import com.fluffyluffs.slayplayer.controller.station.Station;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Comparator;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.beans.value.ChangeListener;
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
  public void initialize(URL url, ResourceBundle rb) {

    stations.setCellFactory((ListView<Station> p) -> new StationListCell());

    stations
        .getItems()
        .addAll(
            Stream.of(Station.values())
                .sorted(Comparator.comparing(Station::getName))
                .collect(Collectors.toSet()));

    stations
        .getSelectionModel()
        .selectedItemProperty()
        .addListener(
            new ChangeListener<Station>() {
              @Override
              public void changed(
                  ObservableValue<? extends Station> ov,
                  Station currentStation,
                  Station newStation) {
                Optional.ofNullable(currentStation)
                    .ifPresent(
                        station -> {
                          // destroy
                        });

                try {

                  playStation(new StationInstance(new URL(newStation.getUrl())));

                } catch (MalformedURLException ex) {
                  throw new RuntimeException(ex.getLocalizedMessage(), ex);
                }
              }
            });

    //    stations
    //        .getSelectionModel()
    //        .selectedItemProperty()
    //        .addListener(
    //            (ObservableValue<? extends StationInstance> ov,
    //                StationInstance t,
    //                StationInstance t1) -> {
    //              Optional.ofNullable(t)
    //                  .ifPresent(
    //                      c -> {
    //                        stopStation(t);
    //                      });
    //
    //              playStation(t1);
    //            });
  }

  private StationInstance createStation(Station station) {
    try {
      return new StationInstance(station.getName(), new URL(station.getUrl()), station.getImg());

    } catch (MalformedURLException ex) {
      LOG.always().log(ex.getMessage());
    }

    return null;
  }

  private void playStation(StationInstance station) {
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

  private void pauseStation(StationInstance station) {
    Optional.ofNullable(station)
        .map(StationInstance::getMediaPlayer)
        .filter(player -> player.getStatus().equals(MediaPlayer.Status.PLAYING))
        .orElseThrow(() -> new IllegalStateException("There was no Media Player"))
        .stop();
  }

  private void stopStation(StationInstance station) {
    Optional.ofNullable(station).ifPresent(StationInstance::stopMedia);
  }
}
