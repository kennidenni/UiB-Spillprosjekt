package uib.teamdank.cargame.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import uib.teamdank.common.GameObject;
import uib.teamdank.common.util.Generator;
import uib.teamdank.common.util.TextureAtlas;
import uib.teamdank.common.util.WeatherData.WeatherType;

public class WeatherGenerator implements Generator<GameObject> {
	private final List<Generator<GameObject>> entityGenerators = new ArrayList<>();
	
	private WeatherType wType;
	public WeatherGenerator(WeatherType wType) {
		this.wType = wType;

//		wType = WeatherType.SNOW;
		if(wType == WeatherType.CLOUD) {
			TextureAtlas atlas = TextureAtlas.createFromJson(Gdx.files.internal("Images/cloud_sheet.json"));
			this.entityGenerators.add(rnd -> {
				GameObject wObj = new GameObject(atlas.getRegion("cloud_1"));
				wObj.getVelocity().set(-10, -20);
				return wObj;
			});
			this.entityGenerators.add(rnd -> {
				GameObject wObj = new GameObject(atlas.getRegion("cloud_2"));
				wObj.getVelocity().set(-10, -20);
				return wObj;
			});
			this.entityGenerators.add(rnd -> {
				GameObject wObj = new GameObject(atlas.getRegion("cloud_3"));
				wObj.getVelocity().set(-10, -20);
				return wObj;
			});
		}else if (wType == WeatherType.SNOW)
			this.entityGenerators.add(rnd -> {
				GameObject wObj = new GameObject(new TextureRegion(new Texture(Gdx.files.internal("Images/snow.png"))));
				wObj.getVelocity().set(-10, -20);
				return wObj;
			});
		else if (wType == WeatherType.RAIN)
			this.entityGenerators.add(rnd -> {
				GameObject wObj = new GameObject(new TextureRegion(new Texture(Gdx.files.internal("Images/rain.png"))));
				wObj.getVelocity().set(-20, -40);
				return wObj;
			});
		else
			this.entityGenerators.add(rnd -> new GameObject(new TextureRegion(new Texture(Gdx.files.internal("Images/empty.png")))));
	}
	
	@Override
	public GameObject generate(Random random) {
		int index = random.nextInt(entityGenerators.size());
		return entityGenerators.get(index).generate(random);
	}

}
