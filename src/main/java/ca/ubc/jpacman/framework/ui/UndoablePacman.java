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
        // TODO Auto-generated method stub
        // System.out.println("UndoablePacman");
        getGame().undo();
    }

    @Override
    public MainUI initialize() throws FactoryException {
        withFactory(new UndoableGameFactory());
        this.buttonPanel = new UndoableButtonPanel();
        withButtonPanel(getButtonPanel());
        withModelInteractor(new UndoablePacmanInteraction());
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
