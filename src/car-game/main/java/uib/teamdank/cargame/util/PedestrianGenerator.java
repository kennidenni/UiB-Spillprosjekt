package uib.teamdank.cargame.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import uib.teamdank.cargame.Pedestrian;
import uib.teamdank.common.GameObject;
import uib.teamdank.common.util.AudioManager;
import uib.teamdank.common.util.Generator;
import uib.teamdank.common.util.TextureAtlas;
import uib.teamdank.common.util.WeatherData.WeatherType;

/**
 * Generates {@link GameObject}s of the correct pedestrian type. This class does
 * not interact with the game directly; its purely functional.
 */
public class PedestrianGenerator implements Generator<GameObject> {
	private final List<Generator<GameObject>> entityGenerators = new ArrayList<>();

	public PedestrianGenerator(AudioManager audio, TextureAtlas atlas, WeatherType wType) {
		this.entityGenerators.add(rnd -> new Pedestrian(audio, 5, 0, 100, rnd.nextBoolean(), atlas.getRegion("cyclist")));
		this.entityGenerators.add(rnd -> new Pedestrian(audio, 10, 100, 0, rnd.nextBoolean(), atlas.getRegion("skateboard")));
		this.entityGenerators.add(rnd -> new Pedestrian(audio, 2, 50, 0, rnd.nextBoolean(), atlas.getRegion("umbrella_blue")));
		this.entityGenerators.add(rnd -> new Pedestrian(audio, 2, 50, 0, rnd.nextBoolean(), atlas.getRegion("umbrella_red")));
		this.entityGenerators.add(rnd -> new Pedestrian(audio, 2, 50, 0, rnd.nextBoolean(), atlas.getRegion("umbrella_yellow")));
		this.entityGenerators.add(rnd -> new Pedestrian(audio, 2, 50, 0, rnd.nextBoolean(), atlas.getRegion("umbrella_green")));
		
		if (wType == WeatherType.SNOW) {
			this.entityGenerators.add(rnd -> new Pedestrian(audio, 15, 0, 0, rnd.nextBoolean(), atlas.getRegion("snowman")));
			this.entityGenerators.add(rnd -> new Pedestrian(audio, 20, 0, 100, rnd.nextBoolean(), atlas.getRegion("skier")));
		}
	}

	@Override
	public GameObject generate(Random random) {
		final int index = random.nextInt(entityGenerators.size());
		return entityGenerators.get(index).generate(random);
	}
}
