/*
 * Copyright Oracle America, Inc. 2023 All rights reserved.
 */

package com.fluffyluffs.slayplayer.controller.station;

import javafx.scene.image.Image;

/** Stations */
public enum Station {
  ABSOLUTE_EIGHTIES(
      "Absolute 80s",
      "http://edge-bauerall-01-gos2.sharp-stream.com/absolute80s.mp3?aw_0_1st.skey=1687423077&aw_0_1st.playerid=BMUK_RPi",
      "absolute80s") {
    @Override
    public void setStation() {
      throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public StationInstance getStationInstance() {
      throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
  },
  SLAY_RADIO(
      "Slay Radio",
      "https://www.slayradio.org/tune_in.php/128kbps/slayradio.128.m3u",
      "slay_radio") {
    @Override
    public void setStation() {
      throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public StationInstance getStationInstance() {
      throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
  },
  SMOOTH_CHILL("Smooth Chill", "http://icecast.thisisdax.com/SmoothChillMP3", "smooth_chill") {
    @Override
    public void setStation() {
      throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public StationInstance getStationInstance() {
      throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
  },
  CAPITAL_CHILL(
      "Capital Chill", "http://media-ice.musicradio.com/CapitalChillMP3", "capital_chill") {
    @Override
    public void setStation() {
      throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public StationInstance getStationInstance() {
      throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
  };

  private final String name;
  private final String url;
  private final String img;

  private Station(String name, String url, String img) {
    this.name = name;
    this.url = url;
    this.img = img;
  }

  public String getName() {
    return name;
  }

  public String getUrl() {
    return url;
  }

  public Image getImg() {
    return new Image(StationInstance.class.getResourceAsStream("/images/" + this.img + ".png"));
  }
  
  public abstract void setStation();
  
  public abstract StationInstance getStationInstance();
}
