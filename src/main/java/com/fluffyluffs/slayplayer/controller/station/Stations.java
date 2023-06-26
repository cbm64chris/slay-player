/*
 * Copyright Oracle America, Inc. 2023 All rights reserved.
 */

package com.fluffyluffs.slayplayer.controller.station;

/** Stations */
public enum Stations {
  ABSOLUTE_EIGHTIES(
      "Absolute 80s",
      "http://edge-bauerall-01-gos2.sharp-stream.com/absolute80s.mp3?aw_0_1st.skey=1687423077&aw_0_1st.playerid=BMUK_RPi",
      "absolute80s"),
  SLAY_RADIO(
      "Slay Radio",
      "https://www.slayradio.org/tune_in.php/128kbps/slayradio.128.m3u",
      "slay_radio"),
  SMOOTH_CHILL("Smooth Chill", "http://icecast.thisisdax.com/SmoothChillMP3", "smooth_chill"),
  CAPITAL_CHILL("Capital Chill", "http://media-ice.musicradio.com/CapitalChillMP3", "capital_chill");

  private final String name;
  private final String url;
  private final String img;

  private Stations(String name, String url, String img) {
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

  public String getImg() {
    return img;
  }
}
