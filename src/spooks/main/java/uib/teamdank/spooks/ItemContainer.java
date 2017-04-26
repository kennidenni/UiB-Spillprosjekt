package uib.teamdank.spooks;

import uib.teamdank.common.Inventory;
import uib.teamdank.common.Item;
import uib.teamdank.common.ItemHolder;

/**
 * An item that has inventory of other items. 
 */
public class ItemContainer extends Item implements ItemHolder {

    private final Inventory bag;

    public ItemContainer(String n, String d) {
        super(n, d);
        this.bag = new Inventory();
    }

    @Override
	public Inventory getInventory() {
        return this.bag;
	}

}
