package uib.teamdank.common;

/**
 * A recipe to combine two items. There is no order to the recipe's ingredients.
 */
public class ItemRecipe {

	/**
	 * Removes the two ingredients required for this recipe from the given
	 * inventory and adds the resulting item. If the inventory does not contain
	 * the ingredients, nothing happens.
	 * 
	 * @return {@code true} if the item was crafted, {@code false} if not
	 */
	public boolean craft(Inventory inventory) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @return the name of the second item in the recipe
	 */
	public String getItem1() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return the name of the second item in the recipe
	 */
	public String getItem2() {
		// TODO Auto-generated method stub
		return null;
	}

}
