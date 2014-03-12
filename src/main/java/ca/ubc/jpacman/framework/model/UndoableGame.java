package ca.ubc.jpacman.framework.model;

import java.util.ArrayDeque;
import java.util.Deque;

import org.jpacman.framework.model.Direction;
import org.jpacman.framework.model.Game;
import org.jpacman.framework.model.Ghost;
import org.jpacman.framework.model.Player;

public class UndoableGame extends Game {
    private Deque<GameFrame> frames = new ArrayDeque<GameFrame>();

    public void undo() {
        // System.out.println("UndoableGame");
        if (frames.size() > 1) {
            GameFrame frame = this.frames.pop();
            frame.set(this);
            notifyViewers();
        } else if (frames.size() == 1) {
            GameFrame frame = this.frames.peekFirst();
            frame.set(this);
            notifyViewers();
        } else {
            // System.out.println("Disable user from undoing");
        }
    }

    @Override
    public void movePlayer(Direction dir) {
        frames.push(new GameFrame(this));
        super.movePlayer(dir);
        // System.out.println("====================");
        // System.out.println("Player pushing new game frame into the frames stack");
        // System.out.println("====================");
    }

    @Override
    public void moveGhost(Ghost theGhost, Direction dir) {
        super.moveGhost(theGhost, dir);
        // System.out.println("Ghost pushing new game frame into the frames stack");
        // frames.push(new GameFrame(this));
    }

    @Override
    public void addPlayer(Player p) {
        super.addPlayer(p);
        // System.out.println("====================");
        // System.out.println("!!!!Push the first game frame into the frames stack");
        // System.out.println("====================");
        frames.push(new GameFrame(this));
    }
}
