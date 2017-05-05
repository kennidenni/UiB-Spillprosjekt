package uib.teamdank.foodfeud;

/**
 * Represents a team in the game.
 * <p>
 * Each team specifies the following paths in string format (with
 * the name of the character in the team as the only argument) to
 * locate the appropriate resources:
 * <ul>
 * <li>{@code Images/%s_still_animation.json}</li>
 * <li>{@code Images/%s_walking_animation.json}</li>
 * <li>{@code Images/%s_falling_animation.json}</li>
 * </ul> 
 */
public enum Team {
	ALPHA("Rock'n'Rollers", "elvis"),
	BETA("Useless Celebrities", "kim"),
	CHARLIE("Depressed Magicians", "guy1"),
	DELTA("Annoying Teenagers", "guy2");
	
	private static final String STILL_ANIMATION = "Images/%s_still_animation.json";
	private static final String WALKING_ANIMATION = "Images/%s_walking_animation.json";
	private static final String FALLING_ANIMATION = "Images/%s_falling_animation.json";
	
	private final String name;
	private final String characterName;

	private Team(String name, String characterName) {
		this.name = name;
		this.characterName = characterName;
	}

	public String getName() {
		return name;
	}

	public String getFallingAnimation() {
		return String.format(FALLING_ANIMATION, characterName);
	}
	
	public String getStillAnimation() {
		return String.format(STILL_ANIMATION, characterName);
	}

	public String getWalkingAnimation() {
		return String.format(WALKING_ANIMATION, characterName);
	}
}
