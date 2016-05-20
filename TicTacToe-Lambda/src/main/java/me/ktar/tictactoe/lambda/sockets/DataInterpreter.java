package me.ktar.tictactoe.lambda.sockets;

import com.amazon.speech.speechlet.SpeechletResponse;
import me.ktar.tictactoe.lambda.responces.Generic;
import me.ktar.tictactoe.lambda.responces.TicTacFirstResponse;
import me.ktar.tictactoe.lambda.responces.TicTacPlayResponse;
import me.ktar.tictactoe.lambda.responces.TicTacStartResponse;
import org.json.JSONException;
import org.json.JSONObject;

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
            System.out.println("interpret1");
            json = new JSONObject(data);
            if (json.has("error")) {
                System.out.println("interpret12");
                System.out.println(json.getString("error"));
                return Generic.getErrorResponse(json.getString("error"));
            }
            String intent = json.getString("intent");
            System.out.println("interpret13");
            switch (intent.toLowerCase()) {
                case "tictacstart":
                    System.out.println("interpret14");
                    return TicTacStartResponse.getResponse();
                case "tictacfirst":
                    System.out.println("interpret15");
                    return TicTacFirstResponse.getResponse(json.getBoolean("humanFirst"));
                case "tictacplay":
                    System.out.println("interpret16");
                    return TicTacPlayResponse.getResponse(json);
                default:
                    System.out.println("interpret17");
                    return Generic.getErrorResponse("There was an error, try again");
            }
        } catch (JSONException e) {
            System.out.println(e);
        }

        return Generic.getErrorResponse("To be honest, dont event know what happened");
    }

}
