package ua.anton.vovan_alk.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ua.anton.vovan_alk.Drop;


public class PauseState extends State {

    private Texture background;
    private BitmapFont font;


    public PauseState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("background_bar.jpg");
        font = new BitmapFont();
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.pop();
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
        font.draw(sb, "PauseScreen :)", Drop.WIDTH/2, Drop.HEIGHT/2);
        sb.end();

    }

    @Override
    public void dispose() {
        background.dispose();
    }
}
