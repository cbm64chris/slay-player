package com.fluffyluffs.slayplayer.controller.station;

import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;

/** StationListCell */
public class StationListCell extends ListCell<Station> {

  public StationListCell() {
    super();
  }

  @Override
  protected void updateItem(Station station, boolean empty) {
    super.updateItem(station, empty);

    setText(null);
    if (empty) {
      setGraphic(null);
    } else {

//      station
//          .getNowPlaying()
//          .addListener(
//              new ChangeListener<String>() {
//                @Override
//                public void changed(ObservableValue<? extends String> ov, String t, String t1) {
//                  Platform.runLater(() -> setText(t1));
//                }
//              });

      ImageView img = new ImageView(station.getImg());
      img.setFitHeight(100d);
      img.setFitWidth(100d);

      setGraphic(img);
    }
  }
}
