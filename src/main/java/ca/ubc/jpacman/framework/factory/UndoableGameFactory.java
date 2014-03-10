package ca.ubc.jpacman.framework.factory;

import org.jpacman.framework.factory.DefaultGameFactory;

import ca.ubc.jpacman.framework.model.UndoableGame;

public class UndoableGameFactory extends DefaultGameFactory {

	private transient UndoableGame theUndoableGame;

	@Override
	public UndoableGame makeGame() {
		theUndoableGame = new UndoableGame();
		return theUndoableGame;
	}

	@Override
	protected UndoableGame getGame() {
		assert theUndoableGame != null;
		return theUndoableGame;
	}

}
