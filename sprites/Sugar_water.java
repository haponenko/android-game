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

public class Sugar_water{

    private Texture Sugar_water_img;
    private Sound sugar_water_sound;
    private Array<Rectangle> Sugar_waters;
    private long lastDropTime_Sugar_water;
    long speed = 1500000000;

    public Sugar_water(){
        Sugar_water_img = new Texture("vodka.png");
        sugar_water_sound = Gdx.audio.newSound(Gdx.files.internal("sugar_water_drink.mp3"));

        Sugar_waters = new Array<Rectangle>();

        spawnSugar_waters();
    }

    public void spawnSugar_waters(){
        Rectangle water = new Rectangle();
        water.x = MathUtils.random(0, Drop.WIDTH - 64);
        water.y = Drop.HEIGHT;
        water.width = 64;
        water.height = 64;
        Sugar_waters.add(water);
        lastDropTime_Sugar_water = TimeUtils.nanoTime();
    }

    public Array<Rectangle> getSugar_water() {
        return Sugar_waters;
    }

    public Texture getSugar_water_img() {
        return Sugar_water_img;
    }

    public void update(float dt) {
        if (TimeUtils.nanoTime() - lastDropTime_Sugar_water > speed*4) spawnSugar_waters();

        Iterator<Rectangle> iter = Sugar_waters.iterator();
        while (iter.hasNext()){
            Rectangle water = iter.next();
            water.y -= 200 * dt;
            if (water.y + 64 < 0) {
                iter.remove();
            }
            if (water.overlaps(Bucket.bucket)){
                sugar_water_sound.play();
                Life.uncrement_lifes_count();
                iter.remove();
            }
        }
    }
    public void dispose() {
        Sugar_water_img.dispose();
        sugar_water_sound.dispose();
    }
}
