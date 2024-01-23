package de.tum.cit.ase.maze;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.utils.ScreenUtils;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;



/**
 * The GameScreen class is responsible for rendering the gameplay screen.
 * It handles the game logic and rendering of the game elements.
 */
public class GameScreen implements Screen {

    private final MazeRunnerGame game;
    private final OrthographicCamera camera;
    private final BitmapFont font;
    private float sinusInput = 0f;


    // adding variables that can store character's position + movement speed
    private float playerx;
    private float playery;
    private float playerSpeed;

    // variables for the map
    public static final int WALL = 0;
    public static final int ENTRY_POINT = 1;
    public static final int EXIT = 2;
    public static final int TRAP = 3;
    public static final int ENEMY = 4;
    public static final int KEY = 5;

    private int[][] map; // Adding this variable to store the map data
    private TextureRegion[] tileset; // Adding this to texture the map
    private static final int TILE_WIDTH = 96;
    private static final int TILE_HEIGHT = 96;

    /**
     * Constructor for GameScreen. Sets up the camera and font.
     *
     * @param game The main game class, used to access global resources and methods.
     */
    public GameScreen(MazeRunnerGame game) {
        this.game = game;
        loadMap("level-2.properties");
        Texture texture = new Texture("mobs.png");
        TextureRegion[][] tmp = TextureRegion.split(texture, TILE_WIDTH, TILE_HEIGHT);
        int tilesetCols = tmp[0].length;
        int tilesetRows = tmp.length;

        // Flatten 2D array into a 1D array
        tileset = new TextureRegion[tilesetCols * tilesetRows];
        int index = 0;
        for (int i = 0; i < tilesetRows; i++) {
            for (int j = 0; j < tilesetCols; j++) {
                tileset[index++] = tmp[i][j];
            }
        }

        // Create and configure the camera for the game view
        camera = new OrthographicCamera();
        camera.setToOrtho(false);
        camera.zoom = 0.75f;

        // Get the font from the game's skin
        font = game.getSkin().getFont("font");

        // initialize added variables above
        playerx = 200f;
        playery = 200f;
        playerSpeed = 200f;
    }
    private void loadMap(String fileName) {
        try {
            Properties properties = new Properties();
            InputStream input = Gdx.files.internal("maps/" + fileName).read();
            if (input == null) {
                throw new FileNotFoundException("File not found: " + fileName);
            }
            System.out.println(fileName);
            properties.load(input);

            // Assuming the map size is fixed, you can modify this based on your actual requirements
            int rows = 99;
            int cols = 99;

            map = new int[rows][cols];

            // Iterate through the properties and populate the map array
            for (String key : properties.stringPropertyNames()) {
                String[] coordinates = key.split(",");
                int x = Integer.parseInt(coordinates[0]);
                int y = Integer.parseInt(coordinates[1]);
                int value = Integer.parseInt(properties.getProperty(key));
                map[x][y] = value;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Screen interface methods with necessary functionality
    @Override
    public void render(float delta) {


        // Check for escape key press to go back to the menu
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            game.goToMenu();
        }

        ScreenUtils.clear(0, 0, 0, 1); // Clear the screen

        camera.update(); // Update the camera


        // Move text in a circular path to have an example of a moving object
        //sinusInput += delta;
        //float textX = (float) (camera.position.x + Math.sin(sinusInput) * 100);
        //float textY = (float) (camera.position.y + Math.cos(sinusInput) * 100);

        // Set up and begin drawing with the sprite batch
        game.getSpriteBatch().setProjectionMatrix(camera.combined);

        //movment controls

        //last key pressed
        String direction = "";

        if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)) {
            direction = "W";
            playery += Gdx.graphics.getDeltaTime() * playerSpeed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            direction = "S";
            playery -= Gdx.graphics.getDeltaTime() * playerSpeed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            direction = "A";
            playerx -= Gdx.graphics.getDeltaTime() * playerSpeed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            direction = "D";
            playerx += Gdx.graphics.getDeltaTime() * playerSpeed;
        }

        game.getSpriteBatch().begin(); // Important to call this before drawing anything
        //rendering the actual map
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                int mapValue = map[i][j];
                if (mapValue >= 0 && mapValue < tileset.length) {
                    float tileX = j * TILE_WIDTH;
                    float tileY = i * TILE_HEIGHT;
                    game.getSpriteBatch().draw(tileset[mapValue], tileX, tileY);
                }
            }
        }

        // Render the text
        font.draw(game.getSpriteBatch(), "THIS IS less GAY", playerx, playery);

        // Draw the character next to the text :) / We can reuse sinusInput here
        // looping true makes our character have the leg walking animation
        // Time variables for each direction

        game.getSpriteBatch().draw(
                    game.getCharacterDownAnimation().getKeyFrame(sinusInput, true),
                    playerx - 96,
                    playery - 64,
                    64,
                    128
            );

        if(Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            game.getSpriteBatch().draw(
                    game.getCharacterUpAnimation().getKeyFrame(sinusInput, true),
                    playerx - 96,
                    playery - 64,
                    64,
                    128
            );
        }

        if(Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)) {
            game.getSpriteBatch().draw(
                    game.getCharacterLeftAnimation().getKeyFrame(sinusInput, true),
                    playerx - 96,
                    playery - 64,
                    64,
                    128
            );

        }if(Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            game.getSpriteBatch().draw(
                    game.getCharacterRightAnimation().getKeyFrame(sinusInput, true),
                    playerx - 96,
                    playery - 64,
                    64,
                    128
            );
        }

        game.getSpriteBatch().end(); // Important to call this after drawing everything
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
    }
    // Additional methods and logic can be added as needed for the game screen
}
