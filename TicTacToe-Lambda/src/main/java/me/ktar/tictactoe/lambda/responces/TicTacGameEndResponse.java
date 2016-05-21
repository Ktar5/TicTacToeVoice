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

public class TicTacGameEndResponse {

    public static SpeechletResponse getResponse(String json){
        String response = "<speak>" +
                "Nice move, now it is my turn <break time=\"2s\"/> " +
                "There we go. <break time=\"1s\"/> %WIN%"
                + "</speak>";

        switch (json.toLowerCase()){
            case "ai win":
                return Response.getSsmlResponse("TicTac AI Win",
                        response.replace("%WIN%", "Hah! I win again."));
            case "player win":
                return Response.getSsmlResponse("TicTac Human Win",
                        response.replace("%WIN%", "Wow! Someone actually beat me! Well done!"));
            case "tie":
                return Response.getSsmlResponse("TicTac Tie",
                        response.replace("%WIN%", "Looks like we have tied, good job."));
        }
        return null;
    }

}
