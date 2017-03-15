package ua.anton.vovan_alk.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

import ua.anton.vovan_alk.Drop;

public class Life {

    private Texture life_img;
    private static Array<Rectangle> lifes;
    private static int lifes_count;

    public Life() {
        life_img = new Texture("life.png");
        lifes = new Array<Rectangle>();
    }

    public static int getLife_count(){
        return lifes_count;
    }
    public static void setLife_count(int newlife){
        lifes_count = newlife;
    }
    public static void increment_lifes_count() {
        lifes_count++;
    }

    public static void uncrement_lifes_count() {
        lifes_count--;
        lifes.clear();
    }

    public void update(){
        for(int i=0; i < lifes_count; i++) {
            Rectangle life = new Rectangle();
            life.x = Drop.WIDTH - 40 - i * life_img.getWidth();
            life.y = Drop.HEIGHT - 10 - life_img.getHeight();
            lifes.add(life);
        }
    }

    public Texture getLife_img(){
        return life_img;
    }

    public Array<Rectangle> getLife(){
        return lifes;
    }

    public void dispose() {
        life_img.dispose();
    }
}
