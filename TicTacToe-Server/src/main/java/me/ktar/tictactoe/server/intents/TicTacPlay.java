package me.ktar.tictactoe.server.intents;
/*
 * Copyright (C) 2013-Current Carter Gale (Ktar5) <buildfresh@gmail.com>
 * 
 * This file is part of TicTacToeVoice.
 * 
 * TicTacToeVoice can not be copied and/or distributed without the express
 * permission of the aforementioned owner.
 */

import org.json.JSONObject;

public class TicTacPlay implements IntentHandler {
    @Override
    public JSONObject handle(JSONObject json) {
        if(json.has("slots") && json.getString("intent").equalsIgnoreCase("LUTRON")){
            JSONObject slots = json.getJSONObject("slots");
            if(slots.has("ONOFF") && slots.has("AREA")){
                String area = slots.getJSONObject("AREA").getString("value");
                Light light = Light.valueOf(area.replace(" ", "_").toUpperCase());
                if(light == null){
                    return Error.json("light is null in LutronHandler: " + area);
                }
                String onoffstring = slots.getJSONObject("ONOFF").getString("value").toUpperCase();
                if(!onoffstring.equals("ON") && !onoffstring.equals("OFF") && !onoffstring.equals("OUT")){
                    return Error.json("LutronHandler knows that " + onoffstring + " is not ON nor OFF");
                }
                boolean onoff = onoffstring.equals("ON");
                Thread a = new Thread(() -> {
                    AutomatedTelnetClient telnet = new AutomatedTelnetClient(
                            "192.168.1.201", "lutron", "integration");
                    telnet.sendCommand(light.getCommand(onoff));
                    telnet.disconnect();
                });

                JSONObject response = new JSONObject();
                response.put("intent", Intents.LUTRON.name());
                response.put("light", light.name().replace("_", " "));
                response.put("onoff", onoffstring);
                return response;
            }
        }else if(json.has("slots") && json.getString("intent").equalsIgnoreCase("LUTRONSET")) {
            JSONObject slots = json.getJSONObject("slots");
            if (slots.has("AMOUNT")) {
                String area = slots.getJSONObject("DIMMER").getString("value");
                Light light = Light.valueOf(area.replace(" ", "_").toUpperCase());
                if (light == null) {
                    return Error.json("light is null in LutronHandler: " + area);
                }
                int amount = slots.getJSONObject("AMOUNT").getInt("value");
                if (amount > 100 || amount < 0) {
                    amount = (amount > 100 ? 100 : 0);
                }
                int finalAmount = amount;
                Thread a = new Thread(() -> {
                    AutomatedTelnetClient telnet = new AutomatedTelnetClient(
                            "192.168.1.201", "lutron", "integration");
                    telnet.sendCommand(light.getCommand(finalAmount));
                    telnet.disconnect();
                });

                JSONObject response = new JSONObject();
                response.put("intent", Intents.LUTRONSET.name());
                response.put("dimmer", light.name().replace("_", " "));
                response.put("amount", amount);
                return response;
            }
        }
        return Error.json("LutronHandler can't find 'slots' in the json string: " + json.toString());
    }
}
