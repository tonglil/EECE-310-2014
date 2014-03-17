package ca.ubc.jpacman.framework.model;

import java.util.ArrayDeque;
import java.util.Deque;

import org.jpacman.framework.model.Direction;
import org.jpacman.framework.model.Game;
import org.jpacman.framework.model.Player;

public class UndoableGame extends Game {
	private Deque<GameFrame> frames = new ArrayDeque<GameFrame>();

	public void undo() {
		if (frames.size() > 1) {
			GameFrame frame = this.frames.pop();
			frame.setNewGameFrame(this);
			notifyViewers();
		} else if (frames.size() == 1) {
			GameFrame frame = this.frames.peekFirst();
			frame.setNewGameFrame(this);
			notifyViewers();
		}
	}

	/**
	 * Move the player in the given direction
	 * 
	 * @param dir
	 *            Direction to move the player
	 */
	@Override
	public void movePlayer(Direction dir) {
		frames.push(new GameFrame(this));
		super.movePlayer(dir);
	}

	/**
	 * Saves the first game frame
	 * 
	 * @param p
	 *            Player
	 */
	@Override
	public void addPlayer(Player p) {
		super.addPlayer(p);
		frames.push(new GameFrame(this));
	}
}
