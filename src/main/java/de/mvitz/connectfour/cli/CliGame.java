package de.mvitz.connectfour.cli;

import de.mvitz.connectfour.Game;

public final class CliGame {

    private CliGame() {
    }

    public static void main(String[] args) {
        new Game(7, 6).start(new CliHumanPlayer("First"), new CliHumanPlayer("Second"));
    }

}
