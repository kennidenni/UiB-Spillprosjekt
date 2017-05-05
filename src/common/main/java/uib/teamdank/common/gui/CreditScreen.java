package uib.teamdank.common.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;

import uib.teamdank.common.Game;

public class CreditScreen implements Screen {
		
		private Stage stage;
		private ImageButton backButton;
		private Game game;
		private String creditFile;
		private Container<ImageButton> buttonCont;
		private VerticalGroup creditGroup;

		public CreditScreen(Game game, String buttonFile, String fileWithCredit) {
			this.game = game;
			stage = new Stage(new FitViewport(1920, 1080));
			this.creditFile = fileWithCredit;
						
			backButton = setupButton(buttonFile);
			buttonCont = new Container<>(backButton);
			buttonCont.width(backButton.getWidth() / 4).height(backButton.getHeight() / 4)
				.align(Align.bottomLeft)
				.pad(0, Gdx.graphics.getWidth() / 16, Gdx.graphics.getHeight() / 16, 0);

			stage.addActor(buttonCont);
					
			creditGroup = new VerticalGroup();
			creditGroup.setWidth(Gdx.graphics.getWidth() - buttonCont.getMaxWidth());
			creditGroup.align(Align.center);

			stage.addActor(creditGroup);
			
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
		
		public ImageButton setupButton(String imgPath) {
			return new ImageButton(
					new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal(imgPath)))));
		}
		
		@Override
		public void show() {
			Gdx.input.setInputProcessor(stage);
			
			String[] lines = Gdx.files.internal(creditFile).readString().split("\\r?\\n");
			
			BitmapFont font = new BitmapFont();
			font.getData().setScale(4);
			
			for (String line : lines) {
				Label l = new Label(line, new LabelStyle(font, Color.WHITE));
				l.setAlignment(Align.left);
				l.setWrap(true);
				l.setWidth(creditGroup.getWidth());
				
				creditGroup.addActor(new Container<Label>(l).width(creditGroup.getWidth()));
			}
			
			creditGroup.setPosition(2 * buttonCont.getMaxWidth(), -font.getCapHeight() * lines.length);
		}	

		@Override
		public void render(float delta) {
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			stage.act(delta);
			stage.draw();
			
			creditGroup.moveBy(0, delta * 50);
		}

		@Override
		public void resize(int width, int height) {
			stage.getViewport().update(width, height, true);
		}

		@Override
		public void pause() {
			//TODO
		}

		@Override
		public void resume() {
			//TODO
		}

		@Override
		public void hide() {
			Gdx.input.setInputProcessor(null);
			creditGroup.clear();
		}

		@Override
		public void dispose() {
			stage.dispose();			
		}

		public void goBack() {
			game.setScreen(game.getStartMenuScreen());
		}

	}


