package ca.ubc.jpacman.framework.model;

import java.util.List;

import org.jpacman.framework.model.Player;
import org.jpacman.framework.model.PointManager;
import org.jpacman.framework.model.Ghost;
import org.jpacman.framework.model.Food;

public class GameFrame {
    private UndoPlayer player;
    private List<Ghost> ghosts;

    public GameFrame(UndoableGame game) {
        this.player = new UndoPlayer(game.getPlayer());
        ghosts = game.getGhosts();
    }

    public void set(UndoableGame game) {
        Player player = game.getPlayer();
        PointManager pm = game.getPointManager();

        //Set points
        int restore = this.player.getPoints() - player.getPoints();
        if (restore != 0) {
            pm.consumePointsOnBoard(player, restore);
            Food food = new Food();
            food.occupy(player.getTile());
        }

        //Set position
        player.deoccupy();
        player.occupy(this.player.getTile());
        player.setDirection(this.player.getDirection());

        //read the information and set the ghosts
    }
}
