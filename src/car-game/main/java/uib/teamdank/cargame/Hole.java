package uib.teamdank.cargame;

import uib.teamdank.common.Item;

/**
 * A hole in the road. Has negative effects if driven over by the
 * {@link Player}.
 */
public class Hole extends RoadObjects {

    public Hole(String n, String d) {
        super(n, d);
    }

    @Override
    public boolean isMoveable (){
        return false;
    }
}
