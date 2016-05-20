package me.ktar.tictactoe.server.intents;
/*
 * Copyright (C) 2013-Current Carter Gale (Ktar5) <buildfresh@gmail.com>
 * 
 * This file is part of TicTacToeVoice.
 * 
 * TicTacToeVoice can not be copied and/or distributed without the express
 * permission of the aforementioned owner.
 */

import me.ktar.tictactoe.server.tictac.TicTacGame;
import org.json.JSONObject;

public class TicTacStart implements IntentHandler {
    @Override
    public JSONObject handle(JSONObject json) {
        TicTacGame.startNewGame();

        JSONObject response = new JSONObject();
        response.put("intent", Intents.TICTACSTART.name());

        return response;
    }
}
