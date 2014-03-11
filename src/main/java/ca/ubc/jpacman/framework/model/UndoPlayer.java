package ca.ubc.jpacman.framework.model;

import org.jpacman.framework.model.Player;
import org.jpacman.framework.model.Direction;

public class UndoPlayer extends Player {
    protected int points = 0;
    protected boolean alive = true;
    protected Direction direction = Direction.LEFT;

    /*
     *Copy constructor
     */
    public UndoPlayer(Player player) {
        points = player.getPoints();
        alive = player.isAlive();
        direction = player.getDirection();
    }

    @Override
    public int getPoints() {
        return points;
    }

    @Override
    public boolean isAlive() {
        return alive;
    }

    @Override
    public Direction getDirection() {
        return direction;
    }
}
