package ca.ubc.jpacman.framework.ui;

import org.jpacman.framework.ui.MainUI;
import org.jpacman.framework.factory.FactoryException;
import ca.ubc.jpacman.framework.factory.UndoableGameFactory;
import ca.ubc.jpacman.framework.model.UndoableGame;

public class UndoablePacman extends MainUI {
    public void undo() {
        // TODO Auto-generated method stub
    }

    @Override
    public MainUI initialize() throws FactoryException {
        withFactory(new UndoableGameFactory());
        return super.initialize();
    }

    @Override
    public UndoableGame getGame() {
        return (UndoableGame) super.getGame();
    }
}
