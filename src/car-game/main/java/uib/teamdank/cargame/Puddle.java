package uib.teamdank.cargame;

import uib.teamdank.common.Item;
/**
 * A puddle in the road. Has negative effects if driven over by the
 * {@link Player}.
 */
public class Puddle extends Item {
	private double penalty;

    public Puddle(String n, String d) {
        super(n, d);
        this.penalty = 100;
    }
    public double getFuelPenalty (){
        return this.penalty;
        }
<<<<<<< HEAD

=======
>>>>>>> 9c9deaa70786eea6f5f41535eb0acd055a10bddd
}
