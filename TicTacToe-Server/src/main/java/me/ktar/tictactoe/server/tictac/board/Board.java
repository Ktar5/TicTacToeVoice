package me.ktar.tictactoe.server.tictac.board;
/*
 * Copyright (C) 2013-Current Carter Gale (Ktar5) <buildfresh@gmail.com>
 * 
 * This file is part of TicTacToeVoice.
 * 
 * TicTacToeVoice can not be copied and/or distributed without the express
 * permission of the aforementioned owner.
 */

import me.ktar.tictactoe.server.tictac.ai.Seed;
import me.ktar.tictactoe.server.tictac.ai.players.AIPlayerMinimax;
import org.fusesource.jansi.Ansi;

import java.io.IOException;

import static me.ktar.tictactoe.server.tictac.board.BoardPart.*;
import static org.fusesource.jansi.Ansi.ansi;

public class Board {
    //private int currentRow, currentCol;  // the current seed's row and column

    public static final BoardPart[][] def = {
            {TOP_LEFT, TOP_MID, TOP_RIGHT},
            {MID_LEFT, MID_MID, MID_RIGHT},
            {LOW_LEFT, LOW_MID, LOW_RIGHT}
    };

    public Seed[][] board = {
            {Seed.EMPTY, Seed.EMPTY, Seed.EMPTY},
            {Seed.EMPTY, Seed.EMPTY, Seed.EMPTY},
            {Seed.EMPTY, Seed.EMPTY, Seed.EMPTY}
    };

    public void reset(){

    }

    public void printBoard() {
        try {
            clear();
            BoardPart[][] current = generateBoard();
            printRow(current[0]);
            printMid();
            printRow(current[1]);
            printMid();
            printRow(current[2]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public GameStatus play(BoardPart part, Seed seed) {
        for (int r = 0; r < def.length; r++) {
            BoardPart[] col = def[r];
            for (int c = 0; c < col.length; c++) {
                if (col[c] == part) {
                    return play(r, c, false, seed);
                }
            }
        }
        return GameStatus.TIE;
    }

    public GameStatus play(Seed seed) {
        int[] move = new AIPlayerMinimax(this, seed).move();
        return play(move[0], move[1], true, seed);
    }

    private GameStatus play(int row, int col, boolean ai, Seed seed) {
        if(board[row][col] != Seed.EMPTY){
            return null;
        }
        board[row][col] = seed;
        printBoard();
        if (hasWon(seed, row, col)) {
            return ai ? GameStatus.AI_WIN : GameStatus.PLAYER_WIN;
        } else if (isDraw()) {
            return GameStatus.TIE;
        } else {
            return GameStatus.NOTHING;
        }
    }


    /**
     * Return true if it is a draw (i.e., no more EMPTY cell)
     */
    private boolean isDraw() {
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 3; ++col) {
                if (board[row][col] == Seed.EMPTY) {
                    return false; // an empty seed found, not a draw, exit
                }
            }
        }
        return true; // no empty cell, it's a draw
    }

    /**
     * Return true if the player with "theSeed" has won after placing at
     * (currentRow, currentCol)
     */
    private boolean hasWon(Seed theSeed, int currentRow, int currentCol) {
        return (board[currentRow][0] == theSeed         // 3-in-the-row
                && board[currentRow][1] == theSeed
                && board[currentRow][2] == theSeed
                || board[0][currentCol] == theSeed      // 3-in-the-column
                && board[1][currentCol] == theSeed
                && board[2][currentCol] == theSeed
                || currentRow == currentCol            // 3-in-the-diagonal
                && board[0][0] == theSeed
                && board[1][1] == theSeed
                && board[2][2] == theSeed
                || currentRow + currentCol == 2    // 3-in-the-opposite-diagonal
                && board[0][2] == theSeed
                && board[1][1] == theSeed
                && board[2][0] == theSeed);
    }

    private BoardPart[][] generateBoard() {
        BoardPart[][] current = new BoardPart[def.length][];
        for(int i = 0; i < def.length; i++)
            current[i] = def[i].clone();

        for (int r = 0; r < board.length; r++) {
            Seed[] col = board[r];
            for (int c = 0; c < col.length; c++) {
                if (col[c] != Seed.EMPTY) {
                    current[r][c] = board[r][c].boardPart;
                }
            }
        }
        return current;
    }

    private void clear() throws IOException, InterruptedException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }

    private void printRow(BoardPart... parts) {
        for (int i = 0; i < NO.value.length; i++) {
            StringBuilder builder = new StringBuilder("        ")
                    .append("                             " +
                            "                                   ");
            for (int f = 0; f < 3; f++) {
                builder.append(ansi()
                        .fgBright(getColor(parts[f]))
                        .a(parts[f].value[i])
                        .fg(Ansi.Color.DEFAULT))
                        .append(f == 2 ? "" : DOWN.value[i]);
            }
            System.out.println(builder);
        }
    }

    private void printMid() {
        for (int i = 0; i < ARM.value.length; i++) {
            System.out.print("                                       " +
                    "                         "
                    + ARM.value[i]);
            System.out.println();
        }
    }

    private Ansi.Color getColor(BoardPart part) {
        switch (part) {
            case X:
                return Ansi.Color.GREEN;
            case O:
                return Ansi.Color.CYAN;
            case NO:
            case DOWN:
            case ARM:
                return Ansi.Color.DEFAULT;
            default:
                return Ansi.Color.YELLOW;
        }
    }

}
