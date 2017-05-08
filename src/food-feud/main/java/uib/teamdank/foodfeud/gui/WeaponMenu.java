package uib.teamdank.foodfeud.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;

import uib.teamdank.common.Game;
import uib.teamdank.foodfeud.FoodFeud;

public class WeaponMenu implements Screen{
    private ImageButton menuButton;
	private Texture myTexture;
	private Table menu;
	private Stage stage;
	private Container<ImageButton> buttonCont;
	
	private FoodFeud game;
	private Table weapons;

	private Array<Button> buttons;
	private static final String ANANAS = "Weapons/Ananas.png";
    private static final String CARROT = "Weapons/Carrot.png";
    private static final String CHEESE = "Weapons/Cheese.png";
    private static final String DONUT = "Weapons/donut.png";
	
    private ImageButton weaponMenuButton;
	private ImageButton ananas; 
	private ImageButton carrot;
	private ImageButton cheese;
	private ImageButton donut;
	
    private static final String MENU = "Images/Buttons/ff_menu.png";


	public WeaponMenu(FoodFeud game) {
		this.game = game;
		stage = new Stage(new FitViewport(1920, 1080));
		
        buttons = new Array<Button>();
		menu = new Table(); 
		weapons = new Table();
		weaponMenuButton = setupButton(MENU);
		menu.add(weaponMenuButton).height((float) (weaponMenuButton.getHeight() /4)).pad(980, 1670, 0, 0);
        addButtonListener();
		
		ananas = setupButton(ANANAS);
        carrot = setupButton(CARROT);
        cheese = setupButton(CHEESE);
        donut = setupButton(DONUT);
        
        addToWeapons();
        
        weapons.setFillParent(true);
        weapons.debug();
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

        weapons.row();
    }


	 public ImageButton setupButton(String imageString) {
	        myTexture = new Texture(Gdx.files.internal(imageString));
	        TextureRegion myTextureRegion = new TextureRegion(myTexture);
	        TextureRegionDrawable myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
	        ImageButton logo = new ImageButton(myTexRegionDrawable);
	        return logo;
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
	                	if(weapons.isVisible()) 
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
		Gdx.input.setInputProcessor(null);
	}


	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
