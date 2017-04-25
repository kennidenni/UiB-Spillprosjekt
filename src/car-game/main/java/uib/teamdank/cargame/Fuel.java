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
    }
<<<<<<< HEAD

    public double getFuelIncrease() {
=======
    public double getFuelIncrease (){
>>>>>>> 9c9deaa70786eea6f5f41535eb0acd055a10bddd
        return fuel;
    }
}