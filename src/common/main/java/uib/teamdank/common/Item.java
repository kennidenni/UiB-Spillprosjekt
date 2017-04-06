package uib.teamdank.common;

/**
 * Interface for Items in the game
 */
public class Item extends GameObject {

    private String description;
    private String name;

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
