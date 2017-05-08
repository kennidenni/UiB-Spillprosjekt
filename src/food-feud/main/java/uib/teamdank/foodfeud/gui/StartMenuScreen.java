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

public class StartMenuScreen implements uib.teamdank.common.gui.StartMenuScreen {


    private static final String LOGO = "Images/logo.png";
    private static final String PLAY = "Images/Buttons/ff_start.png";
    private static final String CREDIT = "Images/Buttons/ff_credit.png";
    private static final String EXIT = "Images/Buttons/ff_quit.png";

	private Stage stages;
    private Texture myTexture;
    private Table menu;
    private ImageButton logoButton;
    private ImageButton playButton;
    private ImageButton creditButton;
    private ImageButton exitButton;
    private Array<Button> buttons;
    private CreditScreen creditScreen;
    private Game game;

    
    /**/
    private static final String MENU = "Images/Buttons/ff_menu.png";
    private Table weaponTable;
	private ImageButton weaponMenuButton;
	private WeaponMenu weaponMenu;
    /**/
    
    
    public StartMenuScreen(FoodFeud game){
        this.game = game;
        stages = new Stage(new FitViewport(1920, 1080));
        creditScreen = new CreditScreen(game, "Images/Buttons/ff_back.png", "Data/credit_foodfeud.txt");
        weaponMenu = new WeaponMenu();
        buttons = new Array<Button>();
        menu = new Table();
        
        
        logoButton = setupButton(LOGO);
        playButton = setupButton(PLAY);
        creditButton = setupButton(CREDIT);
        exitButton = setupButton(EXIT);
        weaponMenuButton = setupButton(MENU);

        setUpWeaponMenu();
        addButtonListener();
        addToTables();

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
        buttons.add(creditButton);
        buttons.add(exitButton);
        buttons.add(weaponMenuButton);

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
        throw new UnsupportedOperationException("no highscores in this game");
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
    
    private void setUpWeaponMenu() {
		weaponMenuButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("hei");
            	return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                Stage stage = event.getTarget().getStage();
                Vector2 mouse = stage.screenToStageCoordinates(new Vector2(Gdx.input.getX(), Gdx.input.getY()));

                if (stage.hit(mouse.x, mouse.y, true) == event.getTarget()) {
                	game.setScreen(weaponMenu);
                }
            }
        });
	}

}