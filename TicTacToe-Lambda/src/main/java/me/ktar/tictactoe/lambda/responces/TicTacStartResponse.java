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

public class TicTacStartResponse {

    public static SpeechletResponse getResponse(){
        return Response.newAskResponse("Ok, lets play! ... Who will go first? You, or I?",
                false, "Well? You, or I?", false);
    }

}
