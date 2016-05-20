package me.ktar.tictactoe.lambda.sockets;

import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazonaws.util.json.JSONException;
import com.amazonaws.util.json.JSONObject;
import me.ktar.tictactoe.lambda.responces.Generic;

/*
 * Copyright (C) 2013-Current Carter Gale (Ktar5) <buildfresh@gmail.com>
 *
 * This file is part of me.ktar.audo.me.ktar.alexa.me.ktar.audo.lambda.
 *
 * me.ktar.audo.me.ktar.alexa.me.ktar.audo.lambda can not be copied and/or distributed without the express
 * permission of the aforementioned owner.
 */

public class DataInterpreter {

    public static SpeechletResponse interpret(String data) {
        if (data.equalsIgnoreCase("invalid")) {
            return Generic.getErrorResponse("There was an error, try again");
        }
        JSONObject json = null;
        try {
            json = new JSONObject(data);
            if (json.has("error")) {
                System.out.println(json.getString("error"));
                return Generic.getErrorResponse(json.getString("error"));
            }
            String intent = json.getString("intent");
            switch (intent.toLowerCase()) {
                case "tictacstart":
                case "tictacfirst":
                case "tictacplay":
                default:
                    Generic.getErrorResponse("There was an error, try again");

            }
        } catch (JSONException e) {
            System.out.println(e);
        }

        return Generic.getErrorResponse("To be honest, dont event know what happened");
    }

}
