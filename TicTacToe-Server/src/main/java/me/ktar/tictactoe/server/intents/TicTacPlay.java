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

public class TicTacPlay implements IntentHandler {
    @Override
    public JSONObject handle(JSONObject json) {
        if (json.has("slots")) {
            JSONObject slots = json.getJSONObject("slots");
            if (slots.has("positionY") && slots.has("positionX")) {
                String positionY = slots.getJSONObject("positionY").getString("value");
                String positionX = slots.getJSONObject("positionX").getString("value");
                if (positionY == null || positionX == null) {
                    return error("I couldn't understand what you just said, try again?");
                }
                if (TicTacGame.ended) {
                    TicTacGame.startNewGame();
                    TicTacGame.humanReady = true;
                }
                int row = 0;
                switch (positionY.toLowerCase()){
                    case "top":
                    case "upper":
                        row = 0;
                        break;
                    case "mid":
                    case "center":
                    case "middle":
                        row = 1;
                        break;
                    case "bottom":
                    case "lower":
                    case "low":
                        row = 2;
                        break;
                }
                switch (positionX.toLowerCase()){
                    case "left":
                        break;
                    case "mid":
                    case "middle":
                    case "center":
                        break;
                    case "right":
                        break;
                }

                JSONObject response = new JSONObject();
                response.put("intent", Intents.TICTACFIRST.name());
                response.put("positionY", positionY.toLowerCase());
                response.put("positionX", positionX.toLowerCase());
                return response;
            }
        }
        new Thread(TicTacGame::startNewGame);

        return error("I couldn't understand what you just said, try again?");
    }
}
