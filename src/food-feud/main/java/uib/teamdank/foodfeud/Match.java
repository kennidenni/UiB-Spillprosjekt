/*******************************************************************************
 * Copyright (C) 2017  TeamDank
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/
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
	    Player winner = null;
	    for (Player player : players) {
	        if (!player.isDead()) {
	            if (winner != null) {
	                return null;
	            }
	            winner = player;
	        }
	    }
	    return winner;
	}
}
