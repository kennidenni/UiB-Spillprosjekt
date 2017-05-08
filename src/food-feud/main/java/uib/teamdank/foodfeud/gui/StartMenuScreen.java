package uib.teamdank.foodfeud.gui;

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

import uib.teamdank.common.Game;
import uib.teamdank.common.gui.CreditScreen;
import uib.teamdank.common.gui.MenuScreen;
import uib.teamdank.foodfeud.FoodFeud;

public class StartMenuScreen extends MenuScreen implements uib.teamdank.common.gui.StartMenuScreen {
    private static final String LOGO = "Images/logo.png";
    private static final String PLAY = "Images/Buttons/ff_start.png";
    private static final String CREDIT = "Images/Buttons/ff_credit.png";
    private static final String EXIT = "Images/Buttons/ff_quit.png";

    private Table menu;
    private ImageButton logoButton;
    private Array<Button> buttons;
    private CreditScreen creditScreen;
    private Game game;

    public StartMenuScreen(FoodFeud game){
        super();
        this.game = game;
        creditScreen = new CreditScreen(game, "Images/Buttons/ff_back.png", "Data/credit_foodfeud.txt");
        buttons = new Array<>();
        menu = new Table();

        logoButton = createButton(LOGO, null);
        buttons.add(createButton(PLAY, () -> newGame()));
        buttons.add(createButton(CREDIT, () -> viewCredit()));
        buttons.add(createButton(EXIT, () -> exitGame()));

        addToTables();

        menu.setFillParent(true);
        getStage().addActor(menu);
    }

    private void addToTables() {
        menu.add(logoButton).height((float) (logoButton.getHeight() /1.3)).pad(10, 0, 0, 0);
        menu.row();
        for (Button but : buttons) {
            menu.add(but).width((float) (but.getWidth() / 4)).height((float) (but.getHeight() / 4)).pad(5);
            menu.row();
        }
    }

	@Override
	public void exitGame() {
		Gdx.app.exit();
	}

	@Override
	public void newGame() {
		game.setScreen(game.newGame());
	}

	@Override
	public void viewHighscores() {
        throw new UnsupportedOperationException("no highscores in this game");
	}

    public void viewCredit() {
        game.setScreen(creditScreen);
    }
}