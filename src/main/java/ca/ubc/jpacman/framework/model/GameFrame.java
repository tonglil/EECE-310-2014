package ca.ubc.jpacman.framework.model;

import java.util.Stack;

import org.jpacman.framework.model.Food;
import org.jpacman.framework.model.Ghost;
import org.jpacman.framework.model.Player;
import org.jpacman.framework.model.PointManager;
import org.jpacman.framework.model.Tile;

public class GameFrame {
	private UndoPlayer player;
	private Stack<Tile> ghosts = new Stack<Tile>();

	public GameFrame(UndoableGame game) {
		// Save the player
		this.player = new UndoPlayer(game.getPlayer());

		// Save the ghosts' positions
		for (Ghost ghost : game.getGhosts()) {
			this.ghosts.add(ghost.getTile());
		}
	}

	/**
	 * Sets the Pacman's points, position, and ghost positions after undo
	 * 
	 * @param game
	 *            UndoableGame
	 */
	public void setNewGameFrame(UndoableGame game) {
		Player player = game.getPlayer();
		PointManager pm = game.getPointManager();

		// Set points
		setPoints(player, pm);
		// Set position
		setPosition(player);
		// Set ghosts
		setGhosts(game);
		// Make sure the player is still alive
		if (!player.isAlive())
			player.resurrect();
	}

	/**
	 * Sets the new points for the player score
	 * 
	 * @param player
	 *            Pacman in the game
	 * @param pm
	 *            PointManager to manage the points
	 */
	protected void setPoints(Player player, PointManager pm) {
		int restore = this.player.getPoints() - player.getPoints();
		if (restore != 0) {
			pm.consumePointsOnBoard(player, restore);
			Food food = new Food();
			food.occupy(player.getTile());
		}
	}

	/**
	 * Sets the new position of the player
	 * 
	 * @param player
	 *            Pacman in the game
	 */
	protected void setPosition(Player player) {
		if (this.player.getTile() != null) {
			player.deoccupy();
			player.occupy(this.player.getTile());
		}
		player.setDirection(this.player.getDirection());
	}

	/**
	 * Sets the new positions of the ghosts
	 * 
	 * @param game
	 *            the UndoableGame
	 */
	protected void setGhosts(UndoableGame game) {
		int i = 0;
		for (Ghost ghost : game.getGhosts()) {
			ghost.deoccupy();
			ghost.occupy(this.ghosts.get(i++));
		}
	}
}
