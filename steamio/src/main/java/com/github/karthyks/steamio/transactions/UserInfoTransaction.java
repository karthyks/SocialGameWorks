package com.github.karthyks.steamio.transactions;

import com.github.karthyks.steamio.SteamAPI;
import com.github.karthyks.steamio.model.PlayerProfile;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;

import okhttp3.Request;
import okhttp3.Response;


public class UserInfoTransaction extends Transaction {

    private static final String URL = "http://api.steampowered.com/ISteamUser/" +
            "GetPlayerSummaries/v2/?key=" + SteamAPI.API_KEY;

    @Override
    public Object execute(Object param) throws IOException {
        String url = URL + "&steamids=" + param.toString();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        JsonElement jsonElement = new JsonParser().parse(response.body().string());
        JsonElement playersElement = jsonElement.getAsJsonObject().get("response").getAsJsonObject()
                .get("players");
        System.out.println(playersElement.toString());
        if(playersElement.isJsonArray()) {
            playersElement = playersElement.getAsJsonArray().get(0);
        }
        JsonObject playerObject =  playersElement.getAsJsonObject();
        Gson gson = new Gson();
        System.out.println(playerObject.toString());
        return gson.fromJson(playerObject, PlayerProfile.class);
    }
}
