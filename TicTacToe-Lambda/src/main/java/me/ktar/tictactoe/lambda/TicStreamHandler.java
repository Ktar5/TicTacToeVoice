package me.ktar.tictactoe.lambda;

import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler;

import java.util.HashSet;
import java.util.Set;

public class TicStreamHandler extends SpeechletRequestStreamHandler {

    private static final Set<String> supportedApplicationIds = new HashSet<>();

    static {
        /*
		 * This Id can be found on https://developer.amazon.com/edw/home.html#/
		 * "Edit" the relevant Alexa Skill and put the relevant Application Ids
		 * in this Set.
		 */
        supportedApplicationIds.add("amzn1.echo-sdk-ams.app.52d39e0e-451b-4bc3-92c3-d9656f107eef");
    }

    public TicStreamHandler() {
        super(new TicSpeechlet(), supportedApplicationIds);
    }

}