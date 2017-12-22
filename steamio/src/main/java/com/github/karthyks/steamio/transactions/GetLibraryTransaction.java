package com.github.karthyks.steamio.transactions;

import com.github.karthyks.steamio.SteamAPI;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.IOException;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by karthy07 on 12/20/2017.
 */

public class GetLibraryTransaction extends Transaction {
//    / http://api.steampowered.com/IPlayerService/GetOwnedGames/v0001/?key=XXXXXXXXXXXXXXXXX&steamid=76561197960434622&format=json
    public static final String URL = "http://api.steampowered.com/IPlayerService/GetOwnedGames/v0001/";
    @Override
    public Object execute(Object param) throws IOException {
        String url = URL + "?key=" + SteamAPI.API_KEY + "&steamid=" + param.toString();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        JsonElement jsonElement = new JsonParser().parse(response.body().string());
        System.out.println("GamesList : " + jsonElement.toString());
        return null;
    }
}
