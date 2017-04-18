package uib.teamdank.common;

import org.junit.Before;
import org.junit.Test;
import uib.teamdank.common.Actor;

import java.util.Random;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

/**
 * Test class for actor
 * @author oli007
 */
public class ActorTest {
    private int maxHealth;
    private int currentHealth;
    private String name;
    private Actor actor;
    private Random ran;


    @Before
    public void setUp() throws Exception {
        ran = new Random();
        maxHealth = ran.nextInt(Integer.MAX_VALUE);
        currentHealth = ran.nextInt(maxHealth);
        name = "Per";
        actor = new Actor(maxHealth, currentHealth, name);
    }

    @Test
    public void testGetHealth() throws Exception {
        assertThat(currentHealth, is(equalTo(actor.getHealth())));
    }

    @Test
    public void testGetMaxHealth() throws Exception {
        assertThat(maxHealth, is(equalTo(actor.getMaxHealth())));
    }

    @Test
    public void testGetName() throws Exception {
        assertThat(name, is(equalTo(actor.getName())));
    }

    @Test
    public void testIncreaseHealth() throws Exception {
        int amount = ran.nextInt(maxHealth);
        int expectedResult = currentHealth + amount;
        actor.increaseHealth(amount);

        if (expectedResult <= maxHealth)
            assertThat(expectedResult, is(equalTo(actor.getHealth())));
        else
            assertThat(maxHealth, is(equalTo(actor.getHealth())));
    }

    @Test
    public void testDecreaseHealth() throws Exception {
        int amount = ran.nextInt(maxHealth);
        int expectedResult = currentHealth - amount;
        actor.decreaseHealth(amount);
        if (expectedResult > 0)
            assertThat(expectedResult, is(equalTo(actor.getHealth())));
        else
            assertThat(0, is(equalTo(actor.getHealth())));
    }

    @Test
    public void testSetHealth() throws Exception {
        int newHealth = ran.nextInt(maxHealth);
        actor.setHealth(newHealth);
        assertThat(newHealth, is(equalTo(actor.getHealth())));
    }

}