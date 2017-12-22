package com.github.karthyks.steamio.transactions;

import java.io.IOException;

import okhttp3.OkHttpClient;

/**
 * Created by karthy07 on 11/26/2016.
 */

public abstract class Transaction {

    public static final OkHttpClient client = new OkHttpClient();

    public abstract Object execute(Object param) throws IOException, Exception;
}
