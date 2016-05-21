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
            return TicTacGameEndResponse.getResponse(json.getString("gameEnd"));
        }else{
            String place = json.getString("positionY") + " " + json.getString("positionX");
            //<break time="3s"/>
            return Response.newAskResponse("<speak>" +
                    "Nice move, now it is my turn <break time=\"2s\"/> " +
                    "There we go, your turn again"
                    + "</speak>",
                    true,
                    "Well? Your turn.",
                    false);
        }
    }

}
