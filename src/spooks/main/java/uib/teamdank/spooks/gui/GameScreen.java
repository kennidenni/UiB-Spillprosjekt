package uib.teamdank.spooks.gui;

import java.util.Objects;

import uib.teamdank.common.Game;
import uib.teamdank.spooks.Room;

/**
 * The main gameplay screen.
 */
public class GameScreen extends uib.teamdank.common.gui.GameScreen {

	private Room currentRoom;
	
	public GameScreen(Game game) {
		super(game);
	}

	public void setRoom(Room room) {
		Objects.requireNonNull(room, "room cannot be null");
		this.currentRoom = room;
	}

}