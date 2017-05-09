package uib.teamdank.foodfeud;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import uib.teamdank.common.util.AssetManager;

public class MatchBuilder {

	private static class MatchPlayer {
		public String name;
		public Team team;
	}

	private final AssetManager assets;

	private final List<MatchPlayer> players = new ArrayList<>();

	public MatchBuilder(AssetManager assets) {
		this.assets = Objects.requireNonNull(assets, "assets cannot be null");
	}

	public MatchBuilder addPlayer(String name, Team team) {
		MatchPlayer player = new MatchPlayer();
		player.name = Objects.requireNonNull(name, "a player name cannot be null");
		player.team = Objects.requireNonNull(team, "a player team cannot be null");

		for (MatchPlayer other : players) {
			if (player.name.equals(other.name)) {
				throw new IllegalArgumentException("that name is already taken: " + player.name);
			}
			if (player.team == other.team) {
				throw new IllegalArgumentException("only one player per team allowed: " + player.team);
			}
		}

		players.add(player);
		return this;
	}

	public Match build() {
		List<Player> playerList = new ArrayList<>(players.size());
		for (MatchPlayer matchPlayer : players) {
			playerList.add(new Player(assets, matchPlayer.team, matchPlayer.name));
		}
		return new Match(playerList);
	}
	
	public void removePlayer(Team t) {
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).team == t) {
				players.remove(i);
				break;
			}
		}
	}

}
