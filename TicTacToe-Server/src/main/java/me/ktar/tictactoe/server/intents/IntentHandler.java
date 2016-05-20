package me.ktar.tictactoe.server.intents;
/*
 * Copyright (C) 2013-Current Carter Gale (Ktar5) <buildfresh@gmail.com>
 * 
 * This file is part of Auto-PiServer.
 * 
 * Auto-PiServer can not be copied and/or distributed without the express
 * permission of the aforementioned owner.
 */

import org.json.JSONObject;

interface IntentHandler {

    JSONObject handle(JSONObject json);

    default JSONObject error(String error){
        return new JSONObject().put("error", error);
    }

}
