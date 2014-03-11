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
        System.out.println("====================");
        System.out.println("Player pushing new game frame into the frames stack");
        System.out.println("====================");
        frames.push(new GameFrame(this));
    }

    @Override
    public void moveGhost(Ghost theGhost, Direction dir) {
        super.moveGhost(theGhost, dir);
        System.out.println("Ghost pushing new game frame into the frames stack");
        frames.push(new GameFrame(this));
    }
}
