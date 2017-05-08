package uib.teamdank.foodfeud;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Match {

	public static final int MAX_PLAYER_COUNT = 4;

	private final List<Player> players;

	private int turnCount;

	public Match(List<Player> players) {
		Objects.requireNonNull(players, "player list cannot be null");
		Arrays.asList(players).forEach(name -> Objects.requireNonNull(name, "a player cannot be null"));
		this.players = Collections.unmodifiableList(players);
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

	public Player getWinner() {
		for (Player p : players){
			if(!p.isDead()) 
				return p;
		}
		return null;
	}
}
