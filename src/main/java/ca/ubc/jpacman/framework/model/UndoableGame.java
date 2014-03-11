package ca.ubc.jpacman.framework.model;

import java.util.Stack;

import org.jpacman.framework.model.Game;
import org.jpacman.framework.model.Direction;
import org.jpacman.framework.model.Ghost;
import org.jpacman.framework.model.Player;

public class UndoableGame extends Game {
    private Stack<GameFrame> frames = new Stack<GameFrame>();

    public void undo() {
        System.out.println("UndoableGame");
        if (!frames.empty()) {
            GameFrame frame = this.frames.pop();
            frame.set(this);
            notifyViewers();
        } else {
            System.out.println("Disable user from undoing");
        }
    }

    @Override
    public void movePlayer(Direction dir) {
        System.out.println("====================");
        System.out.println("Player pushing new game frame into the frames stack");
        System.out.println("====================");
        frames.push(new GameFrame(this));
        super.movePlayer(dir);
    }

    @Override
    public void moveGhost(Ghost theGhost, Direction dir) {
        System.out.println("Ghost pushing new game frame into the frames stack");
        frames.push(new GameFrame(this));
        super.moveGhost(theGhost, dir);
    }

    @Override
    public void addPlayer(Player p) {
        super.addPlayer(p);
        System.out.println("====================");
        System.out.println("!!!!Push the first game frame into the frames stack");
        System.out.println("====================");
        frames.push(new GameFrame(this));
    }
}
