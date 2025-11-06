package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.board.Board;
import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.board.Square;
import nl.tudelft.jpacman.npc.Ghost;
import nl.tudelft.jpacman.npc.ghost.Blinky;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This is a test class for MapParser.
 */
@ExtendWith(MockitoExtension.class)
public class MapParserTest {

    private static final int EXPECTED_WALLS = 26;
    private static final int EXPECTED_GROUND = 10;
    private static final int EXPECTED_GHOSTS = 1;

    @Mock private BoardFactory boardFactory;
    @Mock private LevelFactory levelFactory;
    @Mock private Blinky blinky;
    @Mock private Square groundSquare;
    @Mock private Square wallSquare;
    @Mock private Board board;
    @Mock private Level level;

    /**
     * Set up common mock behavior for the MapParser tests.
     */
    @BeforeEach
    public void setUp() {
        // Setup mocks for ALL expected creations:
        Mockito.when(boardFactory.createGround()).thenReturn(groundSquare);
        Mockito.when(boardFactory.createWall()).thenReturn(wallSquare);
        Mockito.when(levelFactory.createGhost()).thenReturn(blinky);
        Mockito.when(boardFactory.createBoard(Mockito.any())).thenReturn(board);
    }

    /**
     * Test for the parseMap method (good map).
     */
    @Test
    public void testParseMapGood() {
        // Capture arguments passed to the final createLevel call
        ArgumentCaptor<List> ghostsCaptor = ArgumentCaptor.forClass(List.class);
        ArgumentCaptor<List> startPositionsCaptor = ArgumentCaptor.forClass(List.class);

        // Stub createLevel() with the Captors
        Mockito.when(levelFactory.createLevel(
            Mockito.eq(board), ghostsCaptor.capture(),
            startPositionsCaptor.capture())).thenReturn(level);

        MapParser mapParser = new MapParser(levelFactory, boardFactory);
        ArrayList<String> map = new ArrayList<>();
        map.add("############");
        map.add("#P        G#");
        map.add("############");

        mapParser.parseMap(map);

        // Verify key factory interactions (Map structure)
        Mockito.verify(boardFactory, Mockito.times(EXPECTED_WALLS)).createWall();
        Mockito.verify(boardFactory, Mockito.times(EXPECTED_GROUND)).createGround();
        Mockito.verify(levelFactory, Mockito.times(EXPECTED_GHOSTS)).createGhost();

        // Verify Level Assembly
        Mockito.verify(levelFactory, Mockito.times(EXPECTED_GHOSTS))
            .createLevel(Mockito.eq(board), Mockito.anyList(), Mockito.anyList());

        // Verify Captured Ghost List
        List<Ghost> capturedGhosts = ghostsCaptor.getValue();
        assertEquals(EXPECTED_GHOSTS, capturedGhosts.size(),
            "The map should contain exactly one ghost.");
    }
}
