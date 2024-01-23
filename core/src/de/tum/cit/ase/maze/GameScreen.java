package de.tum.cit.ase.maze;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;

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

    /**
     * Constructor for GameScreen. Sets up the camera and font.
     *
     * @param game The main game class, used to access global resources and methods.
     */
    public GameScreen(MazeRunnerGame game) {
        this.game = game;

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
            System.out.println("W");
            direction = "W";
            playery += Gdx.graphics.getDeltaTime() * playerSpeed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            System.out.println("S");
            direction = "S";
            playery -= Gdx.graphics.getDeltaTime() * playerSpeed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            System.out.println("A");
            direction = "A";
            playerx -= Gdx.graphics.getDeltaTime() * playerSpeed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            System.out.println("D");
            direction = "D";
            playerx += Gdx.graphics.getDeltaTime() * playerSpeed;
        }

        game.getSpriteBatch().begin(); // Important to call this before drawing anything

        // Render the text
        font.draw(game.getSpriteBatch(), "THIS IS GAY", playerx, playery);

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
