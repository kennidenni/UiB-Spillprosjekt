package uib.teamdank.cargame;
import uib.teamdank.common.Item;
/**
 * Interface for road objects in the game
 */
public class RoadObjects extends item {
	private boolean death;
	private double fuelPenalty;
	private long scoreIncrease;
	private double fuel;
	
	public RoadObjects (){
		this("name", "desc", false, 0, 0, 0 )
	}
	
	public RoadObjects (String n, String d, boolean d, double fp, long si, double f){
		this.death = d;
		this.fuelPenalty = fp;
		this.scoreIncrease = si;
		this.fuel = f;
	}
	
	/**
	 * @return true if object gives game over
	 */
    public boolean getDeath () {
    return death;
    }
    
    /**
     * @returns fuel lost when colliding with this object
     */
    public int getFuelPenalty (){
    return fuelPenalty;
    }
 
    /**
     * @return high score points
     */
    public int getScoreIncrease(){
    return scoreIncrease;
    }
   
    /**
     * @return increases fuel when collided with this object
     */
    public int getFuel (){
    return fuel;
    }