package ca.ubc.jpacman.framework.ui;

import org.jpacman.framework.ui.PacmanInteraction;

import ca.ubc.jpacman.framework.model.UndoableGame;

public class UndoablePacmanInteraction extends PacmanInteraction {
	void undo() {
		updateState();
		UndoableGame undoableGame = (UndoableGame) getGame();
		undoableGame.undo();
	}
}
