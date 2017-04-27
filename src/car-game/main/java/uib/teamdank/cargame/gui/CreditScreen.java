package uib.teamdank.cargame.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;

import uib.teamdank.cargame.CarGame;

public class CreditScreen implements uib.teamdank.common.gui.CreditScreen {
	private static final String BACK = "Images/Buttons/bs_back.png";
	
	private Stage stage;
	private ImageButton backButton;
	private Table menu;
	private CarGame game;
	private TextButtonStyle textButtonStyle;
	private BitmapFont font;
	private SpriteBatch batch;
	private String credit; 
	
	private float creditsY = 0;

	public CreditScreen(CarGame game) {
		this.game = game;
		stage = new Stage(new FitViewport(1920, 1080));
		
		backButton = setupButton(BACK);
			
		textButtonStyle = new TextButtonStyle();
		textButtonStyle.font = font;
		

		menu = new Table();
		menu.row();
		menu.add(backButton).width((float) (backButton.getWidth() / 4)).height((float) (backButton.getHeight() / 4)).pad(950, 0, 0, 1630);

		menu.setFillParent(true);
		stage.addActor(menu);
		Gdx.input.setInputProcessor(stage);
		
		backButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Stage myStage = event.getTarget().getStage();
				Vector2 mouse = myStage.screenToStageCoordinates(new Vector2(Gdx.input.getX(), Gdx.input.getY()));

				if (myStage.hit(mouse.x, mouse.y, true) == event.getTarget()) {
					game.setScreen(game.getStartMenuScreen());
				}
			}
		});
	}
	
	public ImageButton setupButton(String imageString) {
		Texture myTexture = new Texture(Gdx.files.internal(imageString));
		TextureRegion myTextureRegion = new TextureRegion(myTexture);
		TextureRegionDrawable myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
		return new ImageButton(myTexRegionDrawable);
	}
	
	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
		
		String[] lines = Gdx.files.internal("Data/credit_crasher.txt").readString().split("\\r?\\n");
		
		batch = new SpriteBatch();
		font = new BitmapFont();
		font.getData().setScale(3);
		
		StringBuilder strb = new StringBuilder();
		
		credit = "";		
		for (String text : lines) {
			strb.append(text + "\n");
		}
		
		credit = strb.toString();
	}	

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(delta);
		stage.draw();
		
		final int width = Gdx.graphics.getWidth();
		batch.begin(); 
		font.draw(batch, credit, 0, creditsY, width, Align.center, true);
		batch.end();
		creditsY += delta * 50;
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		Gdx.input.setInputProcessor(null);
		creditsY = 0;
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
	}

	@Override
	public void goBack() {
		game.setScreen(game.getStartMenuScreen());
	}

}
