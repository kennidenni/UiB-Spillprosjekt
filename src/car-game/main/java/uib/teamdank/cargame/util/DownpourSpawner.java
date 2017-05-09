package uib.teamdank.cargame.util;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Queue;
import uib.teamdank.common.Game;
import uib.teamdank.common.GameObject;
import uib.teamdank.common.gui.Layer;

public class DownpourSpawner extends ScrollingSpawner {
    private Queue<Array<GameObject>> rows = new Queue<>();

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

    private Array<GameObject> spawnRow(float y) {
        Array<GameObject> row = new Array<>();
        float x = 0 - getCamera().viewportWidth / 2;
        while (x < getCamera().viewportWidth / 2) {
            GameObject sp = createNewSpawn(x, y);
            row.add(sp);
            x += sp.getWidth();
        }
        return row;
    }

    @Override
    public void update(float delta) {
        if(getSpawns().size == 0) {
            float y = getCamera().position.y - getCamera().viewportHeight / 2;
            while(y < getCamera().position.y + getCamera().viewportHeight / 2) {
                Array<GameObject> row = spawnRow(y);
                rows.addLast(row);
                y += row.get(0).getHeight();
            }
        }

        else {
            boolean move = false;
            Array<GameObject> row = rows.first();

            if(row.get(0).getPosition().y + row.get(0).getHeight() < getCamera().position.y - getCamera().viewportHeight / 2) {
                move = true;
            }

            if(move) {
                for(int i = 0; i < row.size; i++) {
                    if(rows.size > 1)
                        row.get(i).getPosition().y = rows.get(1).get(0).getPosition().y + row.get(i).getHeight();
                    else
                        row.get(i).getPosition().y += row.get(i).getHeight();
                    row.get(i).getPosition().x = row.get(i).getWidth()*i - getCamera().position.x - getCamera().viewportWidth / 2;
                }

                rows.addLast(rows.removeFirst());
            }

            if(rows.first().get(0).getPosition().y + rows.first().get(0).getHeight() < getCamera().position.y + getCamera().viewportHeight / 2) {
                rows.addFirst(spawnRow(rows.first().get(0).getPosition().y + rows.first().get(0).getHeight()));
            }
        }
    }
}
