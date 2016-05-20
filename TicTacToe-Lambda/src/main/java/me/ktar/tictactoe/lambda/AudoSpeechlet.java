package me.ktar.tictactoe.lambda;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.slu.Slot;
import com.amazon.speech.speechlet.*;
import com.amazonaws.util.json.JSONException;
import com.amazonaws.util.json.JSONObject;
import me.ktar.tictactoe.lambda.responces.Generic;
import me.ktar.tictactoe.lambda.sockets.DataInterpreter;
import me.ktar.tictactoe.lambda.sockets.SocketCommunicator;

import java.util.Map;

@SuppressWarnings("Duplicates")
public class AudoSpeechlet implements Speechlet {

    @Override
    public void onSessionStarted(final SessionStartedRequest request, final Session session) throws SpeechletException {
        //System.out.printf("onSessionStarted requestId={}, sessionId={}", request.getRequestId(), session.getSessionId());
        // any initialization logic goes here
    }

    @Override
    public SpeechletResponse onLaunch(final LaunchRequest request, final Session session) throws SpeechletException {
        //System.out.printf("onLaunch requestId={}, sessionId={}", request.getRequestId(), session.getSessionId());
        return Generic.getHelloResponse();
    }

    @Override
    public SpeechletResponse onIntent(final IntentRequest request, final Session session) throws SpeechletException {
        //System.out.printf("onIntent requestId={}, sessionId={}", request.getRequestId(), session.getSessionId());

        Intent intent = request.getIntent();
        if (intent == null) {
            throw new SpeechletException("Invalid Intent");
        }

        JSONObject json = null;
        try {
            json = new JSONObject();
            json.put("intent", intent.getName());
            if (!intent.getSlots().isEmpty()) {
                JSONObject slots = new JSONObject();
                for (Map.Entry<String, Slot> value : intent.getSlots().entrySet()) {
                    JSONObject slot = new JSONObject();
                    slot.put("name", value.getValue().getName());
                    if (value.getValue().getValue() == null) {
                        return Generic.getErrorResponse("value " + value.getValue().getName() + " was not set");
                    }
                    slot.put("value", value.getValue().getValue());
                    slots.put(value.getKey(), slot);
                }
                json.put("slots", slots);
            }
        } catch (JSONException e) {
            System.out.println("JSON Creation failed");
        }

        String response = SocketCommunicator.send(json);
        //System.out.println(response);
        return DataInterpreter.interpret(response);
    }

    @Override
    public void onSessionEnded(final SessionEndedRequest request, final Session session) throws SpeechletException {
        //System.out.printf("onSessionEnded requestId={}, sessionId={}", request.getRequestId(),session.getSessionId());
        // any cleanup logic goes here
    }

}