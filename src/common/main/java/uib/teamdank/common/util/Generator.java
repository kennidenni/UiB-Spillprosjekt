package uib.teamdank.common.util;

import java.util.List;
import java.util.Random;

/**
 * A generator procedurally creates a certain kind of object.
 *
 * @param <T> the type the generator produces
 */
public interface Generator<T> {

	/**
	 * @param random the random from which to pull random values
	 * @return the procedurally generated object
	 */
	public T generate(Random random);

	/**
	 * Calls {@link #generate(Random)} the given amount of times and fills the
	 * specified list with the results.
	 */
	public default void generate(Random random, int amount, List<T> list) {
		// TODO Auto-generated method stub
	}

}
