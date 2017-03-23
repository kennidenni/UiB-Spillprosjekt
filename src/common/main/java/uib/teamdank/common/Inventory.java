package uib.teamdank.common;

import java.util.List;

/**
 * A collection of {@link Item}s with a maximum capacity.
 */
public class Inventory {

	/**
	 * @param amount the amount of gold to add to this inventory
	 */
	public void addGold(int amount) {
		// TODO Auto-generated method stub
	}
	
	/**
	 * Adds the given item to this inventory, if there is space.
	 */
	public void addItem(Item item) {
		// TODO Auto-generated method stub
	}
	
	/**
	 * @return the maximum amount of items this inventory can hold
	 */
	public int getCapacity() {
		// TODO Auto-generated method stub
		return -1;
	}
	
	/**
	 * @return the amount of gold this inventory contains
	 */
	public int getGold() {
		// TODO Auto-generated method stub
		return -1;
	}

	/**
	 * @return the amount of items currently in this inventory
	 */
	public int getItemCount() {
		// TODO Auto-generated method stub
		return -1;
	}

	/**
	 * @return an immutable list of the items in this inventory
	 */
	public List<Item> getItems() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return whether or not this inventory is full
	 */
	public boolean isFull() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @param amount the amount of gold to remove from this inventory
	 */
	public void removeGold(int amount) {
		// TODO Auto-generated method stub
	}

	/**
	 * Removes the item with the given index.
	 */
	public void removeItem(int index) {
		// TODO Auto-generated method stub
	}

}
