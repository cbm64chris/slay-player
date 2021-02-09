package com.fluffyluffs.slayplayer.controller.station;

import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;

/**
 *
 * StationListCell
 */
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
            setText(station.getStationName());
            setGraphic(new ImageView(station.getImage()));
        }
    }
    
    
    
}
