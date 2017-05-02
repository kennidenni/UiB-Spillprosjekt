package uib.teamdank.cargame.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import uib.teamdank.cargame.Pedestrian;
import uib.teamdank.common.GameObject;
import uib.teamdank.common.util.Generator;
import uib.teamdank.common.util.TextureAtlas;

/**
 * Generates {@link GameObject}s of the correct pedestrian type. This class
 * does not interact with the game directly; its purely functional. 
 */
public class PedestrianGenerator implements Generator<GameObject> {
	private final List<Generator<GameObject>> entityGenerators = new ArrayList<>();

	public PedestrianGenerator(TextureAtlas pedestrianAtlas1) {
		Random r=new Random();
		this.entityGenerators.add(rnd -> new Pedestrian(100,0,5,r.nextBoolean(),pedestrianAtlas1.getRegion("cyclist")));
		this.entityGenerators.add(rnd -> new Pedestrian(0,100,10,r.nextBoolean(),pedestrianAtlas1.getRegion("skateboard")));
		this.entityGenerators.add(rnd -> new Pedestrian(0,50,2,r.nextBoolean(),pedestrianAtlas1.getRegion("umbrella_blue")));
		this.entityGenerators.add(rnd -> new Pedestrian(0,50,2,r.nextBoolean(),pedestrianAtlas1.getRegion("umbrella_red")));
		this.entityGenerators.add(rnd -> new Pedestrian(0,50,2,r.nextBoolean(),pedestrianAtlas1.getRegion("umbrella_yellow")));
		this.entityGenerators.add(rnd -> new Pedestrian(0,50,2,r.nextBoolean(),pedestrianAtlas1.getRegion("umbrella_green")));

	}

	@Override
	public GameObject generate(Random random) {
		final int index = random.nextInt(entityGenerators.size());
		return entityGenerators.get(index).generate(random);
	}
}
