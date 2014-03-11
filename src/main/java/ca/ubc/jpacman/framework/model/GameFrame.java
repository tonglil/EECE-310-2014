package ca.ubc.jpacman.framework.model;

import java.util.List;

import org.jpacman.framework.model.Player;
import org.jpacman.framework.model.Ghost;

//import org.jpacman.framework.model.Direction;
//import org.jpacman.framework.model.Level;
//import org.jpacman.framework.model.Tile;

public class GameFrame {
    private UndoPlayer player;
    private List<Ghost> ghosts;

    //private Level level;
    //private Tile tile;

    public GameFrame(UndoableGame game) {
        this.player = new UndoPlayer(game.getPlayer());
        System.out.println("constructor: player has " + player.getPoints() + " points");
        ghosts = game.getGhosts();
    }

    public void set(UndoableGame game) {
        Player player = game.getPlayer();
        System.out.println("set: player had " + this.player.getPoints() + " points");
        //set the player's points back
        player.setDirection(this.player.getDirection());
        //get tile
        //occupy the tile
        //deoccupy
        System.out.println("setting the game back to previous state");
        //read the information and set the players and ghosts
    }
}
