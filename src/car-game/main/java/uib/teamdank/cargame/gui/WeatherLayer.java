package uib.teamdank.cargame.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import uib.teamdank.cargame.util.LoopingBackground;
import uib.teamdank.common.gui.Layer;
import uib.teamdank.common.util.WeatherData;
import uib.teamdank.common.util.WeatherData.WeatherType;

public class WeatherLayer extends Layer {
	private static final String CLOUDS = "Images/clouds.png";
	private static final String SNOW = "Images/snow.png";
	private static final String RAIN = "Images/rain.png";
	private static final String EMPTY = "Images/empty.png";

	private final OrthographicCamera playerCamera;
	private final OrthographicCamera screenCamera;

	private final WeatherData wData;
	private final WeatherType wType;

	private final Texture wTexture;
	private final LoopingBackground scrollingWeather;

	public WeatherLayer(OrthographicCamera playerCamera, OrthographicCamera screenCamera) {
		super(false);

		this.playerCamera = playerCamera;
		this.screenCamera = screenCamera;

		wData = new WeatherData();
		wType = wData.pullWeather("Norway", "Hordaland", "Bergen", "Bergen");

		if (wType == WeatherType.CLOUD)
			wTexture = new Texture(Gdx.files.internal(CLOUDS));
		else if (wType == WeatherType.RAIN)
			wTexture = new Texture(Gdx.files.internal(RAIN));
		else if (wType == WeatherType.SUN)
			wTexture = new Texture(Gdx.files.internal(CLOUDS));
		else
			wTexture = new Texture(Gdx.files.internal(SNOW));

		scrollingWeather = new LoopingBackground(playerCamera, wTexture, 1);
		scrollingWeather.updateHorizontalPosition(-wTexture.getWidth()/2);
	}

	@Override
	protected void preRender(SpriteBatch batch, float delta) {

		batch.setProjectionMatrix(screenCamera.combined);
		batch.setProjectionMatrix(playerCamera.combined);
		scrollingWeather.render(batch);
	}

}
