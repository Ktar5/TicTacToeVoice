package me.ktar.tictactoe.server.tictac.ai.players;

import me.ktar.tictactoe.server.tictac.ai.Seed;
import me.ktar.tictactoe.server.tictac.board.Board;

/**
 * Copyright (C) 2013-Current Carter Gale (Ktar5) <buildfresh@gmail.com>
 * <p>
 * This file is part of TicTacToeVoice.
 * <p>
 * TicTacToeVoice can not be copied and/or distributed without the express
 * permission of the aforementioned owner.
 * <p>
 * Computer move based on simple table lookup of preferences
 */
public class AIPlayerTableLookup extends AIPlayer {

    // Moves {row, col} in order of preferences. {0, 0} at top-left corner
    private int[][] preferredMoves = {
            {1, 1}, {0, 0}, {0, 2}, {2, 0}, {2, 2},
            {0, 1}, {1, 0}, {1, 2}, {2, 1}};

    /**
     * constructor
     */
    public AIPlayerTableLookup(Board board, Seed seed) {
        super(board, seed);
    }

    /**
     * Search for the first empty cell, according to the preferences
     * Assume that next move is available, i.e., not gameover
     *
     * @return int[2] of {row, col}
     */
    @Override
    public int[] move() {
        for (int[] move : preferredMoves) {
            if (cells[move[0]][move[1]] == Seed.EMPTY) {
                return move;
            }
        }
        return null;
    }
}
