package ca.ubc.jpacman.test.framework.accept;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.jpacman.framework.factory.FactoryException;
import org.jpacman.framework.model.Direction;
import org.jpacman.framework.model.Tile;
import org.junit.Test;

import ca.ubc.jpacman.framework.ui.UndoablePacman;
import ca.ubc.jpacman.test.framework.ui.MovePlayerStoryTest;

public class UndoStoryTest extends MovePlayerStoryTest {
    private UndoablePacman pacman;

    @Override
    public UndoablePacman makeUI() {
        pacman = new UndoablePacman();
        try {
            return (UndoablePacman) pacman.initialize();
        } catch (FactoryException e) {
            return null;
        }
    }

    @Override
    protected UndoablePacman getUI() {
        return pacman;
    }

    // Scenario S7.1: Undo left
    // Given the game is suspended,
    // and the Pacman's previous action was 'move left';
    // When I press undo;
    // Then the Pacman moves back right,
    // if the Pacman ate anything:
    // the food is put back,
    // points are reversed,
    // the ghosts undo position to one time unit ago.
    @Test
    public void test_S7_1_UndoPlayerLeft() {
        Tile PlayerTile = tileAt(1, 1);
        Tile GhostTile = tileAt(2, 1);
        getEngine().start();
        getEngine().left();
        assertTrue(getPlayer().getPoints() > 0);
        getUI().getGame().moveGhost(theGhost(), Direction.DOWN);
        getUI().undo();
        // getUI().getGame().undo();
        assertEquals(PlayerTile, getPlayer().getTile());
        assertEquals(0, getPlayer().getPoints());
        assertEquals(GhostTile, theGhost().getTile());
    }

    // Scenario S7.2: Undo Right
    // Given the game is suspended,
    // and the Pacman's previous action was 'move right';
    // When I press undo;
    // Then the Pacman moves back left,
    // if the Pacman ate anything:
    // the food is put back,
    // points are reversed,
    // the ghosts undo position to one time unit ago.
    @Test
    public void test_S7_2_UndoPlayerRight() {
        Tile PlayerTile = tileAt(1, 1);
        Tile GhostTile = tileAt(2, 1);
        getEngine().start();
        // Make sure Player doesn't die when moved right, so move ghost first

        getEngine().right();
        getUI().getGame().moveGhost(theGhost(), Direction.RIGHT);
        getUI().undo();
        // getUI().getGame().undo();
        assertEquals(PlayerTile, getPlayer().getTile());
        assertEquals(0, getPlayer().getPoints());
        assertEquals(GhostTile, theGhost().getTile());
    }

    // Scenario S7.3: Undo No-movement
    // Given the game is suspended,
    // and the Pacman's previous action was 'no-movement';
    // When I press undo;
    // Then the Pacman does not move,
    // the ghosts undo position to one time unit ago.
    @Test
    public void test_S7_3_UndoPlayerNoMovement() {
        Tile PlayerTile = tileAt(1, 1);
        Tile GhostTile = tileAt(2, 1);
        getEngine().start();

        // Moving down from initial state does not move the player(against the wall), so it counts
        // as no movement
        getEngine().down();
        getUI().getGame().moveGhost(theGhost(), Direction.DOWN);
        getUI().undo();
        // getUI().getGame().undo();
        assertEquals(PlayerTile, getPlayer().getTile());
        assertEquals(0, getPlayer().getPoints());
        assertEquals(GhostTile, theGhost().getTile());
    }

    // Scenario S7.4: Undo Up
    // Given the game is paused,
    // and the Pacman's previous action is moving up,
    // When I press undo;
    // Then move Pacman back down,
    // If the Pacman ate anything:
    // Then the food is put back,
    // and points are reversed,
    // and the ghosts undo position to one time unit ago.
    @Test
    public void test_S7_4_UndoPlayerUp() {
        Tile PlayerTile = tileAt(1, 1);
        Tile GhostTile = tileAt(2, 1);
        getEngine().start();
        // Player moves up
        getEngine().up();
        // Since no points here, moving up won't increment points
        assertEquals(0, getPlayer().getPoints());
        // Ghost moves down
        getUI().getGame().moveGhost(theGhost(), Direction.DOWN);
        getUI().undo();
        // getUI().getGame().undo();
        assertEquals(PlayerTile, getPlayer().getTile());
        assertEquals(0, getPlayer().getPoints());
        assertEquals(GhostTile, theGhost().getTile());
    }

    // Scenario S7.5: Undo Down.
    // Given the game is paused,
    // and the Pacman's previous action is moving down,
    // When I press undo;
    // Then move Pacman back up,
    // If the Pacman ate anything:
    // Then the food is put back,
    // and points are reversed,
    // and the ghosts undo position to one time unit ago.
    @Test
    public void test_S7_5_UndoPlayerDown() {
        Tile Point1Tile = tileAt(0, 2);
        Tile PlayerTile = tileAt(1, 1);
        Tile GhostTile = tileAt(2, 1);
        getEngine().start();

        // getEngine().right();
        getEngine().down();
        getUI().getGame().moveGhost(theGhost(), Direction.UP);
        getUI().getGame().moveGhost(theGhost(), Direction.LEFT);
        // getUI().getGame().undo();
        getUI().undo();

        assertEquals(PlayerTile, getPlayer().getTile());
        assertEquals(0, getPlayer().getPoints());
        assertEquals(GhostTile, theGhost().getTile());

    }

    // Scenario S7.6: The player dies
    // Given the pacman dies;
    // When I press undo;
    // Then my Pacman back to its place before it dies,
    // and points go back if my Pacman ate anything,
    // and food puts back if my Pacman ate it,
    // and the ghosts move back to the place before my Pacman dies.
    @Test
    public void test_S7_6_UndoPlayerDies() {
        Tile PlayerTile = tileAt(1, 1);
        Tile GhostTile = tileAt(2, 1);
        getEngine().start();
        getEngine().right();
        assertFalse(getPlayer().isAlive());
        // getUI().getGame().undo();
        getUI().undo();
        assertTrue(getPlayer().isAlive());
        assertEquals(0, getPlayer().getPoints());
        assertEquals(PlayerTile, getPlayer().getTile());
        assertEquals(GhostTile, theGhost().getTile());
    }

    // Scenario S7.6: The player wins
    // Given the pacman wins;
    // When I press undo;
    // Then my Pacman back to its place before it wins,
    // and points go back if my Pacman ate anything,
    // and food puts back if my Pacman ate it,
    // and the ghosts move back to the place before my Pacman wins.
    @Test
    public void test_S7_7_UndoPlayerWins() {
        Tile PlayerTile = tileAt(1, 1);
        Tile EmptyTile = tileAt(1, 0);
        Tile GhostTile = tileAt(2, 1);
        getEngine().start();
        getEngine().left(); // eat first food
        getEngine().right(); // go back
        getEngine().up(); // move next to final food
        getEngine().right(); // eat final food
        getUI().getGame().moveGhost(theGhost(), Direction.DOWN);
        assertTrue(getUI().getGame().getPointManager().allEaten());
        getUI().getGame().undo();
        // getUI().undo();
        assertFalse(getUI().getGame().getPointManager().allEaten());
        assertEquals(EmptyTile, getPlayer().getTile());
        assertEquals(GhostTile, theGhost().getTile());
    }
}
