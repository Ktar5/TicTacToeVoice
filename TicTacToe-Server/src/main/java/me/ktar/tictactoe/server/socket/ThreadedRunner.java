package me.ktar.tictactoe.server.socket;
/*
 * Copyright (C) 2013-Current Carter Gale (Ktar5) <buildfresh@gmail.com>
 * 
 * This file is part of Auto-PiServer.
 * 
 * Auto-PiServer can not be copied and/or distributed without the express
 * permission of the aforementioned owner.
 */

import me.ktar.tictactoe.server.intents.Intents;
import org.json.JSONObject;

import java.io.*;
import java.net.Socket;


public class ThreadedRunner extends Thread {
    private Socket socket;

    public ThreadedRunner(Socket socket) {
        this.socket = socket;
        System.out.println("Thread started...");
    }

    public static String receive(JSONObject input) {
        String response = "invalid";
        if (input.has("intent")) {
            String jsonintent = input.getString("intent");
            response = Intents.valueOf(jsonintent.toUpperCase()).handle(input).toString();
            System.out.println("RESPONSE: " + response);
        }

        return response != null ? response : "invalid";
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            while (true) {
                String input = in.readLine();
                if (input == null || input.equals(".")) {
                    break;
                }
                //ystem.out.println("INPUT: " + input);

                JSONObject json = new JSONObject(input);
                String response = receive(json);
                //System.out.println("response end: " + response);

                writer.write(response + "\n");
                writer.flush();
            }
            in.close();
            writer.close();
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println("Couldn't close a socket, what's going on?");
            }
            System.out.println("Connection closed");
        }
    }

}