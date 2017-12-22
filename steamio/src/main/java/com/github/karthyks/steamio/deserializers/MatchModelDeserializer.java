package com.github.karthyks.steamio.deserializers;

import com.github.karthyks.steamio.model.MatchModel;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by karthy07 on 12/16/2017.
 */

public class MatchModelDeserializer implements JsonDeserializer<List<MatchModel>> {
    @Override
    public List<MatchModel> deserialize(JsonElement json, Type typeOfT,
                                        JsonDeserializationContext context) throws JsonParseException {
        List<MatchModel> matchModelList = new LinkedList<>();
        System.out.println(
                json.toString()
        );
        if (!json.isJsonArray()) return matchModelList;
        JsonArray matches = json.getAsJsonArray();
        System.out.println("Match Count " + matches.size());
        for (JsonElement jsonElement : matches) {
            MatchModel matchModel = new Gson().fromJson(jsonElement, MatchModel.class);
            if (matchModel.getLobbyType() == 7) {
                System.out.println(matchModel.getMatch_seq_num());
            }
            for (MatchModel.Player player : matchModel.getPlayers()) {
                if (player.getAccountId() == (76561198043852816L - 76561197960265728L)) {
                    System.out.println(player.getAccountId());
                    System.out.println("Match Model : Lobby type " + matchModel.getLobbyType());
                    matchModelList.add(matchModel);
                }
            }
        }
        try {
            System.out.println("Last Match Model : " + matchModelList.get(matchModelList.size() - 1).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
