/*******************************************************************************
 * Copyright (C) 2017  TeamDank
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/
package uib.teamdank.foodfeud.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;

import uib.teamdank.common.util.AssetManager;
import uib.teamdank.common.util.TextureAtlas;
import uib.teamdank.foodfeud.FoodFeud;

public class FoodHud {

	private Stage stage;
	private AssetManager assets;
	private ImageButton muteButton;

	private BitmapFont font;
	private TextButtonStyle textButtonStyle;
	private TextButton time;

	private Table scoreTable;
	private Table muteTable;

	private FoodFeud game;

	private boolean muted = false;
	
    private static final String MENU = "Images/Buttons/ff_menu.png";
    private Table weaponTable;
	private ImageButton weaponMenuButton;
	private WeaponMenu weaponMenu;
	
	public FoodHud() {
		stage = new Stage(new FitViewport(1920, 1080));
		
		this.assets = new AssetManager();
		weaponMenu = new WeaponMenu();
		setUpWeaponMenu();
		setUpMute();

		font = new BitmapFont();
		textButtonStyle = new TextButtonStyle();
		textButtonStyle.font = font;
		
		time = new TextButton("0", textButtonStyle);
		time.getLabel().setFontScale(10, 10);
		
		scoreTable = new Table();
		scoreTable.add(time).width(300).pad(900, 0, 0, 1600);
	
		scoreTable.setFillParent(true);
		
		stage.addActor(weaponTable);
		
		stage.addActor(scoreTable);
		stage.addActor(muteTable);
	}

	public boolean isMuted() {
		return muted;
	}

	public void setAsInputProcessor() {
		Gdx.input.setInputProcessor(stage);
	}

	private void setUpWeaponMenu() {
		weaponTable = new Table();      
        weaponMenuButton = setupButton(MENU);
		weaponTable.add(weaponMenuButton).height((float) (weaponMenuButton.getHeight() /4)).pad(200, 3600, 300, 0);
		
		weaponMenuButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            	return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                Stage stage = event.getTarget().getStage();
                Vector2 mouse = stage.screenToStageCoordinates(new Vector2(Gdx.input.getX(), Gdx.input.getY()));

                if (stage.hit(mouse.x, mouse.y, true) == event.getTarget()) {
                    viewWeaponMenu();
                }
            }
        });
	}
	
	 public void viewWeaponMenu() {
	        game.setScreen(weaponMenu);
	    }
	
	private void setUpMute() {
		TextureAtlas muteTextures = assets.getAtlas("Images/mute.json");
		muteButton = new ImageButton(
				new TextureRegionDrawable(muteTextures.getRegion("unmuted")),
				new TextureRegionDrawable(muteTextures.getRegion("unmuted")),
				new TextureRegionDrawable(muteTextures.getRegion("muted"))
		);
		muteTable = new Table();
		muteTable.add(muteButton).width((float) (muteButton.getWidth() / 2.5))
				.height((float) (muteButton.getHeight() / 2.5)).pad(0, 200, 2000, 0);

		muteButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Stage stage = event.getTarget().getStage();
				Vector2 mouse = stage.screenToStageCoordinates(new Vector2(Gdx.input.getX(), Gdx.input.getY()));

				if (stage.hit(mouse.x, mouse.y, true) == event.getTarget()) {
					toggleMute();
				}
			}
		});
	}

	private void toggleMute() {
		if (muted) {
			muted = false;
			game.setAudio(muted);
			muteButton.setChecked(false);
		} else {
			muted = true;
			game.setAudio(muted);
			muteButton.setChecked(true);
		}
	}

	public void setGame(FoodFeud game) {
		this.game = game;
	}

	public void setMute(boolean isMuted) {
		muted = isMuted;
		muteButton.setChecked(isMuted);
	}

	public void render(float delta) {
		time.act(delta);
		stage.act(delta);
		stage.draw();
	}

	public void setTime(long l) {
		time.setText(String.valueOf(l));
	}

	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
	}
	
	public ImageButton setupButton(String imageString) {
        Texture myTexture = new Texture(Gdx.files.internal(imageString));
        TextureRegion myTextureRegion = new TextureRegion(myTexture);
        TextureRegionDrawable myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        return new ImageButton(myTexRegionDrawable);
    }

}