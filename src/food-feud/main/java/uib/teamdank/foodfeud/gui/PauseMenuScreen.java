package uib.teamdank.foodfeud.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import uib.teamdank.common.Game;
import uib.teamdank.common.gui.MenuScreen;

public class PauseMenuScreen extends MenuScreen implements uib.teamdank.common.gui.PauseMenuScreen {
	private static final String PAUSE = "Images/Buttons/ff_pause.png";
	private static final String BACK = "Images/Buttons/ff_back.png";
	private static final String EXIT = "Images/Buttons/ff_quit.png";

	private Table menu;
	private ImageButton pauseButton;
	private Array<Button> buttons;
	private Game game;

	public PauseMenuScreen(Game game) {
		super();
		this.game = game;

		pauseButton = createButton(PAUSE, null);
		

		menu = new Table();
		buttons = new Array<>();

		buttons.add(createButton(BACK, () -> resume()));
		buttons.add(createButton(EXIT, () -> exitToStartMenu()));

		menu.add(pauseButton).pad(0, 0, 20, 0);
		menu.row();
		menu.pad(50);
		for (Button button : buttons) {
			menu.add(button).width((float) (button.getWidth() / 4)).height((float) (button.getHeight() / 4)).pad(5);
			menu.row();
		}

		menu.setFillParent(true);
		getStage().addActor(menu);
	}


	@Override
	public void exitToStartMenu() {
		game.setScreen(game.getStartMenuScreen());
	}


	@Override
	public void render(float delta) {
		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			resume();
			return;
		}
		super.render(delta);
	}

	@Override
	public void resume() {
		game.setScreen(game.getGameScreen());
	}

	@Override
	public void resumeGame() {
		// TODO Auto-generated method stub
	}

}