package uib.teamdank.foodfeud;

public enum Team {
	ALPHA("Rock'n'Rollers", 	   "Images/elvis_still_animation.json", "Images/elvis_walking_animation.json", "Images/elvis_falling_animation.json"),
	BETA("Useless Celebrities",    "Images/kim_still_animation.json", "Images/kim_walking_animation.json", "Images/kim_falling_animation.json"),
	CHARLIE("Depressed Magicians", "Images/elvis_still_animation.json", "Images/elvis_walking_animation.json", "Images/elvis_falling_animation.json"),
	DELTA("Annoying Teenagers",    "Images/elvis_still_animation.json", "Images/elvis_walking_animation.json", "Images/elvis_falling_animation.json");
	
	private final String name;
	private final String stillAnimation;
	private final String walkingAnimation;
	private final String fallingAnimation;

	private Team(String name, String stillAnimation, String walkingAnimation, String fallingAnimation) {
		this.name = name;
		this.stillAnimation = stillAnimation;
		this.walkingAnimation = walkingAnimation;
		this.fallingAnimation = fallingAnimation;
	}

	public String getName() {
		return name;
	}

	public String getFallingAnimation() {
		return fallingAnimation;
	}
	
	public String getStillAnimation() {
		return stillAnimation;
	}

	public String getWalkingAnimation() {
		return walkingAnimation;
	}
}
