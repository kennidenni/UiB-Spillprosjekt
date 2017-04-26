package uib.teamdank.spooks;

import uib.teamdank.common.Actor;
import uib.teamdank.common.Inventory;
import uib.teamdank.common.ItemHolder;

/**
 * The tower defence player (who doesn't actually appear in the game).
 */
public class Player extends Actor implements ItemHolder {

    Inventory bag;

    public Player() {
        super();
        bag = new Inventory();
    }

    @Override
    public Inventory getInventory() {
        return this.bag;
    }

}
