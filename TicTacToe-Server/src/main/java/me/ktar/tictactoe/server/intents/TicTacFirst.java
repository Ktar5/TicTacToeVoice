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

public class TicTacFirst implements IntentHandler {
    @Override
    public JSONObject handle(JSONObject json) {
        if(json.has("slots")){
            JSONObject slots = json.getJSONObject("slots");
            if(slots.has("firstToGo")){
                String first = slots.getJSONObject("firstToGo").getString("value");
                if(first == null){
                    return null;
                }
                if(TicTacGame.ended){
                    TicTacGame.startNewGame();
                }
                TicTacGame.humanReady = first.equalsIgnoreCase("I") || first.equalsIgnoreCase("You");
            }
        }
        new Thread(TicTacGame::startNewGame);

        JSONObject response = new JSONObject();
        response.put("intent", Intents.TICTACSTART.name());

        return response;
    }
}
