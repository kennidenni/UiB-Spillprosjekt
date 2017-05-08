package uib.teamdank.cargame.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import uib.teamdank.cargame.CarGame;
import uib.teamdank.cargame.Player;
import uib.teamdank.common.Game;
import uib.teamdank.common.gui.MenuScreen;
import uib.teamdank.common.util.WeatherData;
import uib.teamdank.common.util.WeatherData.WeatherType;
import uib.teamdank.common.gui.CreditScreen;

public class StartMenuScreen extends MenuScreen implements uib.teamdank.common.gui.StartMenuScreen {
	private static final String LOGO = "Images/CarGameLogo.png";
	private static final String PLAY = "Images/Buttons/start.png";
	private static final String HIGHSCORE = "Images/Buttons/cg_highscore.png";
	private static final String SHOP = "Images/Buttons/bs_shop.png";
	private static final String CREDIT = "Images/Buttons/bs_credit.png";
	private static final String EXIT = "Images/Buttons/bs_quit.png";

	private Stage stages;
	private Table menu;
	private ImageButton logoButton;
	private Array<Button> buttons;
	private HighscoreMenuScreen highscoreMenuScreen;
	private CreditScreen creditScreen;
	private Game game;
	private ShopScreen shopScreen;
	private Player player;
	private WeatherData wData;

	public StartMenuScreen(CarGame game) {
		this.game = game;
		stages = new Stage(new FitViewport(1920, 1080));
		highscoreMenuScreen = new HighscoreMenuScreen(game);
		creditScreen = new CreditScreen(game, "Images/Buttons/bs_back.png", "Data/credit_crasher.txt");
		shopScreen = new ShopScreen(game);
		buttons = new Array<Button>();
		menu = new Table();
		
		// Weather Data
		wData = game.getWeatherData();
		
		logoButton = setupButton(LOGO);
		buttons.add(createButton(PLAY, () -> newGame()));
		buttons.add(createButton(HIGHSCORE, () -> viewHighscores()));
		buttons.add(createButton(SHOP, () -> viewShop()));
		buttons.add(createButton(CREDIT, () -> viewCredit()));
		buttons.add(createButton(EXIT, () -> exitGame()));

		menu.add(logoButton).height((float) (logoButton.getHeight() /1.3)).pad(10, 0, 0, 0);
		menu.row();
		addButtonsToTable(menu, buttons);

		// Player initialization
		player = game.getPlayer();
		player.setWeatherType(getWeather());
		player.getTexture();
		player.setScale(.5f);
				
				
		menu.setFillParent(true);
		stages.addActor(menu);
		Gdx.input.setInputProcessor(stages);
	}

	@Override
	public void exitGame() {
		Gdx.app.exit();
	}

	@Override
	public void hide() {
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stages.act(delta);
		stages.draw();
	}

	@Override
	public void resize(int width, int height) {
		stages.getViewport().update(width, height, true);
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(stages);
	}

	@Override
	public void viewHighscores() {
		game.setScreen(highscoreMenuScreen);
	}

	public void viewCredit() {
		game.setScreen(creditScreen);
	}
	
	public void viewShop() {
		game.setScreen(shopScreen);
	}
	
	@Override
	public void newGame() {
		game.setScreen(game.newGame());
	}
	
	@Override
	public void dispose() {
		//TODO
	}

	@Override
	public void pause() {
		//TODO
	}

	@Override
	public void resume() {
		//TODO
	}
	
	public Player getPlayer(){
		return player;
	}
	
	public WeatherType getWeather() {
		return wData.pullWeather("Norway", "Hordaland", "Bergen", "Bergen");
	}
}