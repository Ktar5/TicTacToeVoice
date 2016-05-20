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

public class TicTacFirstResponse {

    public static SpeechletResponse getResponse(boolean humanReady){
        if(humanReady){
            return Response.newAskResponse("Ok then, where would you like to go?", false,
                    "Well? Tell me.", false);
        }else{
            return Response.newAskResponse("Ok then, I guess I'll go... here... Where do you want to go?", false,
                    "Well? Tell me.", false);
        }
    }

}
