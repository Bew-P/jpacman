package nl.tudelft.jpacman.board;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test suite to confirm that {@link Unit}s correctly (de)occupy squares.
 *
 * @author Jeroen Roosen 
 *
 */
class OccupantTest {

    /**
     * The unit under test.
     */
    private Unit unit;

    /**
     * Resets the unit under test.
     */
    @BeforeEach
    void setUp() {
        unit = new BasicUnit();
    }

    /**
     * Asserts that a unit has no square to start with.
     */
    @Test
    void noStartSquare() {
        // Remove the following placeholder:
        assertThat(unit.hasSquare()).isFalse();
    }

    /**
     * Tests that the unit indeed has the target square as its base after
     * occupation.
     */
    @Test
    void testOccupy() {
        // Remove the following placeholder:
        BasicSquare square = new BasicSquare();
        unit.occupy(square);
        assertThat(unit.hasSquare()).isTrue();
        assertThat(unit.getSquare()).isEqualTo(square);
        assertThat(square.getOccupants()).contains(unit);
        assertThat(unit.invariant()).isTrue();
    }

    /**
     * Test that the unit indeed has the target square as its base after
     * double occupation.
     */
    @Test
    void testReoccupy() {
        // Remove the following placeholder:
        BasicSquare square1 = new BasicSquare();
        BasicSquare square2 = new BasicSquare();
        unit.occupy(square1);
        unit.occupy(square2);
        assertThat(unit.hasSquare()).isTrue();
        assertThat(unit.getSquare()).isEqualTo(square2);
        assertThat(square1.getOccupants()).doesNotContain(unit);
        assertThat(square2.getOccupants()).containsExactly(unit);
        assertThat(unit.invariant()).isTrue();
    }
}
