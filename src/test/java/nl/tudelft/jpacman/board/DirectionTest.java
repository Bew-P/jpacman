package nl.tudelft.jpacman.board;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 * A very simple (and not particularly useful)
 * test class to have a starting point where to put tests.
 *
 * @author Arie van Deursen
 */
public class DirectionTest {
    /**
     * Do we get the correct delta when moving north?
     */
    @Test
    public void testNorth() { // เปลี่ยนเป็น public
        Direction north = Direction.valueOf("NORTH");
        assertThat(north.getDeltaY()).isEqualTo(-1);
    }

    /**
     * Do we get the correct delta when moving south?
     */
    @Test
    public void testSouth() { // เปลี่ยนเป็น public
        Direction south = Direction.valueOf("SOUTH");
        assertThat(south.getDeltaY()).isEqualTo(1);
    }

    /**
     * Do we get the correct delta when moving east?
     */
    @Test
    public void testEast() { // เปลี่ยนเป็น public
        Direction east = Direction.valueOf("EAST");
        assertThat(east.getDeltaX()).isEqualTo(1);
    }

    /**
     * Do we get the correct delta when moving west?
     */
    @Test
    public void testWest() { // เปลี่ยนเป็น public
        Direction west = Direction.valueOf("WEST");
        assertThat(west.getDeltaX()).isEqualTo(-1);
    }
}
