package uib.teamdank.cargame;

import uib.teamdank.common.Actor;

/**
 * A pedestrian in the road. Has positive effects if driven over by the
 * {@link Player}.
 */
public class Pedestrian extends Actor {
	private long score;
	public Pedestrian (long s){
    this.score = s;
    }
    public long getscore(){
	    return score;

    }
}
