package de.mvitz.connectfour;

import de.mvitz.connectfour.Grid.Chip;

public final class Game {

    private final Grid grid;
    private Chip chip = Chip.YELLOW;

    public Game(int columns, int rows) {
        this.grid = new Grid(columns, rows);
    }

    public void start(Player first, Player second) {
        Player currentPlayer = first;
        while (!isFinished()) {
            currentPlayer.drawBoard(grid);
            makeTurn(currentPlayer);
            currentPlayer = currentPlayer == first ? second : first;
        }
    }

    private boolean isFinished() {
        return false;
    }

    private void makeTurn(Player player) {
        boolean turnMade = false;
        while (!turnMade) {
            final int column = player.getTurn();
            turnMade = drop(column);
            if (!turnMade) {
                player.illegalTurn(column);
            }
        }
    }

    private boolean drop(int column) {
        final boolean success = grid.drop(chip, column);
        if (success) {
            // TODO: check if we have a winner
            chip = chip.change();
        }
        return success;
    }

    public interface Player {
        int getTurn();

        void illegalTurn(int column);

        void drawBoard(Grid grid);
    }

}
