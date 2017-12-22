package com.github.karthyks.steamio.transactions;

import com.github.karthyks.steamio.SteamAPI;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URL;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by karthy07 on 12/14/2017.
 */

public class ResolveProfileTransaction extends Transaction {
    //http://api.steampowered.com/ISteamUser/ResolveVanityURL/v0001/?key=<key>&vanityurl=<vanity_name>

    private static final String URL = "http://api.steampowered.com/ISteamUser/ResolveVanityURL/v0001/?key=" + SteamAPI.API_KEY;
    @Override
    public Object execute(Object param) throws IOException {
        String path = new URL(param.toString()).getPath().replaceFirst("/", "")
                .split("/")[1];
        String url = URL + "&vanityurl=" + path;
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        JsonElement jsonElement = new JsonParser().parse(response.body().string());
        System.out.println(" Resolve : " + jsonElement.toString());
        return null;
    }
}
