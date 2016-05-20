package me.ktar.tictactoe.server.tictac;
/*
 * Copyright (C) 2013-Current Carter Gale (Ktar5) <buildfresh@gmail.com>
 * 
 * This file is part of TicTacToeVoice.
 * 
 * TicTacToeVoice can not be copied and/or distributed without the express
 * permission of the aforementioned owner.
 */

import me.ktar.tictactoe.server.tictac.ai.Seed;
import me.ktar.tictactoe.server.tictac.board.Board;
import me.ktar.tictactoe.server.tictac.board.GameStatus;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public final class TicTacGame {

    public static final Seed AI = Seed.CROSS;
    public static final Seed HUMAN = Seed.NOUGHT;
    private static Board board;

    public static boolean
            ended = false,
            humanReady = false;

    public static void startNewGame() {
        board = new Board();
        ended = false;
        humanReady = false;
    }

    public static Board getBoard(){
        return board;
    }

    public static void startTest(){
        boolean ai = true;
        GameStatus status = GameStatus.NOTHING;
        board = new Board();
        while (status == GameStatus.NOTHING) {
            status = board.play(ai ? AI : HUMAN);
            ai = !ai;
            board.printBoard();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Game has: " + status.name());
        System.out.println("Game has: " + status.name());
        System.out.println("Game has: " + status.name());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void test() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(new Runnable() {
            int runs = 0;

            @Override
            public void run() {
                board.printBoard();
                if (++runs >= 100) System.exit(1);
            }
        }, 0, 3, TimeUnit.SECONDS);
    }

}
