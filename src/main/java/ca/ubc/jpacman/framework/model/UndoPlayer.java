package ca.ubc.jpacman.framework.model;

import org.jpacman.framework.model.Direction;
import org.jpacman.framework.model.Player;
import org.jpacman.framework.model.Tile;

public class UndoPlayer extends Player {
	protected int points = 0;
	protected boolean alive = true;
	protected Direction direction = Direction.LEFT;
	protected Tile tile;

	/**
	 * Sets the parameters of the Pacman after undo
	 * 
	 * @param player
	 *            Pacman in the game
	 */
	public UndoPlayer(Player player) {
		points = player.getPoints();
		alive = player.isAlive();
		direction = player.getDirection();
		tile = player.getTile();
	}

	/**
	 * @return the points
	 */
	@Override
	public int getPoints() {
		return this.points;
	}

	/**
	 * @return if player is still alive
	 */
	@Override
	public boolean isAlive() {
		return this.alive;
	}

	/**
	 * @return direction
	 */
	@Override
	public Direction getDirection() {
		return this.direction;
	}

	/**
	 * @return tile
	 */
	@Override
	public Tile getTile() {
		return this.tile;
	}
}
