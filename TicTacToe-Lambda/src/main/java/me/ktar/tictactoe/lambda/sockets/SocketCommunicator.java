package me.ktar.tictactoe.lambda.sockets;

/*
 * Copyright (C) 2011-Current Carter Gale (Ktar5) <buildfresh@gmail.com>
 * 
 * This file is part of LutronTest.
 * 
 * LutronTest can not be copied and/or distributed without the express
 * permission of the aforementioned owner.
 */

import com.amazonaws.util.json.JSONObject;

import java.io.*;
import java.net.Socket;

public class SocketCommunicator {

    private static final String IP = "100.9.187.12";
    private static final int PORT = 25565;

    public static String send(JSONObject json) {
        return send(json.toString());
    }

    public static String send(String data) {
        Socket socket = null;
        try {
            socket = new Socket(IP, PORT);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            writer.write(data + "\n");
            writer.flush();

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String input = in.readLine();
            if (input == null || input.equals(".")) {
                return "INVALID";
            }
            //System.out.println("Response: " + input);
            writer.close();
            in.close();
            return input;
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
        System.out.println("DONE");
        return "INVALID";
    }


    public static void main(String[] args) {
        send("{\"slots\":{\"AREA\":{\"name\":\"AREA\",\"value\":\"bedroom\"},\"ONOFF\":{\"name\":\"ONOFF\",\"value\":\"on\"}},\"intent\":\"LUTRON\"}");
    }

}
