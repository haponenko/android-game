package ua.anton.vovan_alk.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import ua.anton.vovan_alk.Drop;
import ua.anton.vovan_alk.sprites.Beer;
import ua.anton.vovan_alk.sprites.Bucket;
import ua.anton.vovan_alk.sprites.Button;
import ua.anton.vovan_alk.sprites.Life;
import ua.anton.vovan_alk.sprites.Sugar_water;
import ua.anton.vovan_alk.sprites.Water;

public class PlayState extends State {

    BitmapFont font;
    private Texture background_img, pause, settings;
    protected static Music main_sound;
    private Beer beer;
    private Water water;
    private Sugar_water sugar_water;
    private Bucket bucket;
    private Life life;
    private Vector3 touchPos;
    private Button button_pause, button_settings;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        camera.setToOrtho(false, Drop.WIDTH, Drop.HEIGHT);
        font = new BitmapFont();
        main_sound = Gdx.audio.newMusic(Gdx.files.internal("city_effect.mp3"));
        background_img = new Texture("background_bar.jpg");
        pause = new Texture("pause.png");
        settings = new Texture("settings.png");
        button_pause = new Button(pause, 0, Drop.HEIGHT-pause.getHeight(), pause.getWidth(), pause.getHeight());
        button_settings = new Button(settings, 10+settings.getWidth(),
                Drop.HEIGHT-settings.getHeight(), settings.getWidth(), settings.getHeight());
        beer = new Beer();
        water = new Water();
        sugar_water = new Sugar_water();
        bucket = new Bucket();
        life = new Life();
        Life.setLife_count(5);
        touchPos = new Vector3();

        main_sound.setLooping(true);
        main_sound.play();
    }

    public static void playMusic(){
        main_sound.play();
    }
    public static void stopMusic(){
        main_sound.pause();
    }

    public static Music getmain_sound() {
        return main_sound;
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.isTouched()) {
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            bucket.bucket_move(touchPos);
            if(button_pause.isPressed(touchPos.x, touchPos.y)) {
                gsm.push(new PauseState(gsm));
            }
            if(button_settings.isPressed(touchPos.x, touchPos.y)) {
                gsm.push(new SettingsState(gsm));
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)) bucket.setKey();
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            gsm.push(new PauseState(gsm));
        }

    }

    @Override
    public void update(float dt) {
        handleInput();
        life.update();
        beer.update(dt);
        water.update(dt);
        bucket.update(touchPos);
        sugar_water.update(dt);
    }

    @Override
    public void render(SpriteBatch sb) {
        camera.update();
        sb.setProjectionMatrix(camera.combined);

        sb.begin();
        sb.draw(background_img, 0, 0, Drop.WIDTH, Drop.HEIGHT);
        button_pause.draw_button(sb);
        button_settings.draw_button(sb);
        font.draw(sb, "" + beer.get_bottle_count(), Drop.WIDTH / 2, Drop.HEIGHT );
        sb.draw(bucket.getBucket_img(), bucket.getX(), bucket.getY()-20);

        for(Rectangle beers: beer.getBeers()) {
            sb.draw(beer.getBeer_img(), beers.x, beers.y);
        }
        for(Rectangle waters: water.getWater()) {
            sb.draw(water.getWater_img(), waters.x, waters.y);
        }
        for(Rectangle sugar_waters: sugar_water.getSugar_water()) {
            sb.draw(sugar_water.getSugar_water_img(), sugar_waters.x, sugar_waters.y);
        }
        for(Rectangle lifes: life.getLife()){
            sb.draw(life.getLife_img(), lifes.x, lifes.y);
        }
        sb.end();

        if(Life.getLife_count() == 0) {
            gsm.set(new EndGameState(gsm, beer.get_bottle_count()));
            main_sound.stop();
        }
    }


    @Override
    public void dispose() {
        background_img.dispose();
        pause.dispose();
        main_sound.dispose();
        beer.dispose();
        water.dispose();
        sugar_water.dispose();
        bucket.dispose();
        life.dispose();
        pause.dispose();
        settings.dispose();
    }
}

