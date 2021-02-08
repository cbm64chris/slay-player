package com.luff.slayplayer.controller.metadata;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.stream.Collectors;
import javafx.scene.image.Image;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;

public class AlbumArt {
    private static final String USER_AGENT = "Mozilla/5.0";
    private final static Logger LOG = LogManager.getLogger();
    
//    public static Image getAlbumArt(MetaData metaData) {
//        Image image = null;
//        try {
//            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL("http://itunes.apple.com/search?term=" + URLEncoder.encode(metaData.getMetaData(), "UTF-8"))
//                    .openConnection();
//            httpURLConnection.setRequestMethod("GET");
//            httpURLConnection.setRequestProperty("User-Agent", USER_AGENT);
//            httpURLConnection.setRequestProperty("Accept-Language", "UTF-8");
//            
//            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
//                JSONObject json = (JSONObject) new JSONParser().parse(new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream())).lines()
//                        .parallel().collect(Collectors.joining("\n")));
//                
//                JSONArray results = (JSONArray) json.get("results");
//                if (!results.isEmpty()) {
//                    String artwork100 = ((JSONObject) results.get(0)).get("artworkUrl100").toString();
//                    image = new Image(artwork100);
//                }
//            } else {
//                LOG.error(httpURLConnection.getResponseCode());
//            }
//        } catch (IOException | ParseException ex) {
//            
//        }
//        
//        return image;
//    }
    
}
