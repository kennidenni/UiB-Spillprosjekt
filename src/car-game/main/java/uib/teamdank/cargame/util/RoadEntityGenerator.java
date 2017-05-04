package uib.teamdank.cargame.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import uib.teamdank.cargame.Coin;
import uib.teamdank.cargame.Fuel;
import uib.teamdank.cargame.Hole;
import uib.teamdank.cargame.Puddle;
import uib.teamdank.common.GameObject;
import uib.teamdank.common.util.AudioManager;
import uib.teamdank.common.util.Generator;
import uib.teamdank.common.util.TextureAtlas;
import uib.teamdank.common.util.WeatherData.WeatherType;

/**
 * Generates {@link GameObject}s of the correct road entity type. This class
 * does not interact with the game directly—its purely functional. 
 */
public class RoadEntityGenerator implements Generator<GameObject> {
	private final List<Generator<GameObject>> entityGenerators = new ArrayList<>();

	public RoadEntityGenerator(AudioManager audio, TextureAtlas roadEntityAtlas, WeatherType wType) {
		this.entityGenerators.add(rnd -> new Fuel(audio, roadEntityAtlas));
		this.entityGenerators.add(rnd -> new Fuel(audio, roadEntityAtlas));
		this.entityGenerators.add(rnd -> new Hole(roadEntityAtlas));
		this.entityGenerators.add(rnd -> new Puddle(audio, roadEntityAtlas));
		this.entityGenerators.add(rnd -> new Coin(audio, roadEntityAtlas));
		
		if(wType == WeatherType.RAIN) {
			this.entityGenerators.add(rnd -> new Puddle(audio, roadEntityAtlas));
			this.entityGenerators.add(rnd -> new Puddle(audio, roadEntityAtlas));
		}
	}

	@Override
	public GameObject generate(Random random) {
		final int index = random.nextInt(entityGenerators.size());
		return entityGenerators.get(index).generate(random);
	}
}
