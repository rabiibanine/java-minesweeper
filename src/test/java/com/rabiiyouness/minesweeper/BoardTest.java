package com.rabiiyouness.minesweeper;

import com.rabiiyouness.minesweeper.model.Board;
import com.rabiiyouness.minesweeper.model.Position;
import com.rabiiyouness.minesweeper.model.Tile;
import com.rabiiyouness.minesweeper.model.enums.Difficulty;
import com.rabiiyouness.minesweeper.model.enums.GameState;
import com.rabiiyouness.minesweeper.model.enums.TileState;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    @Test
    void testInitializeGame() {

        Board board = new Board();

        board.initializeGame(Difficulty.BEGINNER);

        assertEquals(GameState.READY, board.getGameState());

        assertEquals(9, board.getRows());

        assertEquals(9, board.getCols());

        assertEquals(10, board.getRemainingMines());

        assertEquals(0, board.getElapsedSeconds());

        assertNull(board.getScore());
    }

    @Test
    void testFirstRevealStartsGame() {

        Board board = new Board();

        board.initializeGame(Difficulty.BEGINNER);

        board.revealTile(new Position(0, 0));

        assertEquals(
                GameState.RUNNING,
                board.getGameState()
        );
    }

    @Test
    void testFirstRevealSafe() {

        Board board = new Board();

        board.initializeGame(Difficulty.BEGINNER);

        Position pos = new Position(4, 4);

        board.revealTile(pos);

        Tile tile = board.getTileAt(pos);

        assertFalse(tile.isMine());

        assertTrue(tile.isRevealed());
    }

    @Test
    void testToggleFlag() {

        Board board = new Board();

        board.initializeGame(Difficulty.BEGINNER);

        Position pos = new Position(1, 1);

        board.toggleFlag(pos);

        Tile tile = board.getTileAt(pos);

        assertEquals(
                TileState.FLAGGED,
                tile.getState()
        );

        assertEquals(
                9,
                board.getRemainingMines()
        );
    }

    @Test
    void testToggleFlagTwice() {

        Board board = new Board();

        board.initializeGame(Difficulty.BEGINNER);

        Position pos = new Position(1, 1);

        board.toggleFlag(pos);

        board.toggleFlag(pos);

        Tile tile = board.getTileAt(pos);

        assertEquals(
                TileState.HIDDEN,
                tile.getState()
        );

        assertEquals(
                10,
                board.getRemainingMines()
        );
    }

    @Test
    void testRevealTile() {

        Board board = new Board();

        board.initializeGame(Difficulty.BEGINNER);

        Position pos = new Position(0, 0);

        board.revealTile(pos);

        Tile tile = board.getTileAt(pos);

        assertEquals(
                TileState.REVEALED,
                tile.getState()
        );
    }

    @Test
    void testTickSecond() {

        Board board = new Board();

        board.initializeGame(Difficulty.BEGINNER);

        board.revealTile(new Position(0, 0));

        board.tickSecond();

        board.tickSecond();

        assertEquals(
                2,
                board.getElapsedSeconds()
        );
    }

    @Test
    void testInvalidPosition() {

        Board board = new Board();

        board.initializeGame(Difficulty.BEGINNER);

        assertThrows(
                IllegalArgumentException.class,
                () -> board.getTileAt(
                        new Position(-1, 0)
                )
        );
    }

    @Test
    void testLoseGameRevealsAllMines() {

        Board board = new Board();

        board.initializeGame(Difficulty.BEGINNER);

        Position minePosition = null;

        // Premier clic pour générer les mines
        board.revealTile(new Position(0, 0));

        // Trouver une mine manuellement
        for (int r = 0; r < board.getRows(); r++) {
            for (int c = 0; c < board.getCols(); c++) {

                Position pos = new Position(r, c);

                Tile tile = board.getTileAt(pos);

                if (tile.isMine()) {
                    minePosition = pos;
                    break;
                }
            }

            if (minePosition != null) {
                break;
            }
        }

        // Cliquer sur une mine
        board.revealTile(minePosition);

        assertEquals(
                GameState.LOST,
                board.getGameState()
        );

        // Vérifier que toutes les mines sont révélées
        for (int r = 0; r < board.getRows(); r++) {
            for (int c = 0; c < board.getCols(); c++) {

                Tile tile = board.getTileAt(
                        new Position(r, c)
                );

                if (tile.isMine()) {

                    assertEquals(
                            TileState.REVEALED,
                            tile.getState()
                    );
                }
            }
        }
    }

    @Test
    void testWinningGame() {

        Board board = new Board();

        board.initializeGame(Difficulty.BEGINNER);

        // Premier clic pour générer les mines
        board.revealTile(new Position(0, 0));

        // Trouver une mine manuellement et les réveiller
        for (int r = 0; r < board.getRows(); r++) {
            for (int c = 0; c < board.getCols(); c++) {

                Position pos = new Position(r, c);

                Tile tile = board.getTileAt(pos);

                if (!tile.isMine()) {
                    board.revealTile(pos);
                }
            }
        }

        assertEquals(
                GameState.WON,
                board.getGameState()
        );

        // Vérifier que toutes les mines sont automatiquement marquées
        for (int r = 0; r < board.getRows(); r++) {
            for (int c = 0; c < board.getCols(); c++) {

                Tile tile = board.getTileAt(
                        new Position(r, c)
                );

                if (tile.isMine()) {

                    assertEquals(
                            TileState.FLAGGED,
                            tile.getState()
                    );
                }
            }
        }

        assertNotNull(board.getScore());
    }

    @Test
    void testFlaggedTileState() {
        Board board = new Board();
        board.initializeGame(Difficulty.BEGINNER);
        Position pos = new Position(2, 2);
        board.toggleFlag(pos);
        Tile tile = board.getTileAt(pos);
        assertSame(TileState.FLAGGED, tile.getState());
    }
}