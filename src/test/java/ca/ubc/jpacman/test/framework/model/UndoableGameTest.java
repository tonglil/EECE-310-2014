package ca.ubc.jpacman.test.framework.model;

import ca.ubc.jpacman.framework.factory.UndoableGameFactory;
import ca.ubc.jpacman.framework.model.UndoableGame;
import org.jpacman.framework.factory.FactoryException;
import org.jpacman.test.framework.model.GameTest;

public class UndoableGameTest extends GameTest {
    @Override
    public UndoableGameFactory makeFactory() {
        return new UndoableGameFactory();
    }

    @Override
    protected UndoableGame makePlay(String singleRow) throws FactoryException {
        return (UndoableGame) super.makePlay(singleRow);
    }
}
