package com.luff.slayplayer.controller.metadata;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ReadMetaData {
    
    private String url;
    private static final String USER_AGENT = "Mozilla/5.0";
    private final static Logger LOG = LogManager.getLogger();
    
    public ReadMetaData setUrl(String url) {
        this.url = url;
        return this;
    }
    
    /**
     * Parses Meta Data when contained in the stream and header includes icy-metaint
     * @return String 
     */
    public MetaData parseMetaData() {
        StringBuilder metaData = new StringBuilder();
        int metaDataOffset = 0;
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(url).openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty("User-Agent", USER_AGENT);
            httpURLConnection.setRequestProperty("Icy-MetaData", "1");
            
            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream stream = httpURLConnection.getInputStream();
                Map<String, List<String>> headers = httpURLConnection.getHeaderFields();
                if (headers.containsKey("icy-metaint")) {
                    metaDataOffset = Integer.parseInt(headers.get("icy-metaint").get(0));
                
                    if (metaDataOffset != 0) {
                        int b;
                        int count = 0;
                        int metaDataLength = 4080;
                        boolean inData = false;

                        while ((b = stream.read()) != -1) {
                            count++;

                            // Length of the metadata
                            if (count == metaDataOffset + 1) {
                                metaDataLength = b * 16;
                            }

                            if (count > metaDataOffset + 1 && count < (metaDataOffset + metaDataLength)) {
                                inData = true;
                            } else {
                                inData = false;
                            }
                            if (inData) {
                                if (b != 0) {
                                    metaData.append((char)b);
                                }
                            }
                            if (count > (metaDataOffset + metaDataLength)) {
                                break;
                            }

                        }
                    }
                }
            }
            httpURLConnection.disconnect();
        } catch (IOException ioe) {
            LOG.error(USER_AGENT);
        }
        
        return processMetaData(metaData.toString());
    }
    
    private MetaData processMetaData(String metaString) {
        if (metaString.length() != 0) {
            return new MetaData(metaString.split("\'")[1], "");
        } else {
            return new MetaData("", "Can't display now playing.");
        }
    }
}
