package uib.teamdank.cargame;

import uib.teamdank.common.Item;
/**
 * A puddle in the road. Has negative effects if driven over by the
 * {@link Player}.
 */
public class Fuel extends Item {
    private double fuel;

    public Fuel(String n, String d) {
        super(n, d);
        this.fuel = 100;
        this.moveable = false
    }
    public int getFuelIncrease (){
        return fuel;
    }

    @Override
    public boolean isMoveable (){
        return false;
    }
}
