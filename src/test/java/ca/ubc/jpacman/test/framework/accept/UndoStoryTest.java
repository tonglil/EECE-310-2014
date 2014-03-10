package ca.ubc.jpacman.test.framework.accept;

import org.jpacman.framework.model.Direction;

import org.jpacman.framework.model.Tile;
import ca.ubc.jpacman.framework.ui.UndoablePacman;
import ca.ubc.jpacman.test.framework.ui.MovePlayerStoryTest;
import org.junit.Test;
import static org.junit.Assert.*;

public class UndoStoryTest extends MovePlayerStoryTest {
    private UndoablePacman pacman;

    @Override
    public UndoablePacman makeUI(){
        pacman = new UndoablePacman();
        return pacman;
    }

    @Override
    protected UndoablePacman getUI(){
        return pacman;
    }

    @Test
    public void test_S7_1_UndoPlayerLeft(){
        Tile PlayerTile = tileAt(1,1);
        Tile GhostTile = tileAt(2,1);
        getEngine().start();
        getEngine().left();
        assertTrue(getPlayer().getPoints() > 0);
        getUI().getGame().moveGhost(theGhost(), Direction.DOWN);
        getUI().undo();
        assertEquals(PlayerTile, getPlayer().getTile());
        assertEquals(0, getPlayer().getPoints());
        assertEquals(GhostTile, theGhost().getTile());

    }

    @Test
    public void test_S7_2_UndoPlayerRight(){
        Tile PlayerTile = tileAt(1,1);
        Tile GhostTile = tileAt(2,1);
        getEngine().start();
        //Make sure Player doesn't die when moved right, so move ghost first
        getUI().getGame().moveGhost(theGhost(), Direction.DOWN);
        getEngine().right();
        getUI().undo();
        assertEquals(PlayerTile, getPlayer().getTile());
        assertEquals(0, getPlayer().getPoints());
        assertEquals(GhostTile, theGhost().getTile());
    }

    @Test
    public void test_S7_3_UndoPlayerNoMovement(){
        Tile PlayerTile = tileAt(1,1);
        Tile GhostTile = tileAt(2,1);
        getEngine().start();
        getUI().getGame().moveGhost(theGhost(), Direction.DOWN);
        //Moving down from initial state does not move the player(against the wall), so it counts as no movement
        getEngine().down();
        getUI().undo();
        assertEquals(PlayerTile, getPlayer().getTile());
        assertEquals(0, getPlayer().getPoints());
        assertEquals(GhostTile, theGhost().getTile());
    }

    @Test
    public void test_S7_4_UndoPlayerUp(){
        Tile PlayerTile = tileAt(1,1);
        Tile GhostTile = tileAt(2,1);
        getEngine().start();
        //Player moves up
        getEngine().up();
        //Since no points here, moving up won't increment points
        assertEquals(0,getPlayer().getPoints());
        //Ghost moves down
        getUI().getGame().moveGhost(theGhost(), Direction.DOWN);
        getUI().undo();
        assertEquals(PlayerTile, getPlayer().getTile());
        assertEquals(0, getPlayer().getPoints());
        assertEquals(GhostTile, theGhost().getTile());
    }

    @Test
    public void test_S7_5_UndoPlayerDown(){
        Tile Point1Tile = tileAt(0,2);
        Tile GhostTile = tileAt(2,1);
        getEngine().start();
        getUI().getGame().moveGhost(theGhost(), Direction.UP);
        getUI().getGame().moveGhost(theGhost(), Direction.LEFT);
        getEngine().right();
        getEngine().down();
        getUI().undo();
        assertEquals(GhostTile, getPlayer().getTile());
        assertEquals(0, getPlayer().getPoints());
        assertEquals(Point1Tile, theGhost().getTile());

    }

    @Test
    public void test_S7_6_UndoPlayerDies(){
        Tile PlayerTile = tileAt(1,1);
        Tile GhostTile = tileAt(2,1);
        getEngine().start();
        getEngine().right();
        getUI().undo();
        assertTrue(getPlayer().isAlive());
        assertEquals(0, getPlayer().getPoints());
        assertEquals(PlayerTile, getPlayer().getTile());
        assertEquals(GhostTile, theGhost().getTile());
    }

    @Test
    public void test_S7_7_UndoPlayerWins(){
        Tile EmptyTile = tileAt(0,1);
        Tile GhostTile = tileAt(2,1);
        getEngine().start();
        getUI().getGame().moveGhost(theGhost(), Direction.DOWN);
        getEngine().left(); // eat first food
        getEngine().right(); // go back
        getEngine().up(); // move next to final food
        getEngine().right(); // eat final food
        getUI().undo();
        assertFalse(getUI().getGame().getPointManager().allEaten());
        assertEquals(EmptyTile, getPlayer().getTile());
        assertEquals(GhostTile, theGhost().getTile());
    }
}
