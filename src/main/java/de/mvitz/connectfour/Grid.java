package de.mvitz.connectfour;

import java.util.Optional;

public final class Grid {

    private final Column[] columns;

    public Grid(int columns, int rows) {
        this.columns = new Column[columns];
        for (int i = 0; i < this.columns.length; i++) {
            this.columns[i] = new Column(rows);
        }
    }

    public int getColumns() {
        return columns.length;
    }

    public int getRows() {
        return columns[0].getRows();
    }

    public Optional<Chip> getChip(int column, int row) {
        return columns[column].spaces[row].getChip();
    }

    public boolean drop(Chip chip, int column) {
        if (columns[column].isFull()) {
            return false;
        }
        columns[column].drop(chip);
        return true;
    }

    public enum Chip {
        RED, YELLOW;

        public Chip change() {
            switch (this) {
                case RED:
                    return YELLOW;
                case YELLOW:
                    return RED;
                default:
                    throw new IllegalStateException("Unknown chip: " + this);
            }
        }
    }

    public static final class Column {

        private final Space[] spaces;
        private int currentRow = 0;

        private Column(int rows) {
            spaces = new Space[rows];
            for (int i = 0; i < spaces.length; i++) {
                spaces[i] = new Space();
            }
        }

        public int getRows() {
            return spaces.length;
        }

        public boolean isFull() {
            return spaces.length <= currentRow;
        }

        public void drop(Chip chip) {
            spaces[currentRow].place(chip);
            currentRow++;
        }

        public static final class Space {

            private Chip chip;

            public void place(Chip chip) {
                this.chip = chip;
            }

            public Optional<Chip> getChip() {
                return Optional.ofNullable(chip);
            }

        }
    }

}
