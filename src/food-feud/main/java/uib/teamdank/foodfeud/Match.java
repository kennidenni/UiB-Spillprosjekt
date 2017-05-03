package uib.teamdank.foodfeud;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Match {
	
	private static final int MAX_PLAYER_COUNT = 4;
	
	private final List<Player> players;
	
	private int turnCount;
	
	public Match(String...playerNames) {
		Objects.requireNonNull(playerNames, "player name list cannot be null");
		Arrays.asList(playerNames).forEach(name -> Objects.requireNonNull(name, "a player name cannot be null"));
		Set<String> names = new HashSet<>(Arrays.asList(playerNames));
		if (names.size() != playerNames.length || names.size() < 2 || names.size() > MAX_PLAYER_COUNT) {
			throw new IllegalArgumentException("there must be 2-" + MAX_PLAYER_COUNT + " players");
		}
		
		List<Player> playerList = new ArrayList<>(playerNames.length);
		for (String playerName : playerNames) {
			playerList.add(new Player(playerName));
		}
		this.players = Collections.unmodifiableList(playerList);
	}

	public Player getActivePlayer() {
		return players.get(turnCount % players.size());
	}
	
	public List<Player> getPlayers() {
		return players;
	}
	
	public void nextTurn() {
		turnCount++;
	}
}
