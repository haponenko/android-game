package ua.anton.vovan_alk.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import ua.anton.vovan_alk.Drop;


public class Bucket{

    //private Texture bucket_img_1, bucket_img_2;
    public static Rectangle bucket;

    private Texture bucket_img;

    public Bucket(){
        bucket_img = new Texture("bucket_2.png");
        //bucket_img_1 = new Texture("bucket_1.png");
       // bucket_img_2 = new Texture("bucket_2.png");

        bucket = new Rectangle();
        bucket.x = Drop.WIDTH / 2 - 64 / 2;
        bucket.y = 20;
        bucket.width = 130;
        bucket.height = 150;
    }

    public void update(Vector3 touchPos){
        /*if(touchPos.x < Drop.WIDTH/2)
            bucket_img = bucket_img_2;
        else bucket_img = bucket_img_2;*/
    }

    public float getX(){
        return bucket.x;
    }
    public float getY(){
        return bucket.y;
    }

    public void bucket_move(Vector3 touchPos){
        bucket.x = (int) (touchPos.x -64 / 2);

        if (bucket.x < 0) bucket.x = 0;
        if (bucket.x > Drop.WIDTH-120) bucket.x = Drop.WIDTH-120;
    }

    public void dispose() {
        bucket_img.dispose();
       // bucket_img_1.dispose();
        //bucket_img_2.dispose();
    }


    public Texture getBucket_img() {
        return bucket_img;
    }

    public void setKey() {
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            bucket.x -= 700 * Gdx.graphics.getDeltaTime();
            //bucket_img = bucket_img_1;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            bucket.x += 700 * Gdx.graphics.getDeltaTime();
            //bucket_img = bucket_img_2;
        }
        if (bucket.x < 0) bucket.x = 0;
        if (bucket.x > Drop.WIDTH-120) bucket.x = Drop.WIDTH-120;
    }
}
