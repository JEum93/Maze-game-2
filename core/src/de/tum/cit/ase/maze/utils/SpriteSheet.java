package de.tum.cit.ase.maze.utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SpriteSheet {
    Texture texture; // this the texture of our sprite sheet
    int rows, columns; // number of rows and columns in our sprite sheet grid
    TextureRegion[] frames; // we use this to choose and store the pictures from our selected file
    int width,height;
    int from;
    int to;
    int current;
    float time;
    boolean loop;

    public SpriteSheet(Texture texture, int rows, int columns) {
        this.texture = texture;
        this.rows = rows;
        this.columns = columns;
    }
    public TextureRegion getTexture(int frame) {
        return frames[frame];
    } // we use this to get the frame we want from jpeg file


    // Will come to play when initializing character animation in class Player
    public void setPlay(int from, int to, float time, boolean loop) {
        this.from = from;
        this.to = to;
        this.time = time;
        this.loop = loop;
        current = from;
    }
}
