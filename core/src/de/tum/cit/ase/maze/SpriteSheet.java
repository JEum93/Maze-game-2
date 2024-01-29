package de.tum.cit.ase.maze;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SpriteSheet {
    Texture texture; // this the texture of our sprite sheet
    int rows, columns; // number of rows and columns in our sprite sheet grid
    TextureRegion[] frames; // we use this to choose and store the pictures from our selected file
    int width,height;

    public SpriteSheet(Texture texture, int rows, int columns) {
        this.texture = texture;
        this.rows = rows;
        this.columns = columns;
    }
    public TextureRegion getTexture(int frame) {
        return frames[frame];
    } // we use this to get the frame we want from jpeg file
}
