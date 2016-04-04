/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.belocraft.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.belocraft.gameworld.GameWorld;
import com.belocraft.gameworld.GameRenderer;
import com.belocraft.mzbhelpers.InputHandler;

/**
 *
 * @author Eugene
 */
public class GameScreen implements Screen{

    private GameWorld world;
    private GameRenderer renderer;
    private float runtime = 0;
    
    public GameScreen()
    {
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        float gameWidth = 136;
        float gameHeight = screenHeight / (screenWidth / gameWidth);

        int midPointY = (int) (gameHeight / 2);
        world = new GameWorld(midPointY);
        renderer = new GameRenderer(world, (int)gameHeight, midPointY);
        
        Gdx.input.setInputProcessor(
                new InputHandler(world, screenWidth / gameWidth, screenHeight / gameHeight));
    }
    
    @Override
    public void show() {
        Gdx.app.log("SCREEN","show");
    }

    @Override
    public void render(float delta) {        
        world.update(delta);
        renderer.render(delta,runtime);
        runtime += delta;        
    }

    @Override
    public void resize(int width, int height){        
    }

    @Override
    public void pause() {        
    }

    @Override
    public void resume() {       
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        
    }
    
}
