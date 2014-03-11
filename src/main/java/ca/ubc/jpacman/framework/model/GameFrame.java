package ca.ubc.jpacman.framework.model;

import java.util.Stack;

import org.jpacman.framework.model.Food;
import org.jpacman.framework.model.Ghost;
import org.jpacman.framework.model.Player;
import org.jpacman.framework.model.PointManager;
import org.jpacman.framework.model.Tile;

public class GameFrame {
    private UndoPlayer player;
    private Stack<Tile> ghosts = new Stack<Tile>();

    public GameFrame(UndoableGame game) {
        this.player = new UndoPlayer(game.getPlayer());

        for (Ghost ghost : game.getGhosts()) {
            this.ghosts.add(ghost.getTile());
        }
        // for (int i = 0; i < game.getGhosts().size(); i++) {
        // this.ghosts.push(game.getGhosts().get(i).getTile());
        // }
    }

    public void set(UndoableGame game) {
        Player player = game.getPlayer();
        PointManager pm = game.getPointManager();

        // Set points
        int restore = this.player.getPoints() - player.getPoints();
        if (restore != 0) {
            pm.consumePointsOnBoard(player, restore);
            Food food = new Food();
            food.occupy(player.getTile());
        }

        // Set position
        if (this.player.getTile() != null) {
            player.deoccupy();
            player.occupy(this.player.getTile());
        }
        player.setDirection(this.player.getDirection());

        // Set ghosts
        int i = 0;
        for (Ghost ghost : game.getGhosts()) {
            ghost.deoccupy();
            ghost.occupy(this.ghosts.get(i++));
        }
        // for (int i = 0; i < game.getGhosts().size(); i++) {
        // Ghost ghost = game.getGhosts().get(i);
        // ghost.deoccupy();
        // ghost.occupy(this.ghosts.get(i));
        // }
        if (!player.isAlive())
            player.resurrect();
    }
}
