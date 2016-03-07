package de.mvitz.connectfour.cli;

import de.mvitz.connectfour.Game.Player;
import de.mvitz.connectfour.Grid;

import java.util.Scanner;

public final class CliHumanPlayer implements Player {

    private final String name;

    public CliHumanPlayer(String name) {
        this.name = name;
    }

    public int getTurn() {
        System.out.print(name + "! Reihe zum Einwerfen wählen (Buchstaben): ");
        final Scanner in = new Scanner(System.in);
        return (int) (in.next().charAt(0)) - (int) 'A';
    }

    public void illegalTurn(int column) {
        System.out.println("Dies Saplte '" + ((char) ((int) 'A' + column)) + "' ist schon voll.");
    }

    public void drawBoard(Grid grid) {
        for (int row = grid.getRows() - 1; row > -1; row--) {
            for (int column = 0; column < grid.getColumns(); column++) {
                System.out.print(grid.getChip(column, row).map((chip) -> chip == Grid.Chip.YELLOW ? '■' : 'x').orElse('o') + " ");
            }
            System.out.println();
        }
        for (int column = 0; column < grid.getColumns(); column++) {
            System.out.print((char) ((int) 'A' + column) + " ");
        }
        System.out.println();
        System.out.println();
    }
}
