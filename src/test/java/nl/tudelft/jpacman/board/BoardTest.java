package nl.tudelft.jpacman.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class BoardTest {
    private Board board;
    Square[][] squares;

    @Test
    public void constructBoardTest() {
        squares = new Square[1][1];
        squares[0][0] = new BasicSquare();
        board = new Board(squares);
        assertThat(board.invariant()).isTrue();
    }

    @Test
    public void InvalidBoardTest() {
        squares = new Square[1][1];
        assertThrows(AssertionError.class, () -> new Board(squares));
    }

    @Test
    public void squareAtValidTest() {
        squares = new Square[1][1];
        squares[0][0] = new BasicSquare();
        board = new Board(squares);
        assertThat(board.invariant()).isTrue();
        assertThat(board.squareAt(0,0)).isEqualTo(squares[0][0]);
    }

    @Test
    public void squareAtOutOfBoundTest() {
        squares = new Square[1][1];
        squares[0][0] = new BasicSquare();
        board = new Board(squares);
        assertThrows(AssertionError.class, () -> board.squareAt(1, 0));
    }

}
