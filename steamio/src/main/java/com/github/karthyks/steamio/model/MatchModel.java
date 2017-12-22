package com.github.karthyks.steamio.model;

import com.google.gson.annotations.SerializedName;

import java.util.Locale;

/**
 * Created by karthy07 on 12/16/2017.
 */

public class MatchModel {
    @SerializedName("match_id")
    private long matchId;
    @SerializedName("match_seq_num")
    private long match_seq_num;
    @SerializedName("start_time")
    private long startTime;
    @SerializedName("lobby_type")
    private int lobbyType;
    @SerializedName("radiant_team_id")
    private int radiant_team_id;
    @SerializedName("dire_team_id")
    private int dire_team_id;
    @SerializedName("players")
    private Player[] players;

    public static class Player {
        @SerializedName("account_id")
        private long accountId;
        @SerializedName("player_slot")
        private int playerSlot;
        @SerializedName("hero_id")
        private int heroId;

        public long getAccountId() {
            return accountId;
        }

        public void setAccountId(long accountId) {
            this.accountId = accountId;
        }

        public int getPlayerSlot() {
            return playerSlot;
        }

        public void setPlayerSlot(int playerSlot) {
            this.playerSlot = playerSlot;
        }

        public int getHeroId() {
            return heroId;
        }

        public void setHeroId(int heroId) {
            this.heroId = heroId;
        }
    }

    @Override
    public String toString() {
        return String.format(Locale.getDefault(), "match_id : %d, start_time: %d, firstPlayerId: %d",
                matchId, startTime, players[0].accountId);
    }

    public long getMatchId() {
        return matchId;
    }

    public void setMatchId(long matchId) {
        this.matchId = matchId;
    }

    public long getMatch_seq_num() {
        return match_seq_num;
    }

    public void setMatch_seq_num(long match_seq_num) {
        this.match_seq_num = match_seq_num;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public int getLobbyType() {
        return lobbyType;
    }

    public void setLobbyType(int lobbyType) {
        this.lobbyType = lobbyType;
    }

    public int getRadiant_team_id() {
        return radiant_team_id;
    }

    public void setRadiant_team_id(int radiant_team_id) {
        this.radiant_team_id = radiant_team_id;
    }

    public int getDire_team_id() {
        return dire_team_id;
    }

    public void setDire_team_id(int dire_team_id) {
        this.dire_team_id = dire_team_id;
    }

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }
}