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

public class Beer{

    private Texture beer_img;
    private static int bottle_count;
    private Sound beer_sound;
    private Sound bottle_crach_sound;
    private Array<Rectangle> beers;
    private long lastDropTime;
    long speed = 1500000000;

    public Beer(){
        bottle_count = 0;
        beer_img = new Texture("bottle_cola.png");
        beer_sound = Gdx.audio.newSound(Gdx.files.internal("drink.mp3"));
        bottle_crach_sound = Gdx.audio.newSound(Gdx.files.internal("bottle_crash.mp3"));

        beers = new Array<Rectangle>();
        spawnBeers();
    }

    public int get_bottle_count() {
        return bottle_count;
    }

    public void spawnBeers(){
        Rectangle beer = new Rectangle();
        beer.x = MathUtils.random(0, Drop.WIDTH - 64);
        beer.y = Drop.HEIGHT;
        beer.width = 46;
        beer.height = 128;
        beers.add(beer);
        lastDropTime = TimeUtils.nanoTime();
    }

    public void update(float dt){
        genegate_speed();
        if (TimeUtils.nanoTime() - lastDropTime > speed) spawnBeers();

        Iterator<Rectangle> iter = beers.iterator();
        while (iter.hasNext()){
            Rectangle beer = iter.next();
            beer.y -= 200 * dt;
            if (beer.y + 64 < 0) {
                iter.remove();
                Life.uncrement_lifes_count();
                bottle_crach_sound.play();
            }
            if (beer.overlaps(Bucket.bucket)){
                bottle_count++;
                beer_sound.play();
                iter.remove();
            }
        }
    }

    private void genegate_speed() {
        switch(bottle_count){
            case 10: speed = (long) (1500000000 * 0.95);break;
            case 20: speed = (long) (1500000000 * 0.9);break;
            case 30: speed = (long) (1500000000 * 0.85);break;
            case 40: speed = (long) (1500000000 * 0.8);break;
            case 50: speed = (long) (1500000000 * 0.75);break;
            case 60: speed = (long) (1500000000 * 0.7);break;
            case 70: speed = (long) (1500000000 * 0.65);break;
            case 80: speed = (long) (1500000000 * 0.6);break;
            case 90: speed = (long) (1500000000 * 0.55);break;
            case 100: speed = (long) (1500000000 * 0.5);break;
            case 120: speed = (long) (1500000000 * 0.45);break;
            case 140: speed = (long) (1500000000 * 0.4);break;
            case 160: speed = (long) (1500000000 * 0.35);break;
            case 180: speed = (long) (1500000000 * 0.3);break;
            case 220: speed = (long) (1500000000 * 0.25);break;
            case 250: speed = (long) (1500000000 * 0.2);break;
            case 350: speed = (long) (1500000000 * 0.1);break;
            case 450: speed = (long) (1500000000 * 0.05);break;
            default: break;
        }
    }


    public Array<Rectangle> getBeers(){
        return beers;
    }
    public Texture getBeer_img(){
        return beer_img;
    }

    public void dispose() {
        beer_img.dispose();
        beer_sound.dispose();
        bottle_crach_sound.dispose();

    }

}
