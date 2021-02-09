
module SlayPlayer {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.base;
    requires javafx.media;
    requires javafx.graphicsEmpty;
    requires javafx.graphics;
    
    requires com.gluonhq.attach.storage;
    requires com.gluonhq.attach.util;
    
    requires org.apache.logging.log4j;
    requires org.apache.logging.log4j.core;
    requires java.base;
    
    opens com.fluffyluffs.slayplayer.controller to javafx.fxml;
    
    exports com.fluffyluffs.slayplayer to javafx.graphics;
}
