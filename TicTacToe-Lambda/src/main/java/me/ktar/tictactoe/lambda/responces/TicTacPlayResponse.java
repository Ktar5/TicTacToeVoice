package me.ktar.tictactoe.lambda.responces;
/*
 * Copyright (C) 2013-Current Carter Gale (Ktar5) <buildfresh@gmail.com>
 * 
 * This file is part of TicTacToeVoice.
 * 
 * TicTacToeVoice can not be copied and/or distributed without the express
 * permission of the aforementioned owner.
 */

import com.amazon.speech.speechlet.SpeechletResponse;
import org.json.JSONObject;

public class TicTacPlayResponse {

    public static SpeechletResponse getResponse(JSONObject json){
        if(json.has("moveNotAvailable")){
            return Response.newAskResponse("Sorry, but that spot is already taken, try again?", false,
                    "Well? Where do you want to move?", false);
        }else if(json.has("gameEnd")){
            return Response.getResponse("End Game", "Wow! This game has ended in a " + json.getString("gameEnd"));
        }else{
            String place = json.getString("positionY") + " " + json.getString("positionX");
            return Response.newAskResponse("Nice move... Now it is my turn... There we go... Your turn again", false,
                    "Well? Your turn.", false);
        }
    }

}
