package uib.teamdank.cargame.util;

import com.badlogic.gdx.graphics.OrthographicCamera;
import uib.teamdank.common.GameObject;
import uib.teamdank.common.gui.Layer;

public class DownpourSpawner extends ScrollingSpawner {
    private GameObject lastSpawn;

    public DownpourSpawner(Layer layer, OrthographicCamera camera, WeatherGenerator weatherGenerator) {
        super(layer, camera, weatherGenerator);
    }

    private GameObject createNewSpawn(float x, float y) {
        GameObject spawn = getGenerator().generate(getRandom());
        spawn.getPosition().y = y;
        spawn.getPosition().x = x;
        spawn.setFlipHorizontally(false);
        getSpawns().add(spawn);
        getLayer().addGameObject(spawn);
        return spawn;
    }

    private void spawnRow(float y) {
        float x = 0 - getCamera().viewportWidth / 2;
        while (x < getCamera().viewportWidth / 2) {
            lastSpawn = createNewSpawn(x, y);
            x += lastSpawn.getWidth();
        }
    }

    @Override
    public void update(float delta) {
        if(getSpawns().size == 0) {
            float y = getCamera().position.y - getCamera().viewportHeight / 2;
            while(y < getCamera().position.y + getCamera().viewportHeight / 2) {
                spawnRow(y);
                y += lastSpawn.getHeight();
            }
        }

        else if(lastSpawn.getPosition().y - lastSpawn.getHeight() < getCamera().position.y - getCamera().viewportHeight / 2) {
            spawnRow(lastSpawn.getPosition().y + lastSpawn.getHeight());
            deleteOldStructures();
        }
    }
}
