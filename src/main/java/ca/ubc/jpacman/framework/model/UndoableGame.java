package ca.ubc.jpacman.framework.model;

import java.util.List;
import java.util.Stack;

import org.jpacman.framework.model.Direction;
import org.jpacman.framework.model.Game;
import org.jpacman.framework.model.Ghost;

public class UndoableGame extends Game {
    private Stack<GameFrame> frames = new Stack<GameFrame>();

    public void undo() {
        GameFrame frame = this.frames.pop();
        frame.set(this);
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
