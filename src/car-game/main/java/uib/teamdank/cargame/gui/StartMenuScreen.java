package uib.teamdank.cargame.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import uib.teamdank.cargame.CarGame;


/**
 * har lagt inn hele dette objectet som GestureListener, dette er nok feil da det er Stage som skal vÃ¦re GestureListener.
 * Dette mÃ¥ endres slik at TextButton kan fÃ¥ tak i touchDown Event og agere pÃ¥ denne.
**/

public class StartMenuScreen implements uib.teamdank.common.gui.StartMenuScreen, GestureListener {
	
	private Stage stage;
	private CarGame game;
	private Array<Button> buttons;
	private ImageButton logo;
	private ImageButton play;
	private ImageButton highscore;
	private ImageButton settings;
	private ImageButton quit;
	private Texture myTexture;
    private Table menu;
    
    private static final String LOGO = "Images/CarGameLogo.png";
    private static final String PLAY = "Image/playButton";
    private static final String HIGHSCORE = "Images/CarGameLogo.png";
    private static final String SETTINGS = "Images/CarGameLogo.png";
    private static final String EXIT = "Images/CarGameLogo.png";
    
    private InputListener touchTest = new InputListener(){
	    @Override
	    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
			System.out.println("TOUCH DOWN");
			return true;
	    }
	    
	    @Override
	    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
	        Stage stage = event.getTarget().getStage();
	        Vector2 mouse = stage.screenToStageCoordinates( new Vector2(Gdx.input.getX(), Gdx.input.getY()) );

	        if(stage.hit(mouse.x, mouse.y, true) == event.getTarget()) {
	            System.out.println("TOUCH UP");
	        }
	    }
		};
	public StartMenuScreen(CarGame game){
		this.game = game;
		stage = new Stage(new FitViewport(1280, 720));

		
		logo = findButton(LOGO);
		play = findButton(PLAY);
		highscore = findButton(HIGHSCORE);
		settings = findButton(SETTINGS);
		quit = findButton(EXIT);
		
		buttons = new Array<Button>();

		buttons.add(logo);
		buttons.add(play);
		buttons.add(highscore);
		buttons.add(settings);
		buttons.add(quit);
	
		
		menu = new Table();
		menu.add(logo).width(500);
		menu.row();
		
		for(Button button : buttons){
			button.addListener(touchTest);
			menu.add(button).width(100);
			menu.row();
		}
		
		menu.setFillParent(true);
		stage.addActor(menu);
		Gdx.input.setInputProcessor(stage);
		
	}
	
	public ImageButton findButton (String imageString) {
		myTexture = new Texture(Gdx.files.internal(imageString));
		TextureRegion myTextureRegion = new TextureRegion(myTexture);
		TextureRegionDrawable myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
		ImageButton logo = new ImageButton(myTexRegionDrawable); //Set the button up
		return logo;
	}
	
	@Override
	public void dispose() {
		stage.dispose();
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
	public void newGame() { // TODO 
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
		// TODO Auto-generated method stub
	}

	
	@Override public boolean tap(float x, float y, int count, int button) {return false;}

	@Override public boolean longPress(float x, float y){ return false;}

	@Override public boolean fling(float velocityX, float velocityY, int button) {return false;}

	@Override public boolean pan(float x, float y, float deltaX, float deltaY) {return false;}

	@Override public boolean panStop(float x, float y, int pointer, int button) {return false;}

	@Override public boolean zoom(float initialDistance, float distance) {return false;}

	@Override public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {return false;}

	@Override public void pinchStop() {
		// TODO
	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

}