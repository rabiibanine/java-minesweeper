package com.rabiiyouness.minesweeper.model.enums;

public enum Difficulty {
    BEGINNER    (9 , 9 , 10, "Beginner"    ),
    INTERMEDIATE(12, 12, 20, "Intermediate"),
    EXPERT      (16, 16, 30, "Expert"      );

    private final int rows;
    private final int columns;
    private final int mines;
    private final String displayName;

    Difficulty(int rows, int columns, int mines, String displayName) {
        this.rows = rows;
        this.columns = columns;
        this.mines = mines;
        this.displayName = displayName;
    }

    public int getRows()    { return rows;    }
    public int getColumns() { return columns; }
    public int getMines()   { return mines;   }
    public String getDisplayName() { return displayName; }

    @Override
    public String toString() {
        return displayName;
    }
}
