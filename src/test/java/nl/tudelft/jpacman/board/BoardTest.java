package nl.tudelft.jpacman.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Test suite for the Board class.
 */
public class BoardTest {

    /** The board under test. */
    private Board board;

    /** The squares used for the board. */
    private Square[][] squares;

    /**
     * Test a board can be constructed from a valid 2D array of squares.
     */
    @Test
    public void constructBoardTest() {
        squares = new Square[1][1];
        squares[0][0] = new BasicSquare();
        board = new Board(squares);
        assertThat(board.invariant()).isTrue();
    }

    /**
     * Test that constructing a board with a null-containing square array throws an error.
     */
    @Test
    public void invalidBoardTest() { // เปลี่ยนชื่อเมธอดให้ตรงตาม Checkstyle
        squares = new Square[1][1];
        assertThrows(AssertionError.class, () -> new Board(squares));
    }

    /**
     * Test that the squareAt method returns the correct square for valid coordinates.
     */
    @Test
    public void squareAtValidTest() {
        squares = new Square[1][1];
        squares[0][0] = new BasicSquare();
        board = new Board(squares);
        assertThat(board.invariant()).isTrue();
        assertThat(board.squareAt(0, 0)).isEqualTo(squares[0][0]); // แก้ไขการเว้นวรรค
    }

    /**
     * Test that the squareAt method throws an error for out-of-bounds coordinates.
     */
    @Test
    public void squareAtOutOfBoundTest() {
        squares = new Square[1][1];
        squares[0][0] = new BasicSquare();
        board = new Board(squares);
        assertThrows(AssertionError.class, () -> board.squareAt(1, 0));
    }
}
