package uib.teamdank.foodfeud.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;

import uib.teamdank.common.Game;

public class WeaponMenu implements Screen{
    private ImageButton menuButton;
	private Texture myTexture;
	private Table menu;
	private Game game;
	private Stage stage;
	private Container<ImageButton> buttonCont;


	public WeaponMenu(String filename) {
		stage = new Stage(new FitViewport(1920, 1080));

		menuButton = setupButton(filename);
		buttonCont = new Container<>(menuButton);
		buttonCont.width(menuButton.getWidth() / 4).height(menuButton.getHeight() / 4)
			.align(Align.bottomLeft)
			.pad(0, Gdx.graphics.getWidth() / 16, Gdx.graphics.getHeight() / 16, 0);

		stage.addActor(buttonCont);
		stage.getDebugColor();
	}


	public ImageButton setupButton(String imgPath) {
		return new ImageButton(
				new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal(imgPath)))));
	}


	@Override
	public void show() {
        Gdx.input.setInputProcessor(stage);
		
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
	public void pause() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
