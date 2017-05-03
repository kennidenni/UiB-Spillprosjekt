//package uib.teamdank.foodfeud;
//
//import static org.junit.Assert.*;
//
//import org.junit.Before;
//import org.junit.Test;
//import static org.hamcrest.core.Is.is;
//import static org.hamcrest.core.IsEqual.equalTo;
//
//public class WeaponTest {
//	
//	private Weapon weapon;
//	private double dmg, weight;
//	
//	@Before
//	public void setUp() {
//		String name = "TestWeapon";
//		String descr = "Usually fed to racing horses.. Does a million dmg and weighs a lot.";
//		dmg = 1.0e6;
//		weight = Double.MAX_VALUE;
//		weapon = new Weapon(name, descr, dmg, weight);
//	}
//
//    @Test
//    public void damage() throws Exception {
//        assertThat(dmg, is(equalTo(weapon.getDamage())));
//    }
//    @Test
//    public void weight() throws Exception {
//        assertThat(weight, is(equalTo(weapon.getWeight())));
//    }
//
//}
