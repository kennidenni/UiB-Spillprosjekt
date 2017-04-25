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
        this.Penalty = 100;
    }
    public int getFuelPenalty (){
        return penalty;
        }

    @Override
    public boolean isMoveable (){
        return false;
    }
}
