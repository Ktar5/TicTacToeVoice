package me.ktar.tictactoe.lambda.responces;
/*
 * Copyright (C) 2013-Current Carter Gale (Ktar5) <buildfresh@gmail.com>
 * 
 * This file is part of me.ktar.alexa.me.ktar.audo.lambda.
 * 
 * me.ktar.alexa.me.ktar.audo.lambda can not be copied and/or distributed without the express
 * permission of the aforementioned owner.
 */

import com.amazon.speech.speechlet.SpeechletResponse;

@SuppressWarnings("Duplicates")
public class Generic {

    /**
     * Creates a {@code SpeechletResponse} for the hello intent.
     *
     * @return SpeechletResponse spoken and visual response for the given intent
     */
    public static SpeechletResponse getHelloResponse() {
        return Response.getResponse("Hello World", "Hello World");
    }

    /**
     * Creates a {@code SpeechletResponse} for the hello intent.
     *
     * @return SpeechletResponse spoken and visual response for the given intent
     */
    public static SpeechletResponse getErrorResponse(String speechText) {
        return Response.getResponse(speechText, "Error");
    }

}
