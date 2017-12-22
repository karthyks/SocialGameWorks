package com.github.karthyks.steamio.transactions;

import com.github.karthyks.steamio.SteamAPI;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.IOException;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by karthy07 on 12/14/2017.
 */

public class MatchInfoTransaction extends Transaction {
    //3629615588
    private static final String URL = "https://api.steampowered.com/IDOTA2Match_570/GetMatchDetails/v1";

    @Override
    public Object execute(Object param) throws IOException {
        long match_id = Long.parseLong(param.toString());
        String matchId = String.valueOf(match_id);
        String url = URL + "?key=" + SteamAPI.API_KEY + "&match_id=" + matchId;
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        JsonElement jsonElement;
        try {
            jsonElement = new JsonParser().parse(response.body().string());
            System.out.println("Match Info :" + jsonElement.toString());
            System.out.println("Match Info : " + matchId + " : lobby :" + jsonElement.getAsJsonObject().get("result").getAsJsonObject().get("lobby_type"));
            try {
                Thread.sleep(5000);
                execute(String.valueOf(match_id -1));
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        } catch (Exception e) {
            try {
                Thread.sleep(5000);
                execute(String.valueOf(match_id - 1));
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
        return null;
    }
}
