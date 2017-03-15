package ua.anton.vovan_alk.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ua.anton.vovan_alk.Drop;
import ua.anton.vovan_alk.sprites.Button;

public class SettingsState extends State {

    private Texture background, sound_on, back;
    private BitmapFont font;
    Button button_sound, button_back;

    public SettingsState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("background_bar.jpg");
        sound_on = new Texture("sound_on.png");
        back = new Texture("back.png");
        button_sound = new Button(sound_on, Drop.WIDTH/2-sound_on.getWidth(), Drop.HEIGHT/2, sound_on.getWidth(), sound_on.getHeight());
        button_back = new Button(back, 0, 0, back.getWidth(), back.getHeight());
        font = new BitmapFont();
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()) {
            if (button_sound.isPressed(Gdx.input.getX(), Drop.HEIGHT-Gdx.input.getY()))
                if (PlayState.getmain_sound().isPlaying()) PlayState.stopMusic();
                else PlayState.playMusic();
            if(button_back.isPressed(Gdx.input.getX(), Drop.HEIGHT-Gdx.input.getY())) gsm.pop();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.setProjectionMatrix(camera.combined);
        sb.draw(background, 0, 0, Drop.WIDTH, Drop.HEIGHT);
        button_sound.draw_button(sb);
        button_back.draw_button(sb);
        font.draw(sb, "SettingScreen :)", Drop.WIDTH/2, Drop.HEIGHT);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        sound_on.dispose();
        back.dispose();
    }
}

