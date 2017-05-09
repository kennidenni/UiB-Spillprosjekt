package uib.teamdank.cargame;

/**
 * Denotes a class represents an item in the road the player can interact with.
 */
@FunctionalInterface
public interface RoadEntity {
	public void drivenOverBy(Player player);
}
