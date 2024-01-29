package de.tum.cit.ase.maze;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class SpriteSheet {
    Texture texture; // this the texture of our sprite sheet
    int rows, cols; // number of rows and columns in our sprite sheet grid
    TextureRegion[] frames; // we use this to choose and store the pictures from our selected file
    int width,height;


    public SpriteSheet(Texture texture, int rows, int cols) {
        this.texture = texture;
        this.rows = rows;
        this.cols = cols;
        frames = new TextureRegion[rows * cols];
    }
    public TextureRegion getTexture(int frame) {
        return frames[frame];
    } // we use this to get the frame we want from jpeg file
    public float getWidth() {
        return width;
    }
    public float getHeight() {
        return height;
    }
}
