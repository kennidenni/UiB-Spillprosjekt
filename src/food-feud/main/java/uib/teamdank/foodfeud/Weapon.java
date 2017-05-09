/*******************************************************************************
 * Copyright (C) 2017  TeamDank
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/
package uib.teamdank.foodfeud;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import uib.teamdank.common.Item;

/**
 * Represents a weapon item. A weapon can be thrown (distance depends of on
 * weight and thrower) and inflicts damage on impact.
 */
public class Weapon extends Item {

	private final int dmg, weight;
	private final WeaponProjectile proj;

	private boolean damagedPlayer = false;

	public enum WeaponProjectile {
		// light bullets like guns
		LIGHT_BALLISTIC,
		// like uzi, HK416.. etc
		BURST_BALLISTIC,
		// arching ballistics like rpg and grenades..(only exploding one)
		HEAVY_BALLISTIC,
		// shotgun etc
		SPRAY_BALLISTIC;

	}

	/**
	 * Creates a weapon that does no damage and weighs nothing.
	 * 
	 * @param name
	 * @param descr
	 *            weapon description
	 */
	public Weapon(String name, String descr, int d, int w, TextureRegion texture, WeaponProjectile p) {
		super(name, descr);
		this.dmg = d;
		this.weight = w;
		setTexture(texture);
		proj = p;
	}

	public int getDamage() {
		return dmg;
	}

	public double getWeight() {
		return weight;
	}

	public WeaponProjectile getProjectileType() {
		return proj;
	}

	public void hitPlayer(Player player) {
		if (!damagedPlayer) {
			player.decreaseHealth(getDamage());
			this.markForRemoval();
			damagedPlayer = true;
		}
	}

}
