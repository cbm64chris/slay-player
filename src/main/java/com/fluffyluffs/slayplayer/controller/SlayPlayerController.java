package com.fluffyluffs.slayplayer.controller;

import com.fluffyluffs.slayplayer.controller.station.Station;
import com.fluffyluffs.slayplayer.controller.station.StationListCell;
import com.gluonhq.attach.storage.StorageService;
import com.gluonhq.attach.util.Services;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Function;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.MediaPlayer;
import javafx.util.StringConverter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * FXML Controller class
 *
 * @author cl011157
 */
public class SlayPlayerController implements Initializable {

    private final static Logger LOG = LogManager.getLogger();

    @FXML
    private StackPane stackPane;
    @FXML
    private AnchorPane playerPane;
    @FXML
    private ListView<Station> stations;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

//        stations.setCellFactory(lv -> {
//            TextFieldListCell<Station> cell = new TextFieldListCell<Station>();
//
//            
//            StringConverter<Station> converter = new StringConverter<Station>() {
//                @Override
//                public String toString(Station t) {
//                    return t.getStationName();
//                }
//
//                @Override
//                public Station fromString(String string) {
//                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//                }
//
//            };
//
//            cell.setConverter(converter);
//            
//            return cell;
//        });

        stations.setCellFactory(lv -> {
            return new StationListCell();
        });
        

        try {
            //Station lbc = new Station("LBC", new URL("http://media-ice.musicradio.com/LBCLondon.m3u"));
            Station slayRadio = new Station("Slay Radio", new URL("https://www.slayradio.org/tune_in.php/128kbps/slayradio.128.m3u"), "slay_radio");
            stations.getItems().addAll(slayRadio);
            stations.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Station>() {
                @Override
                public void changed(ObservableValue<? extends Station> ov, Station t, Station t1) {
                    Optional.ofNullable(t).ifPresent(c -> pauseStation(t));
                    playStation(t1);
                }
            });
        } catch (MalformedURLException ex) {
            LOG.always().log(ex.getMessage());
        }
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
        Optional.ofNullable(station).map(Station::getMediaPlayer)
                .filter(player -> player.getStatus().equals(MediaPlayer.Status.PLAYING))
                .orElseThrow(() -> new IllegalStateException("There was no Media Player")).pause();
    }

    private void stopStation(Station station) {
        Optional.ofNullable(station).ifPresent(Station::stopMedia);
    }

    //    ABSOLUTE80(new MediaPlayer(new Media("http://icy-e-bab-04-cr.sharp-stream.com/absolute80s.mp3"))),
//    SLAY(new MediaPlayer(new Media("https://relay4.slayradio.org)")),
//    HEART80(new MediaPlayer(new Media("http://media-ice.musicradio.com/Heart80sMP3"))),
//    LBC(new MediaPlayer(new Media("http://media-ice.musicradio.com/LBCLondon.m3u"))),
//    MOUNTAINCHILL(new MediaPlayer(new Media("http://edge3.peta.live365.net/b58063_128mp3")));
}
