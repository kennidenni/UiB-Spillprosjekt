package uib.teamdank.spooks;

import java.util.List;

import uib.teamdank.common.Inventory;
import uib.teamdank.common.ItemHolder;
import uib.teamdank.common.ItemRecipe;
import uib.teamdank.common.util.Difficulty;

/**
 * A room that has a time limit and items in it.
 */
public class Room implements ItemHolder {

	public Difficulty getDifficulty() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Inventory getInventory() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return an immutable list of the available crafting recipes in this room
	 */
	public List<ItemRecipe> getRecipes() {
		// TODO Auto-generated method stub
		return null;
	}

	public double getTimeLimit() {
		// TODO Auto-generated method stub
		return -1;
	}

	/**
	 * @return whether player has performed the necessary actions to solve this
	 *         room
	 */
	public boolean isSolved() {
		// TODO Auto-generated method stub
		return false;
	}
}
