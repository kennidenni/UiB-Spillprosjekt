package uib.teamdank.foodfeud;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.badlogic.gdx.physics.box2d.World;

public class Match {
	
	private static final int MAX_PLAYER_COUNT = 4;
	
	private final Player[] players;
	
	private int turnCount;
	
	public Match(World world, String...playerNames) {
		Objects.requireNonNull(playerNames, "player name list cannot be null");
		Arrays.asList(playerNames).forEach(name -> Objects.requireNonNull(name, "a player name cannot be null"));
		Set<String> names = new HashSet<>(Arrays.asList(playerNames));
		if (names.size() != playerNames.length || names.size() < 2 || names.size() > MAX_PLAYER_COUNT) {
			throw new IllegalArgumentException("there must be 2-" + MAX_PLAYER_COUNT + " players");
		}
		
		this.players = new Player[playerNames.length];
		for (int i = 0; i < players.length; i++) {
			players[i] = new Player(world);
		}
		
	}

	public Player getActivePlayer() {
		return players[turnCount % players.length];
	}
	
	public void nextTurn() {
		turnCount++;
	}
}
