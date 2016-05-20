package me.ktar.tictactoe.server.tictac.ai;
/*
 * Copyright (C) 2013-Current Carter Gale (Ktar5) <buildfresh@gmail.com>
 * 
 * This file is part of TicTacToeVoice.
 * 
 * TicTacToeVoice can not be copied and/or distributed without the express
 * permission of the aforementioned owner.
 */

import me.ktar.tictactoe.server.tictac.board.BoardPart;

public enum Seed {
    EMPTY(BoardPart.NO), //nothing
    CROSS(BoardPart.X), //computer
    NOUGHT(BoardPart.O); //player

    public final BoardPart boardPart;

    Seed(BoardPart bp) {
        this.boardPart = bp;
    }
}
