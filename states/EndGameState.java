package ua.anton.vovan_alk.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ua.anton.vovan_alk.Drop;

public class EndGameState extends State {

    private Texture background;
    private BitmapFont font, res;
    private int result;

    public EndGameState(GameStateManager gsm, int score) {
        super(gsm);
        background = new Texture("background_bar.jpg");
        font = new BitmapFont();
        res = new BitmapFont();
        result = score;
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new MenuState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    public void render(SpriteBatch sb) {
        sb.begin();
        sb.setProjectionMatrix(camera.combined);
        sb.draw(background, 0, 0, Drop.WIDTH, Drop.HEIGHT);
        font.draw(sb, "EndGameScreen :(", Drop.WIDTH/2, 20 + Drop.HEIGHT/2);
        res.draw(sb, "Your result: " + result, Drop.WIDTH/2, Drop.HEIGHT/2);
        sb.end();

    }

    @Override
    public void dispose() {
        background.dispose();
    }
}
