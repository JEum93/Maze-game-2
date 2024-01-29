package de.tum.cit.ase.maze.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import de.tum.cit.ase.maze.utils.SpriteSheet;

public class Player extends Entity {
    float speed;
    SpriteSheet sheet;
    SpriteSheet walkSheet_down;
    SpriteSheet walkSheet_up;
    SpriteSheet walkSheet_left;
    SpriteSheet walkSheet_right;

    //represents the position of the player in the 2D game world
    //part of the LibGDX framework
    //used to represent 2D vectors, where x and y components denote the position in the horizontal and vertical directions
    Vector2 dir;
    Vector2 facingDir;

    public Player(Vector2 pos) {
        super(pos, null);

        // Initializing sheet with walkSheet_right initially makes the character start by looking to the right
        sheet = walkSheet_right;

        // player walk png was added to our asset folder as we didn't want to use all animations included in character png
        // player walk png is 4 x 4 grid png that only contains animations of the player moving up, down, left and right

        // rows 4 and columns 4 tells the program that our png is a 4 x 4 grid
        // using the setplay method from the spritesheet class we can tell the program to choose the animations from 0 to 3
        // which in this case are the walking down animations

        //0.1f sets the speed of the animation, each frame will be displayed for 0.1 seconds
        //true will allow the animation to loop repeating indefinitely.

        // the rest walksheet right, up and left follows the same logic

        walkSheet_down = new SpriteSheet(new Texture("player_walk.png"), 4, 4);
        walkSheet_down.setPlay(0, 3, 0.1f, true);

        walkSheet_right = new SpriteSheet(new Texture("player_walk.png"), 4, 4);
        walkSheet_right.setPlay(4, 7, 0.1f, true);

        walkSheet_up = new SpriteSheet(new Texture("player_walk.png"), 4, 4);
        walkSheet_up.setPlay(8, 11, 0.1f, true);

        walkSheet_left = new SpriteSheet(new Texture("player_walk.png"), 4, 4);
        walkSheet_left.setPlay(12, 15, 0.1f, true);

        speed = 2;

        // character direction/position is initialized as 0,0 coordinates
        dir = new Vector2(0, 0);

        // character facing direction is initialized as 1,0 coordinates initially starting into the game looking to the right
        facingDir = new Vector2(1, 0);
    }
}




