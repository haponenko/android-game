package ua.anton.vovan_alk.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;

import ua.anton.vovan_alk.Drop;

public class Water{

    private Texture water_img;
    private Sound water_sound;
    private Array<Rectangle> waters;
    private long lastDropTime_water;
    long speed = 1500000000;

    public Water(){
        water_img = new Texture("martini.png");
        water_sound = Gdx.audio.newSound(Gdx.files.internal("water_drink.mp3"));

        waters = new Array<Rectangle>();

        spawnWaters();
    }

    public void spawnWaters(){
        Rectangle water = new Rectangle();
        water.x = MathUtils.random(0, Drop.WIDTH - 64);
        water.y = Drop.HEIGHT;
        water.width = 64;
        water.height = 64;
        waters.add(water);
        lastDropTime_water = TimeUtils.nanoTime();
    }

    public Array<Rectangle> getWater() {
        return waters;
    }

    public Texture getWater_img() {
        return water_img;
    }

    public void update(float dt) {
        if (TimeUtils.nanoTime() - lastDropTime_water > speed*15) spawnWaters();

        Iterator<Rectangle> iter = waters.iterator();
        while (iter.hasNext()){
            Rectangle water = iter.next();
            water.y -= 200 * dt;
            if (water.y + 64 < 0) {
                iter.remove();
            }
            if (water.overlaps(Bucket.bucket)){
                water_sound.play();
                Life.increment_lifes_count();
                iter.remove();
            }
        }
    }
    public void dispose() {
        water_img.dispose();
        water_sound.dispose();
    }
}
