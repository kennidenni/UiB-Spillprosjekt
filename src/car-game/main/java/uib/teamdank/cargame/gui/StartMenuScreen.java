package uib.teamdank.cargame.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import uib.teamdank.cargame.CarGame;
import uib.teamdank.cargame.Player;
import uib.teamdank.common.Game;
import uib.teamdank.common.gui.MenuScreen;
import uib.teamdank.common.util.WeatherData;
import uib.teamdank.common.util.WeatherData.WeatherType;
import uib.teamdank.common.gui.CreditScreen;

public class StartMenuScreen extends MenuScreen implements uib.teamdank.common.gui.StartMenuScreen {
	private static final String BACKGROUND = "Images/menu_screen.png";
	private static final String PLAY = "Images/Buttons/start.png";
	private static final String HIGHSCORE = "Images/Buttons/cg_highscore.png";
	private static final String SHOP = "Images/Buttons/bs_shop.png";
	private static final String CREDIT = "Images/Buttons/bs_credit.png";
	private static final String EXIT = "Images/Buttons/bs_quit.png";

	private Table menu;
	
	private HighscoreMenuScreen highscoreMenuScreen;
	private CreditScreen creditScreen;
	private Game game;
	private ShopScreen shopScreen;
	private Player player;
	private WeatherData wData;

	public StartMenuScreen(CarGame game) {
		super();
		this.game = game;
		highscoreMenuScreen = new HighscoreMenuScreen(game);
		creditScreen = new CreditScreen(game, "Images/Buttons/bs_back.png", "Data/credit_crasher.txt");
		shopScreen = new ShopScreen(game);
		menu = new Table();
		
		// Background Image
		setupBackground();
		
		// Weather Data
		wData = game.getWeatherData();

		createButtons();

		// Player initialization
		player = game.getPlayer();
		player.setWeatherType(getWeather());
		player.getTexture();
		player.setScale(.5f);

		menu.setFillParent(true);
		getStage().addActor(menu);
	}

	private void createButtons() {
		Array<Button> buttons = new Array<>();
		
		buttons.add(createButton(PLAY, this::newGame));
		buttons.add(createButton(HIGHSCORE, this::viewHighscores));
		buttons.add(createButton(SHOP, this::viewShop));
		buttons.add(createButton(CREDIT, this::viewCredit));
		buttons.add(createButton(EXIT, this::exitGame));
		
		menu.pad(450, 1100, 0, 0);
		menu.row();
		for (Button but : buttons) {
			menu.add(but).width((float) (but.getWidth() / 4)).height((float) (but.getHeight() / 4)).pad(5);
			menu.row();
		}
	}
	
	private void setupBackground() {
		Texture backgroundTexture = new Texture(BACKGROUND);
		Image backgroundImage = new Image(backgroundTexture);
		getStage().addActor(backgroundImage);
	}

	@Override
	public void exitGame() {
		Gdx.app.exit();
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
	
	public Player getPlayer(){
		return player;
	}
	
	public WeatherType getWeather() {
		return wData.pullWeather("Norway", "Hordaland", "Bergen", "Bergen");
	}
}