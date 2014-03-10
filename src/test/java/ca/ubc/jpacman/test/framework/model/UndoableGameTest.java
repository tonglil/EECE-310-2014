package ca.ubc.jpacman.test.framework.model;

import org.jpacman.framework.factory.FactoryException;
import org.jpacman.test.framework.model.GameTest;

import ca.ubc.jpacman.framework.model.UndoableGame;

public class UndoableGameTest extends GameTest {

	@Override
	protected UndoableGame makePlay(String singleRow) throws FactoryException {
		// TODO Auto-generated method stub
		return (UndoableGame) super.makePlay(singleRow);
	}

}
