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
import uib.teamdank.foodfeud.FoodFeud;
import uib.teamdank.foodfeud.Player;

public class StartMenuScreen implements uib.teamdank.common.gui.StartMenuScreen {


    private static final String LOGO = "Images/FoodFeudLogo.png";
    private static final String PLAY = "Images/Buttons/ff_start.png";
    private static final String HIGHSCORE = "Images/Buttons/ff_highscore.png";
    private static final String CREDIT = "Images/Buttons/ff_go.png";
    private static final String EXIT = "Images/Buttons/ff_quit.png";

	private Stage stages;
    private Texture myTexture;
    private Table menu;
    private ImageButton logoButton;
    private ImageButton playButton;
    private ImageButton highscoreButton;
    private ImageButton creditButton;
    private ImageButton exitButton;
    private Array<Button> buttons;
    private HighscoreMenuScreen highscoreMenuScreen;
    private CreditScreen creditScreen;
    private Game game;
    private Player player;

    public StartMenuScreen(FoodFeud game){
        this.game = game;
        stages = new Stage(new FitViewport(1920, 1080));
        highscoreMenuScreen = new HighscoreMenuScreen(game);
        //creditScreen = new CreditScreen(game);
        buttons = new Array<Button>();
        menu = new Table();

        logoButton = setupButton(LOGO);
        playButton = setupButton(PLAY);
        highscoreButton = setupButton(HIGHSCORE);
        creditButton = setupButton(CREDIT);
        exitButton = setupButton(EXIT);

        addButtonListener();
        addToTables();

        player = new Player();
        player.getTexture();
        player.setScale(.5f);


        menu.setFillParent(true);
        stages.addActor(menu);
        Gdx.input.setInputProcessor(stages);


    }

    // Setting the buttons up
    public ImageButton setupButton(String imageString) {
        myTexture = new Texture(Gdx.files.internal(imageString));
        TextureRegion myTextureRegion = new TextureRegion(myTexture);
        TextureRegionDrawable myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        ImageButton logo = new ImageButton(myTexRegionDrawable);
        return logo;
    }

    private void addToTables() {
        buttons.add(playButton);
        buttons.add(highscoreButton);
        buttons.add(creditButton);
        buttons.add(exitButton);

        menu.add(logoButton).height((float) (logoButton.getHeight() /1.3)).pad(10, 0, 0, 0);
        menu.row();
        for (Button but : buttons) {
            menu.add(but).width((float) (but.getWidth() / 4)).height((float) (but.getHeight() / 4)).pad(5);
            menu.row();
        }
    }
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
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
	public void newGame() {
		game.newGame();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
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
	public void resume() {
		// TODO Auto-generated method stub
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

    private void addButtonListener() {
        playButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                Stage stage = event.getTarget().getStage();
                Vector2 mouse = stage.screenToStageCoordinates(new Vector2(Gdx.input.getX(), Gdx.input.getY()));

                if (stage.hit(mouse.x, mouse.y, true) == event.getTarget()) {
                    game.setScreen(game.newGame());
                }
            }
        });

        highscoreButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                Stage stage = event.getTarget().getStage();
                Vector2 mouse = stage.screenToStageCoordinates(new Vector2(Gdx.input.getX(), Gdx.input.getY()));

                if (stage.hit(mouse.x, mouse.y, true) == event.getTarget()) {
                    viewHighscores();
                }
            }
        });

        exitButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                Stage stage = event.getTarget().getStage();
                Vector2 mouse = stage.screenToStageCoordinates(new Vector2(Gdx.input.getX(), Gdx.input.getY()));

                if (stage.hit(mouse.x, mouse.y, true) == event.getTarget()) {
                    exitGame();
                }
            }
        });

        creditButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                Stage stage = event.getTarget().getStage();
                Vector2 mouse = stage.screenToStageCoordinates(new Vector2(Gdx.input.getX(), Gdx.input.getY()));

                if (stage.hit(mouse.x, mouse.y, true) == event.getTarget()) {
                    viewCredit();
                }
            }
        });
    }

}