package uib.teamdank.cargame;

import uib.teamdank.common.Actor;

/**
 * A pedestrian in the road. Has positive effects if driven over by the
 * {@link Player}.
 */
public class Pedestrian extends Actor {
	private long score;
	private long speed;
	
	public Pedestrian (long sp,long sc){
    this.score = sc;
    this.speed=sp;
    }
    public long getScore(){
	    return score;

    }
    public void driveOver(Player player){
    	player.getScore().addToScore(this.score);
    	this.markForRemoval();
    }
}
