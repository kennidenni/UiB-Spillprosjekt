package uib.teamdank.cargame.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import uib.teamdank.cargame.CarGame;
import uib.teamdank.common.Game;


/**
 * har lagt inn hele dette objectet som GestureListener, dette er nok feil da det er Stage som skal vÃ¦re GestureListener.
 * Dette mÃ¥ endres slik at TextButton kan fÃ¥ tak i touchDown Event og agere pÃ¥ denne.
**/

public class StartMenuScreen implements uib.teamdank.common.gui.StartMenuScreen, GestureListener {
	
	private Stage stage;
	private Texture myTexture;
    private Table menu;
    private ImageButton logo;
    private ImageButton play;
    private ImageButton highscore;
    private ImageButton settings;
    private ImageButton exit;
    private Game game;
    
    private static final String LOGO = "Images/CarGameLogo.png";
    private static final String PLAY = "Images/Buttons/start.png";
    private static final String HIGHSCORE = "Images/Buttons/cg_highscore.png";
    private static final String SETTINGS = "Images/Buttons/bs_menu.png";
    private static final String EXIT = "Images/Buttons/bs_quit.png";
    
	private Array<Button> buttons = new Array<Button>();
	private HighscoreMenuScreen highscoreMenuScreen;

	
	public StartMenuScreen(CarGame game){
		this.game = game;
		stage = new Stage(new FitViewport(1920, 1080));
		highscoreMenuScreen = new HighscoreMenuScreen();

		logo = setupButton(LOGO);
		play = setupButton(PLAY);
		highscore = setupButton(HIGHSCORE);
		settings = setupButton(SETTINGS);
		exit = setupButton(EXIT);
	
		menu = new Table();
		
		buttons.add(play);
		buttons.add(highscore);
		buttons.add(settings);
		buttons.add(exit);
		
		menu.add(logo).pad(0, 0, 20, 0);
		menu.row();
		menu.pad(50);
		for(Button button : buttons){
			menu.add(button).width((float) (button.getWidth()/5)).height((float) (button.getHeight()/5)).pad(5);
			menu.row();
		}
		
		menu.setFillParent(true);
		stage.addActor(menu);
		Gdx.input.setInputProcessor(stage);
		
		play.addListener(new InputListener(){
		    @Override
		    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
		    }
		    
		    @Override
		    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
		        Stage stage = event.getTarget().getStage();
		        Vector2 mouse = stage.screenToStageCoordinates( new Vector2(Gdx.input.getX(), Gdx.input.getY()) );

		        if(stage.hit(mouse.x, mouse.y, true) == event.getTarget()) {
		            hide();
		            game.setScreen(game.getGameScreen());
		        }
		    }
		});
		
		highscore.addListener(new InputListener(){
		    @Override
		    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
		    }
		    
		    @Override
		    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
		        Stage stage = event.getTarget().getStage();
		        Vector2 mouse = stage.screenToStageCoordinates( new Vector2(Gdx.input.getX(), Gdx.input.getY()) );

		        if(stage.hit(mouse.x, mouse.y, true) == event.getTarget()) {
		            hide();
		            game.setScreen(highscoreMenuScreen);
		        }
		    }
		});
		
		settings.addListener(new InputListener(){
		    @Override
		    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
		    }
		    
		    @Override
		    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
		        Stage stage = event.getTarget().getStage();
		        Vector2 mouse = stage.screenToStageCoordinates( new Vector2(Gdx.input.getX(), Gdx.input.getY()) );

		        if(stage.hit(mouse.x, mouse.y, true) == event.getTarget()) {
		            System.out.println("TOUCH UP");
		            //hide();
		            //game.setScreen(game.get());
		        }
		    }
		});
		
		exit.addListener(new InputListener(){
		    @Override
		    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				System.out.println("TOUCH DOWN");
				System.out.println(button);
				return true;
		    }
		    
		    @Override
		    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
		        Stage stage = event.getTarget().getStage();
		        Vector2 mouse = stage.screenToStageCoordinates( new Vector2(Gdx.input.getX(), Gdx.input.getY()) );

		        if(stage.hit(mouse.x, mouse.y, true) == event.getTarget()) {
		            System.out.println("TOUCH UP");
		            hide();
		            exitGame();
		        }
		    }
		});
		
		
		
	}
	
	public ImageButton setupButton (String imageString) {
		myTexture = new Texture(Gdx.files.internal(imageString));
		TextureRegion myTextureRegion = new TextureRegion(myTexture);
		TextureRegionDrawable myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
		ImageButton logo = new ImageButton(myTexRegionDrawable); //Set the button up
		return logo;
	}
	
	@Override
	public void dispose() {
		//screen.dispose();
	}
	
	@Override
	public void exitGame() { 
		// TODO
	}

	@Override
	public void hide() { 
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public void newGame() { 
		// TODO 
	}

	@Override
	public void pause() { // TODO
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(delta);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
	}

	@Override
	public void show() {
		//TODO 
	}

	@Override
	public void viewHighscores() {
		//TODO
	}

	
	
	@Override public boolean tap(float x, float y, int count, int button) {return false;}

	@Override public boolean longPress(float x, float y){ return false;}

	@Override public boolean fling(float velocityX, float velocityY, int button) {return false;}

	@Override public boolean pan(float x, float y, float deltaX, float deltaY) {return false;}

	@Override public boolean panStop(float x, float y, int pointer, int button) {return false;}

	@Override public boolean zoom(float initialDistance, float distance) {return false;}

	@Override public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {return false;}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) { return false; }

	@Override public void pinchStop() { // TODO 
	}
	
	
}