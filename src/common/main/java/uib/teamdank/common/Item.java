package uib.teamdank.common;

/**
 * Interface for Items in the game
 */
public class Item extends GameObject {

    private final String description;
    private final String name;

    public Item (String n, String d){
    	this.name = n;
    	this.description = d;
	}
    /**
     *
     * @return description of this item
     */
	public String getDescription() {
		return description;
	}

    /**
     *
     * @return name of this item
     */
	public String getName() {
		return name;
	}
}
