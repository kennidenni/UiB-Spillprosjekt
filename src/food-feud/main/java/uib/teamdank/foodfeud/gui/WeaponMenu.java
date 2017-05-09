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
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;

import uib.teamdank.foodfeud.FoodFeud;

public class WeaponMenu extends ScreenAdapter {
	private FoodFeud game;
	private Texture myTexture;
	private Table menu;
	private Stage stage;
	private Table weapons;

	private static final String ANANAS_PATH = "Weapons/Ananas.png";
	private static final String CARROT_PATH = "Weapons/Carrot.png";
	private static final String CHEESE_PATH = "Weapons/Cheese.png";
	private static final String DONUT_PATH = "Weapons/donut.png";

	private ImageButton weaponMenuButton;
	private ImageButton ananas;
	private ImageButton carrot;
	private ImageButton cheese;
	private ImageButton donut;

	private static final String MENU_PATH = "Images/Buttons/ff_menu.png";

	public WeaponMenu() {
		stage = new Stage(new FitViewport(1920, 1080));

		menu = new Table();
		weapons = new Table();
		weaponMenuButton = setupButton(MENU_PATH);
		menu.add(weaponMenuButton).height((float) (weaponMenuButton.getHeight() / 4)).pad(980, 1670, 0, 0);
		addButtonListener();

		ananas = setupButton(ANANAS_PATH);
		carrot = setupButton(CARROT_PATH);
		cheese = setupButton(CHEESE_PATH);
		donut = setupButton(DONUT_PATH);

		addToWeapons();

		weapons.setFillParent(true);
		weapons.setVisible(false);
		stage.addActor(weapons);

		menu.setFillParent(true);
		stage.addActor(menu);
		Gdx.input.setInputProcessor(stage);

	}

	private void addToWeapons() {
		weapons.add(ananas).width((float) (ananas.getWidth() / 1.3)).height((float) (ananas.getHeight() / 1.3)).pad(5);
		weapons.add(carrot).width((float) (carrot.getWidth() / 1.3)).height((float) (carrot.getHeight() / 1.3)).pad(5);
		weapons.add(cheese).width((float) (cheese.getWidth() / 1.3)).height((float) (cheese.getHeight() / 1.3)).pad(5);
		weapons.add(donut).width((float) (donut.getWidth() / 1.3)).height((float) (donut.getHeight() / 1.3)).pad(5);
		weapons.debug();
		weapons.pad(900, 0, 5, 0);
		weapons.row();
	}

	public ImageButton setupButton(String imageString) {
		myTexture = new Texture(Gdx.files.internal(imageString));
		TextureRegion myTextureRegion = new TextureRegion(myTexture);
		TextureRegionDrawable myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
		return new ImageButton(myTexRegionDrawable);
	}

	private void addButtonListener() {

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
					if (weapons.isVisible())
						weapons.setVisible(false);
					else
						weapons.setVisible(true);
				}
			}
		});
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void render(float delta) {
		stage.act(delta);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void hide() {
		Gdx.input.setInputProcessor(null);
	}

	public void setAsInputProcessor() {
		Gdx.input.setInputProcessor(stage);
	}

	public void setGame(FoodFeud game) {
		this.game = game;
	}
}
