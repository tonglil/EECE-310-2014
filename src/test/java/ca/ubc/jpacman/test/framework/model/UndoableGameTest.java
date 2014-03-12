package ca.ubc.jpacman.test.framework.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.jpacman.framework.factory.FactoryException;
import org.jpacman.framework.model.Direction;
import org.jpacman.framework.model.Food;
import org.jpacman.framework.model.Ghost;
import org.jpacman.framework.model.Player;
import org.jpacman.test.framework.model.GameTest;
import org.junit.Test;

import ca.ubc.jpacman.framework.factory.UndoableGameFactory;
import ca.ubc.jpacman.framework.model.UndoableGame;

public class UndoableGameTest extends GameTest {

	@Override
	public UndoableGameFactory makeFactory() {
		return new UndoableGameFactory();
	}

	@Override
	protected UndoableGame makePlay(String singleRow) throws FactoryException {
		return (UndoableGame) super.makePlay(singleRow);
	}

	@Test
	public void testS7_UndoPlayerMovesToEmpty() throws FactoryException {
		UndoableGame UndoGame = makePlay("P #");
		UndoGame.movePlayer(Direction.RIGHT);
		UndoGame.undo();

		assertEquals(tileAt(UndoGame, 0, 0), UndoGame.getPlayer().getTile());
		assertEquals(0, UndoGame.getPlayer().getPoints());
	}

	@Test
	public void testS7_1a_UndoPlayerMovesLeft() throws FactoryException {
		UndoableGame UndoGame = makePlay(" P#");
		UndoGame.movePlayer(Direction.LEFT);
		UndoGame.undo();

		assertEquals(tileAt(UndoGame, 1, 0), UndoGame.getPlayer().getTile());
		assertEquals(0, UndoGame.getPlayer().getPoints());
	}

	@Test
	public void testS7_1b_UndoGhostMovesLeft() throws FactoryException {
		UndoableGame UndoGame = makePlay(" G#");
		Ghost theGhost = (Ghost) UndoGame.getBoard().spriteAt(1, 0);
		UndoGame.moveGhost(theGhost, Direction.LEFT);
		UndoGame.undo();
		assertEquals(tileAt(UndoGame, 1, 0), theGhost.getTile());
	}

	@Test
	public void testS7_1c_UndoPlayerMovesLeftFood() throws FactoryException {
		UndoableGame UndoGame = makePlay(".P#");
		Food food = (Food) UndoGame.getBoard().spriteAt(0, 0);
		Player player = UndoGame.getPlayer();

		UndoGame.movePlayer(Direction.LEFT);
		assertTrue(player.getPoints() > 0);
		UndoGame.undo();
		assertFalse(player.getPoints() > 0);
		assertEquals(tileAt(UndoGame, 1, 0).topSprite(), player);
	}

	@Test
	public void testS7_2a_UndoPlayerMovesRight() throws FactoryException {
		UndoableGame UndoGame = makePlay("P #");
		UndoGame.movePlayer(Direction.RIGHT);
		UndoGame.undo();

		assertEquals(tileAt(UndoGame, 0, 0), UndoGame.getPlayer().getTile());
		assertEquals(0, UndoGame.getPlayer().getPoints());
	}

	@Test
	public void testS7_2b_UndoGhostMovesRight() throws FactoryException {
		UndoableGame UndoGame = makePlay("G #");
		Ghost theGhost = (Ghost) UndoGame.getBoard().spriteAt(0, 0);
		UndoGame.moveGhost(theGhost, Direction.RIGHT);
		UndoGame.undo();
		assertEquals(tileAt(UndoGame, 0, 0), theGhost.getTile());
	}

	@Test
	public void testS7_2c_UndoPlayerMovesRightFood() throws FactoryException {
		UndoableGame UndoGame = makePlay("P.#");
		Food food = (Food) UndoGame.getBoard().spriteAt(1, 0);
		Player player = UndoGame.getPlayer();

		UndoGame.movePlayer(Direction.RIGHT);
		assertTrue(player.getPoints() > 0);
		UndoGame.undo();
		assertFalse(player.getPoints() > 0);
		assertEquals(tileAt(UndoGame, 0, 0).topSprite(), player);
	}

	@Test
	public void testS7_3a_UndoPlayerNoMovement() throws FactoryException {
		UndoableGame UndoGame = makePlay("P#");
		Player player = UndoGame.getPlayer();

		UndoGame.movePlayer(Direction.RIGHT);
		UndoGame.undo();
		assertEquals(tileAt(UndoGame, 0, 0).topSprite(), player);
		assertEquals(tileAt(UndoGame, 0, 0), player.getTile());
	}

	@Test
	public void testS7_3b_UndoGhostNoMovement() throws FactoryException {
		UndoableGame UndoGame = makePlay("G#");
		Ghost theGhost = (Ghost) UndoGame.getBoard().spriteAt(0, 0);
		UndoGame.moveGhost(theGhost, Direction.RIGHT);
		UndoGame.undo();
		assertEquals(tileAt(UndoGame, 0, 0), theGhost.getTile());
	}

}
