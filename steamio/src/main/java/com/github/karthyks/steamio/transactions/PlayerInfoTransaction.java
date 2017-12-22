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

public class PlayerInfoTransaction extends Transaction {

    // https://api.steampowered.com/IDOTA2Fantasy_205790/GetPlayerOfficialInfo/v1 accountid

    private static final String URL = "http://api.steampowered.com/IDOTA2Fantasy_205790/" +
            "GetPlayerOfficialInfo/v1/?key=" + SteamAPI.API_KEY;
    @Override
    public Object execute(Object param) throws IOException {
        String url = URL + "&accountid=" + "83587088";
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        JsonElement jsonElement = new JsonParser().parse(response.body().string());
        System.out.println(jsonElement.toString());
        return null;
    }
}
