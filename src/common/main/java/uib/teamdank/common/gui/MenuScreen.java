package uib.teamdank.common.gui;

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
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;

public abstract class MenuScreen implements Screen {
    private Stage stage;

    public MenuScreen() {
        stage = new Stage(new FitViewport(1920, 1080));
        Gdx.input.setInputProcessor(stage);
    }

    public void addButtonListener(Button button, Runnable lambda) {
        button.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                Stage stage = event.getTarget().getStage();
                Vector2 mouse = stage.screenToStageCoordinates(new Vector2(Gdx.input.getX(), Gdx.input.getY()));

                if (stage.hit(mouse.x, mouse.y, true) == event.getTarget()) {
                    lambda.run();
                }
            }
        });
    }

    public ImageButton createButton(String imageString, Runnable lambda) {
        ImageButton button = setupButton(imageString);
        if(lambda != null)
            addButtonListener(button, lambda);
        return button;
    }

    @Override
    public void dispose() {
        //TODO
    }

    public Stage getStage() {
        return stage;
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void pause() {
        //TODO
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
        //TODO
    }

    private ImageButton setupButton(String imageString) {
        Texture myTexture = new Texture(Gdx.files.internal(imageString));
        TextureRegion myTextureRegion = new TextureRegion(myTexture);
        TextureRegionDrawable myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        return new ImageButton(myTexRegionDrawable);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }
}
