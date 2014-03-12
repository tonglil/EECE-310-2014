package ca.ubc.jpacman.framework.ui;

import org.jpacman.framework.ui.PacmanInteraction;

import ca.ubc.jpacman.framework.model.UndoableGame;

public class UndoablePacmanInteraction extends PacmanInteraction {
    public void undo() {
        // We will need to update the state of the game without passing state
        updateState();
        ((UndoableGame) getGame()).undo();
    }
}
