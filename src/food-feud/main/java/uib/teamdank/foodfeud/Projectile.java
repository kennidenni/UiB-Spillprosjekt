package uib.teamdank.foodfeud;

public class Projectile {

	private final double dmg, weight;
	
	public Projectile(double d, double w){
		this.dmg = d;
		this.weight = w;
	}
	
	public double getDmg(){
		return dmg;
	}
	
	public double getWeight() {
		return weight;
	}
	
}
