package ca.ubc.jpacman.framework.ui;

import org.jpacman.framework.factory.FactoryException;
import org.jpacman.framework.ui.MainUI;

import ca.ubc.jpacman.framework.factory.UndoableGameFactory;
import ca.ubc.jpacman.framework.model.UndoableGame;

public class UndoablePacman extends MainUI {
	private static final long serialVersionUID = -30069730355210465L;
	private UndoableButtonPanel buttonPanel;

	public UndoableButtonPanel getButtonPanel() {
		return this.buttonPanel;
	}

	public void undo() {
		getGame().undo();
	}

	@Override
	public MainUI initialize() throws FactoryException {
		this.buttonPanel = new UndoableButtonPanel();
		withButtonPanel(getButtonPanel());
		withModelInteractor(new UndoablePacmanInteraction());
		withFactory(new UndoableGameFactory());
		return super.initialize();
	}

	@Override
	public UndoableGame getGame() {
		return (UndoableGame) super.getGame();
	}

	public static void main(String[] args) throws FactoryException {
		new UndoablePacman().main();
	}
}
