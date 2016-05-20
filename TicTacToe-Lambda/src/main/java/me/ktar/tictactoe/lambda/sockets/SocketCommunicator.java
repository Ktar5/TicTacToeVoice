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
    private static final int PORT = 4048;

    public static String send(JSONObject json) {
        return send(json.toString());
    }

    public static String send(String data) {
        Socket socket = null;
        try {
            System.out.println("socket1");
            socket = new Socket(IP, PORT);
            System.out.println("socket12");
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            System.out.println("socket13");
            writer.write(data + "\n");
            System.out.println("socket14");
            writer.flush();
            System.out.println("socket15");

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("socket16");
            String input = in.readLine();
            System.out.println("socket17");
            if (input == null || input.equals(".")) {
                System.out.println("socket18");
                return "INVALID";
            }
            System.out.println("socket19");
            System.out.println("Response: " + input);
            System.out.println("socket110");
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
        send("{\"intent\":\"TicTacStart\"}");
    }

}
