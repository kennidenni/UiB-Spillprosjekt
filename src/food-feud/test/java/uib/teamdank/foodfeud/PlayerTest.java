package uib.teamdank.foodfeud;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class PlayerTest {
	
	private Player player;
	
	@Before
	public void setUp() {
		player = new Player(100, "sturle");	
	}

	@Test
	public void noWeapons() {
		assertThat(player.getInventory().getItemCount(), is(equalTo(0)));
	}
	
	@Test
	public void addWeapon() {
		Weapon w = new Weapon("TestWeapon", "TestDescription");
		player.getInventory().addItem(w);
		assertThat(player.getInventory().getItem(0), is(equalTo(w)));
	}
	
	@Test
	public void getHealth(){
		assertThat(player.getHealth(), is(equalTo(100)));
	}
	@Test
	public void getName(){
		assertThat(player.getName(), is(equalTo("sturle")));
	}

}
