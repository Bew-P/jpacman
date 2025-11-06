package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.board.Board;
import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.board.Square;
import nl.tudelft.jpacman.npc.Ghost;
import nl.tudelft.jpacman.npc.ghost.Blinky;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * This is a test class for MapParser.
 */
@ExtendWith(MockitoExtension.class)
public class MapParserTest {
    @Mock
    private BoardFactory boardFactory;
    @Mock
    private LevelFactory levelFactory;
    @Mock
    private Blinky blinky;
    @Mock
    private Ghost ghost;
    @Mock
    private Square groundSquare;
    @Mock
    private Square wallSquare;
    @Mock
    private Board board;
    @Mock
    private Level level;

    /**
     * Test for the parseMap method (good map).
     */
    @Test
    public void testParseMapGood() {
        MockitoAnnotations.initMocks(this);
        assertNotNull(boardFactory);
        assertNotNull(levelFactory);

        // Setup mocks for ALL expected creations:
        Mockito.when(boardFactory.createGround()).thenReturn(groundSquare);
        Mockito.when(boardFactory.createWall()).thenReturn(wallSquare);
        Mockito.when(levelFactory.createGhost()).thenReturn(blinky);
        Mockito.when(boardFactory.createBoard(Mockito.any())).thenReturn(board);

        // Use ArgumentCaptor for the final createLevel call to inspect the arguments
        ArgumentCaptor<List> ghostsCaptor = ArgumentCaptor.forClass(List.class);
        ArgumentCaptor<List> startPositionsCaptor = ArgumentCaptor.forClass(List.class);

        // Stub createLevel() with the Captors
        Mockito.when(levelFactory.createLevel(
            Mockito.eq(board), ghostsCaptor.capture(), startPositionsCaptor.capture())).thenReturn(level);

        MapParser mapParser = new MapParser(levelFactory, boardFactory);
        ArrayList<String> map = new ArrayList<>();
        map.add("############");
        map.add("#P        G#");
        map.add("############");

        Level actualLevel = mapParser.parseMap(map);

        // Verify Board Elements Creation (26 Walls, 10 Ground)
        Mockito.verify(boardFactory, Mockito.times(26)).createWall();
        Mockito.verify(boardFactory, Mockito.times(10)).createGround();

        // Verify NPC and Item Creation
        Mockito.verify(levelFactory, Mockito.times(1)).createGhost(); 
        Mockito.verify(levelFactory, Mockito.times(0)).createPellet();

        // Verify Occupancy (Ghost and Pellet are placed on a Ground Square)
        Mockito.verify(blinky, Mockito.times(1)).occupy(groundSquare);

        // Verify Final Board/Level Assembly
        Mockito.verify(boardFactory, Mockito.times(1)).createBoard(Mockito.any(Square[][].class));
        Mockito.verify(levelFactory, Mockito.times(1)).createLevel(
            Mockito.eq(board), Mockito.anyList(), Mockito.anyList()
        );
        assertNotNull(actualLevel);

        // Verify Captured Ghost List
        List<Ghost> capturedGhosts = ghostsCaptor.getValue();
        assertEquals(1, capturedGhosts.size(), "The map should contain exactly one ghost.");
        assertEquals(blinky, capturedGhosts.get(0), "The captured ghost should be the mocked Blinky object.");

        // Verify Captured Start Positions List
        List<Square> capturedStartPositions = startPositionsCaptor.getValue();
        assertEquals(1, capturedStartPositions.size(), "The map should contain exactly one starting position.");
        assertEquals(groundSquare, capturedStartPositions.get(0), "The captured start position should be the mocked GroundSquare.");


    }

}
