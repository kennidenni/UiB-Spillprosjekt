package uib.teamdank.common.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.utils.Align;
import uib.teamdank.common.Game;

public class CreditScreen extends MenuScreen {

		private ImageButton backButton;
		private Game game;
		private String creditFile;
		private Container<ImageButton> buttonCont;
		private VerticalGroup creditGroup;

		public CreditScreen(Game game, String buttonFile, String fileWithCredit) {
			super();
			this.game = game;
			this.creditFile = fileWithCredit;
						
			backButton = createButton(buttonFile, () -> goBack());

			buttonCont = new Container<>(backButton);
			buttonCont.width(backButton.getWidth() / 4).height(backButton.getHeight() / 4)
				.align(Align.bottomLeft)
				.pad(0, Gdx.graphics.getWidth() / 16, Gdx.graphics.getHeight() / 16, 0);

			getStage().addActor(buttonCont);
					
			creditGroup = new VerticalGroup();
			creditGroup.setWidth(Gdx.graphics.getWidth() - buttonCont.getMaxWidth());
			creditGroup.align(Align.center);

			getStage().addActor(creditGroup);
		}
		
		@Override
		public void show() {
			super.show();
			
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
			super.render(delta);
			
			creditGroup.moveBy(0, delta * 50);
		}

		@Override
		public void hide() {
			super.hide();
			creditGroup.clear();
		}

		@Override
		public void dispose() {
			getStage().dispose();
			super.dispose();
		}

		public void goBack() {
			game.setScreen(game.getStartMenuScreen());
		}

	}


