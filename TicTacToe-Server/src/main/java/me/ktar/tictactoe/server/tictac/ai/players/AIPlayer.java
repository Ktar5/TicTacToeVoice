package me.ktar.tictactoe.server.tictac.ai.players;

import me.ktar.tictactoe.server.tictac.ai.Seed;
import me.ktar.tictactoe.server.tictac.board.Board;

/**
 * * Copyright (C) 2013-Current Carter Gale (Ktar5) <buildfresh@gmail.com>
 * <p>
 * This file is part of TicTacToeVoice.
 * <p>
 * TicTacToeVoice can not be copied and/or distributed without the express
 * permission of the aforementioned owner.
 * <p>
 * Abstract superclass for all AI players with different strategies.
 * To construct an AI player:
 * 1. Construct an instance (of its subclass) with the game Board
 * 2. Call setSeed() to set the computer's seed
 * 3. Call move() which returns the next move in an int[2] array of {row, col}.
 * <p>
 * The implementation subclasses need to override abstract method move().
 * They shall not modify Cell[][], i.e., no side effect expected.
 * Assume that next move is available, i.e., not game-over yet.
 */
abstract class AIPlayer {
    final Seed mySeed;    // computer's seed
    final Seed oppSeed;   // opponent's seed
    int ROWS = 3;  // number of rows
    int COLS = 3;  // number of columns
    Seed[][] cells; // the board's ROWS-by-COLS array of Cells

    /**
     * Constructor with reference to game board
     */
    AIPlayer(Board board, Seed seed) {
        cells = board.board;
        this.mySeed = seed;
        oppSeed = (mySeed == Seed.CROSS) ? Seed.NOUGHT : Seed.CROSS;
    }

    /**
     * Abstract method to get next move. Return int[2] of {row, col}
     */
    abstract int[] move();  // to be implemented by subclasses
}