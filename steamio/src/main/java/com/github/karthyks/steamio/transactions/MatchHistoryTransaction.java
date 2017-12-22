package com.github.karthyks.steamio.transactions;

import com.github.karthyks.steamio.SteamAPI;
import com.github.karthyks.steamio.deserializers.MatchModelDeserializer;
import com.github.karthyks.steamio.model.MatchModel;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by karthy07 on 12/14/2017.
 */

public class MatchHistoryTransaction extends Transaction {

    //83587088
//    private static final String URL = "https://api.steampowered.com/IDOTA2Match_570/GetMatchHistory/v1";
//    private static final String URL = "https://community-dota-2.p.mashape.com/IDOTA2Match_570/GetMatchHistory/V001/?key=834C5F73DC17C42C41E2BADEFBBBCB99&format=JSON";
    private static final String URL = "https://api.steampowered.com/IDOTA2Match_570/GetMatchHistoryBySequenceNum/v1";
    @Override
    public Object execute(Object param) throws Exception {
        String url = URL + "?key=" + SteamAPI.API_KEY +"&account_id=" + String.valueOf(param) + "&start_at_match_seq_num=3150400000";
//        String url = URL + "&account_id=" + String.valueOf(param);
        Request request = new Request.Builder()
                .url(url)
                .build();
//        Request request1 = new Request.Builder()
//                .addHeader("X-Mashape-Key", "ZxHgzhbLbdmshoXCprfmaWWlTUiqp18u54UjsnfiXjW1Nbzg22")
//                .addHeader("Accept", "application/json")
//                .url(url)
//                .get().build();
        Response response = client.newCall(request).execute();
        JsonElement jsonElement = new JsonParser().parse(response.body().string());
        System.out.println(jsonElement.toString());
        JsonElement result = jsonElement.getAsJsonObject().get("result");
        JsonElement matches = result.getAsJsonObject().get("matches");
        System.out.println("Match History : " + jsonElement.toString());
        GsonBuilder gsonBuilder = new GsonBuilder();
        Type type = new TypeToken<List<MatchModel>>() { }.getType();
        gsonBuilder.registerTypeAdapter(type, new MatchModelDeserializer());
        return gsonBuilder.create().<List<MatchModel>>fromJson(matches, type);
    }
}
