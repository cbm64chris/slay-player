package com.fluffyluffs.slayplayer.controller;

import com.fluffyluffs.slayplayer.controller.station.Station;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
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
    @FXML
    private Button addRadioStation;
    @FXML
    private Button musicArt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        stations.setCellFactory(lv -> {
            TextFieldListCell<Station> cell = new TextFieldListCell<Station>();

            StringConverter<Station> converter = new StringConverter<Station>() {
                @Override
                public String toString(Station t) {
                    return t.getStationName();
                }

                @Override
                public Station fromString(String string) {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

            };

            cell.setConverter(converter);
            return cell;
        });

        try {
            Station lbc = new Station("LBC", new URL("http://media-ice.musicradio.com/LBCLondon.m3u"));
            Station slayRadio = new Station("Slay Radio", new URL("https://www.slayradio.org/tune_in.php/128kbps/slayradio.128.m3u"));
            stations.getItems().addAll(lbc, slayRadio);

            stations.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Station>() {
                @Override
                public void changed(ObservableValue<? extends Station> ov, Station t, Station t1) {
                    Optional.ofNullable(t).ifPresent(c -> stopStation(t));
                    playStation(t1);
                }
            });
        } catch (MalformedURLException ex) {
            LOG.always().log(ex.getMessage());
        }
    }

    private void playStation(Station station) {
        LOG.always().log(String.format("Starting ", station.getStationName()));
        MediaPlayer mediaPlayer = station.getMediaPlayer();
        
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setOnError(() -> LOG.always().log("Opps"));
        
        MediaPlayer.Status status = mediaPlayer.getStatus();

        switch (status) {
            case PAUSED:
            case HALTED:
            case STOPPED:
                mediaPlayer.play();
                break;
            case UNKNOWN:
                LOG.always().log("Not ready!");
                mediaPlayer.play();
                break;
        }

    }

    private void stopStation(Station station) {
        LOG.always().log(String.format("Stopping ", station.getStationName()));
        station.getMediaPlayer().reset();
    }

    //    ABSOLUTE80(new MediaPlayer(new Media("http://icy-e-bab-04-cr.sharp-stream.com/absolute80s.mp3"))),
//    SLAY(new MediaPlayer(new Media("https://relay4.slayradio.org)")),
//    HEART80(new MediaPlayer(new Media("http://media-ice.musicradio.com/Heart80sMP3"))),
//    LBC(new MediaPlayer(new Media("http://media-ice.musicradio.com/LBCLondon.m3u"))),
//    MOUNTAINCHILL(new MediaPlayer(new Media("http://edge3.peta.live365.net/b58063_128mp3")));
}
