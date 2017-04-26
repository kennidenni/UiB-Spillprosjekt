package uib.teamdank.cargame;

import uib.teamdank.common.Item;
/**
 * A puddle in the road. Has negative effects if driven over by the
 * {@link Player}.
 */
public class Puddle extends Item {
	private final int penalty = 100;

    public Puddle(String n, String d) {
        super(n, d);
    }
    
    public double getFuelPenalty (){
        return this.penalty;
    }

}
