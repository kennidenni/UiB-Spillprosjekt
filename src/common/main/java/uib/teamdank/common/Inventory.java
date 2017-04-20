package uib.teamdank.common;

import com.badlogic.gdx.utils.Array;

/**
 * A collection of {@link Item}s with a maximum capacity.
 */
public class Inventory {

    private int gold;
    private Array<Item> inventory;
    private int maxCapacity;
    private boolean full;


    public Inventory () {
        this(20);
    }

    public Inventory (int size){
        this(size, 0);
    }

    public Inventory (int size, int gold){
        this.inventory = new Array<>(size);
        this.maxCapacity = size;
        this.gold = gold;
        this.full = false;
    }
	/**
	 * @param amount the amount of gold to add to this inventory
	 */
	public void addGold(int amount) {
		this.gold += amount;
	}
	
	/**
	 * Adds the given item to this inventory, if there is space.
	 */
	public void addItem(Item item) {
		if(this.inventory.size != maxCapacity)
            this.inventory.add(item);

		if(this.inventory.size == maxCapacity)
		    this.full = true;

	}
	
	/**
	 * @return the maximum amount of items this inventory can hold
	 */
	public int getCapacity() {
		return this.maxCapacity;
	}
	
	/**
	 * @return the amount of gold this inventory contains
	 */
	public int getGold() {
		return this.gold;
	}

	/**
	 * @return the amount of items currently in this inventory
	 */
	public int getItemCount() {
		return this.inventory.size;
	}

    /**
     * @param index
     * @return the Item at @index
     */
	public Item getItem(int index){
	    if(index >= inventory.size)
	        return null;

        return inventory.get(index);
    }
	/**
	 * @return an immutable list of the items in this inventory
	 */
	public Array<Item> getItems() {
		return this.inventory;
	}

	/**
	 * @return whether or not this inventory is full
	 */
	public boolean isFull() {
		return this.full;
	}

	/**
	 * @param amount the amount of gold to remove from this inventory
	 */
	public void removeGold(int amount) {
		this.gold = this.gold - amount;
	}

	/**
	 * Removes the item with the given index.
	 */
	public void removeItem(int index) {
		inventory.removeIndex(index);
	}

}
