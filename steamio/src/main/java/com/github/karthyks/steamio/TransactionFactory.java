package com.github.karthyks.steamio;

import com.github.karthyks.steamio.transactions.GameInfoTransaction;
import com.github.karthyks.steamio.transactions.GetLibraryTransaction;
import com.github.karthyks.steamio.transactions.MatchHistoryTransaction;
import com.github.karthyks.steamio.transactions.MatchInfoTransaction;
import com.github.karthyks.steamio.transactions.PlayerInfoTransaction;
import com.github.karthyks.steamio.transactions.ResolveProfileTransaction;
import com.github.karthyks.steamio.transactions.Transaction;
import com.github.karthyks.steamio.transactions.UserInfoTransaction;

/**
 * Created by karthy07 on 11/26/2016.
 */

public class TransactionFactory {

    public static Transaction getUserInfoTransaction() {
        return new UserInfoTransaction();
    }

    public static Transaction getResolveProfileTransaction() {
        return new ResolveProfileTransaction();
    }

    public static Transaction getPlayerInfoTransaction() {
        return new PlayerInfoTransaction();
    }

    public static Transaction getMatchHistoryTransaction() {
        return new MatchHistoryTransaction();
    }

    public static Transaction getGameInfoTransaction() {
        return new GameInfoTransaction();
    }

    public static Transaction getMatchInfoTransaction() {
        return new MatchInfoTransaction();
    }

    public static Transaction getLibraryTransaction() {
        return new GetLibraryTransaction();
    }
}
