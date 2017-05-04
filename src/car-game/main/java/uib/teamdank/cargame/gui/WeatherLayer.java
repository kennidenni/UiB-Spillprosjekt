package uib.teamdank.cargame.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import uib.teamdank.cargame.util.LoopingBackground;
import uib.teamdank.common.gui.Layer;
import uib.teamdank.common.util.WeatherData.WeatherType;

public class WeatherLayer extends Layer {
	private static final String CLOUDS = "Images/clouds.png";
	private static final String SNOW = "Images/snow.png";
	private static final String RAIN = "Images/rain.png";
	private static final String EMPTY = "Images/empty.png";

	private final OrthographicCamera playerCamera;
	private final OrthographicCamera screenCamera;

	private final WeatherType wType;

	private final Texture wTexture;
	private final LoopingBackground scrollingWeather;

	public WeatherLayer(OrthographicCamera playerCamera, OrthographicCamera screenCamera, WeatherType wType) {
		super(false);

		this.playerCamera = playerCamera;
		this.screenCamera = screenCamera;

		this.wType = wType;
		this.wTexture = setWeatherTexture();

		scrollingWeather = new LoopingBackground(playerCamera, wTexture, 1);
		scrollingWeather.updateHorizontalPosition(-wTexture.getWidth() / 2);
	}

	private Texture setWeatherTexture() {
		if (wType == WeatherType.CLOUD)
			return new Texture(Gdx.files.internal(EMPTY));
		else if (wType == WeatherType.RAIN)
			return new Texture(Gdx.files.internal(RAIN));
		else if (wType == WeatherType.SUN)
			return new Texture(Gdx.files.internal(EMPTY));
		else
			return new Texture(Gdx.files.internal(SNOW));
	}

	@Override
	public void preRender(SpriteBatch batch, float delta) {

		batch.setProjectionMatrix(screenCamera.combined);
		batch.setProjectionMatrix(playerCamera.combined);
		scrollingWeather.render(batch);
	}

}
