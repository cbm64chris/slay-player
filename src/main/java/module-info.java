
module SlayPlayer {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.base;
    requires javafx.media;
    requires org.controlsfx.controls;
    requires javafx.graphicsEmpty;
    requires javafx.graphics;
    requires org.apache.logging.log4j;
    requires org.apache.logging.log4j.core;
    
    opens com.fluffyluffs.slayplayer.controller to javafx.fxml;
    
    exports com.fluffyluffs.slayplayer to javafx.graphics;
}
