package com.github.karthyks.steamio.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by karthy07 on 12/13/2017.
 */

public class PlayerProfile {

    @SerializedName("steamid")
    private long steamId;
    @SerializedName("communityvisiblitystate")
    private int communityVisiblityState;
    @SerializedName("profilestate")
    private int profileState;
    @SerializedName("personaname")
    private String personaName;
    @SerializedName("lastlogoff")
    private long lastLogOff;
    @SerializedName("profileurl")
    private String profileUrl;
    @SerializedName("avatarfull")
    private String avatarUrl;
    @SerializedName("personastate")
    private String personaState;
    @SerializedName("realname")
    private String realName;

    public long getSteamId() {
        return steamId;
    }

    public int getCommunityVisiblityState() {
        return communityVisiblityState;
    }

    public int getProfileState() {
        return profileState;
    }

    public String getPersonaName() {
        return personaName;
    }

    public long getLastLogOff() {
        return lastLogOff;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getPersonaState() {
        return personaState;
    }

    public String getRealName() {
        return realName;
    }

    @Override
    public String toString() {
        return String.format("steamId : %s, realName : %s", steamId, realName);
    }
}
