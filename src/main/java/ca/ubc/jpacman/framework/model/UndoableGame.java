package ca.ubc.jpacman.framework.model;

import java.util.ArrayDeque;
import java.util.Deque;

import org.jpacman.framework.model.Direction;
import org.jpacman.framework.model.Game;
import org.jpacman.framework.model.Player;

public class UndoableGame extends Game {
    private Deque<GameFrame> frames = new ArrayDeque<GameFrame>();

    public void undo() {
    	
        if (frames.size() > 1) {
            GameFrame frame = this.frames.pop();
            frame.set(this);
        } else if (frames.size() == 1) {
            GameFrame frame = this.frames.peekFirst();
            frame.set(this);
        }
        notifyViewers();
    }

    
    @Override
    public void movePlayer(Direction dir) {
        frames.push(new GameFrame(this));
        super.movePlayer(dir);
    }

    /*
     *@Save the first game frame
     */
    @Override
    public void addPlayer(Player p) {
        super.addPlayer(p);
        frames.push(new GameFrame(this));
    }
}
