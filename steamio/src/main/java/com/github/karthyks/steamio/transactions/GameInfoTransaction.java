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

public class GameInfoTransaction extends Transaction {

    private static final String URL = "http://store.steampowered.com/api/appdetails?appids=";
    @Override
    public Object execute(Object param) throws IOException {
        String url = URL + "570";
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        JsonElement jsonElement = new JsonParser().parse(response.body().string());
        System.out.println(jsonElement.getAsJsonObject().get("570").getAsJsonObject().get("data"));
        return null;
    }
}
