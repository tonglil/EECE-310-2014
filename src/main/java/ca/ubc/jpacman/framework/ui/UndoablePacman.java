package ca.ubc.jpacman.framework.ui;

import org.jpacman.framework.factory.IGameFactory;
import org.jpacman.framework.model.IGameInteractor;
import org.jpacman.framework.ui.MainUI;

import ca.ubc.jpacman.framework.factory.UndoableGameFactory;
import ca.ubc.jpacman.framework.model.UndoableGame;

public class UndoablePacman extends MainUI {
	private IGameFactory fact;

	public UndoablePacman() {
		fact = new UndoableGameFactory();
		withFactory(fact);
	}

	@Override
	public IGameInteractor getGame() {
		return (UndoableGame) super.getGame();
	}

	public void undo() {
		// TODO Auto-generated method stub
	}

}
