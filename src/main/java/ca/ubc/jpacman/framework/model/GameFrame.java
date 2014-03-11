package ca.ubc.jpacman.framework.model;

import java.util.List;

import org.jpacman.framework.model.Player;
import org.jpacman.framework.model.Ghost;

//import org.jpacman.framework.model.Direction;
//import org.jpacman.framework.model.Level;
//import org.jpacman.framework.model.Tile;

public class GameFrame {
    private Player player;
    private List<Ghost> ghosts;

    //private Level level;
    //private Tile tile;

    public GameFrame(UndoableGame game) {
        this.player = game.getPlayer();
        this.ghosts = game.getGhosts();
    }

    public void set(UndoableGame game) {
        System.out.println("setting the game back to previous state");
        //read the information and set the players and ghosts
    }
}
