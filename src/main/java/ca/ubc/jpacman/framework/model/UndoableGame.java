package ca.ubc.jpacman.framework.model;

import org.jpacman.framework.model.Direction;
import org.jpacman.framework.model.Game;
import org.jpacman.framework.model.Ghost;

public class UndoableGame extends Game {
	public void undo() {
		// TODO Auto-generated method stub
	}

	@Override
	public void movePlayer(Direction dir) {
		super.movePlayer(dir);
	}

	@Override
	public void moveGhost(Ghost theGhost, Direction dir) {
		super.moveGhost(theGhost, dir);
	}
}
