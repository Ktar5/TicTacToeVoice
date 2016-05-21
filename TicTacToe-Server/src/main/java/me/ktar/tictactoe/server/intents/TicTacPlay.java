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
import me.ktar.tictactoe.server.tictac.board.Board;
import me.ktar.tictactoe.server.tictac.board.GameStatus;
import org.json.JSONObject;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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
                int col = 0;
                switch (positionX.toLowerCase()){
                    case "left":
                        col = 0;
                        break;
                    case "mid":
                    case "middle":
                    case "center":
                        col = 1;
                        break;
                    case "right":
                        col = 2;
                        break;
                }
                JSONObject response = new JSONObject();
                response.put("intent", Intents.TICTACPLAY.name());

                GameStatus status = TicTacGame.getBoard().play(Board.def[row][col], TicTacGame.HUMAN);
                TicTacGame.getBoard().printBoard();
                if(status == null){
                    return response.put("moveNotAvailable", true);
                }else if(status != GameStatus.NOTHING){
                    haltBoardView();
                    return response.put("gameEnd", status.name().replace("_", " "));
                }else{ //it does == nothing
                    status = TicTacGame.getBoard().play(TicTacGame.AI);
                    haltBoardView();
                    if(status != GameStatus.NOTHING){
                        return response.put("gameEnd", status.name().replace("_", " "));
                    }
                }

                response.put("positionY", positionY.toLowerCase());
                response.put("positionX", positionX.toLowerCase());
                return response;
            }
        }
        new Thread(TicTacGame::startNewGame);

        return error("I couldn't understand what you just said, try again?");
    }

    public static void haltBoardView(){
        final ScheduledExecutorService exec = Executors.newScheduledThreadPool(1);
        exec.schedule(() -> TicTacGame.getBoard().printBoard(), 4, TimeUnit.SECONDS);
    }
}
