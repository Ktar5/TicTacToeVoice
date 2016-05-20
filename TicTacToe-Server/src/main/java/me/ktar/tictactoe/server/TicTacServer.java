package me.ktar.tictactoe.server;
/*
 * Copyright (C) 2013-Current Carter Gale (Ktar5) <buildfresh@gmail.com>
 * 
 * This file is part of TicTacToeVoice.
 * 
 * TicTacToeVoice can not be copied and/or distributed without the express
 * permission of the aforementioned owner.
 */

import me.ktar.tictactoe.server.tictac.TicTacGame;
import org.fusesource.jansi.AnsiConsole;

public class TicTacServer {

    public static void main(String[] args) throws Exception {
        System.out.println("The Audo socket listening server is up and running!");
        AnsiConsole.systemInstall();
        TicTacGame.startNewGame();
        /*
        try (ServerSocket listener = new ServerSocket(4047)) {
            while (true) {
                new ThreadedRunner(listener.accept()).start();
            }
        }
        */
    }

}
