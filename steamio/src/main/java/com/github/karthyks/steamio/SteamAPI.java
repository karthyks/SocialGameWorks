package com.github.karthyks.steamio;

public class SteamAPI {
    private static final SteamAPI instance = new SteamAPI();
    public static final String API_KEY = "834C5F73DC17C42C41E2BADEFBBBCB99";

    public static SteamAPI getInstance() {
        return instance;
    }
}
