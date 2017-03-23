package uib.teamdank.common;

/**
 * An upgradeable object, which means it can receive an {@link Upgrade} to
 * permanently have certain properties positively changed.
 */
public interface Upgradeable {

	/**
	 * @param upgrade
	 *            the upgrade to apply to this upgradeable object
	 */
	public void applyUpgrade(Upgrade upgrade);

}
