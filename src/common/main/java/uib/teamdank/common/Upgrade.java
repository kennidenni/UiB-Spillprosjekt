package uib.teamdank.common;

/**
 * An upgrade specifies a set of positive changes that can be made to an
 * {@link Upgradeable}.
 */
public interface Upgrade {

	/**
	 * @return a user friendly description of this upgrade
	 */
	public String getDescription();

	/**
	 * @return the level of this upgrade, which impacts its effects
	 */
	public int getLevel();

}
