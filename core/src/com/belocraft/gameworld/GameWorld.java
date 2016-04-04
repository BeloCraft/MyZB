/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.belocraft.gameworld;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.belocraft.gameobjects.Bird;
import com.belocraft.gameobjects.ScrollHandler;
import com.belocraft.mzbhelpers.AssetLoader;

/**
 *
 * @author Eugene
 */
public class GameWorld {
    
    private GameState currentState;
    
    private Bird bird;
    private ScrollHandler scroller;
    
    private Rectangle ground;
    
    private int score = 0;
    private float runTime = 0;
    private int midPointY;
    
    boolean one_play_die = true;    
    boolean start_bg = false;
    float delay = 0;
    
    public GameWorld(int midPointY)
    {
        currentState = GameState.MENU;
        bird = new Bird(33, midPointY - 5, 17, 12);
        scroller = new ScrollHandler(this, midPointY + 66);
        ground = new Rectangle(0, midPointY + 66, 136, 11);    
        currentState = GameState.READY;
        this.midPointY = midPointY;
    }
    
     public void update(float delta) {
        runTime += delta;
        
        switch (currentState) {
        case READY:
        case MENU:
            updateReady(delta);
            break;

        case RUNNING:
            updateRunning(delta);
            break;
        default:
            break;
        }

    }

    private void updateReady(float delta) {
        bird.updateReady(runTime);
        scroller.updateReady(delta);
    }
    
    public void updateRunning(float delta) {        
                        
        if (delta > .15f) {
            delta = .15f;
        }

        bird.update(delta);
        scroller.update(delta);

        if (scroller.collides(bird) && bird.isAlive()) {
            scroller.stop();
            bird.die();
            if (delay > 0.5F) AssetLoader.machine.stop();
            AssetLoader.dead.play(0.9F);
        }

        if (Intersector.overlaps(bird.getBoundingCircle(), ground)) {
            scroller.stop();
            if (one_play_die)
            {                
                if (delay > 0.5F) AssetLoader.machine.stop();
                AssetLoader.dead.play(0.9F);
                one_play_die = false;
            }
            bird.die();
            bird.decelerate(); 
            currentState = GameState.GAMEOVER;
            
             if (score > AssetLoader.getHighScore()) {
                AssetLoader.setHighScore(score);
                currentState = GameState.HIGHSCORE;
            }
        }
        
        if (!start_bg)
        {            
            if (delay > 0.5F)
            {          
                if (bird.isAlive()) AssetLoader.machine.loop(0.6F);
                start_bg = true;
            }
            delay += delta;
        }
    }
    
    public void restart() {
        start_bg = false;
        delay = 0;
        currentState = GameState.READY;
        score = 0;
        bird.onRestart(midPointY - 5);
        scroller.onRestart();
        currentState = GameState.READY;
    }
    
    public Bird getBird()
    {
        return bird;
    }
    
    public ScrollHandler getScroller() {
        return scroller;
    }
    
    public int getScore() {
        return score;
    }

    public void addScore(int increment) {
        score += increment;
    }

    public boolean isReady() {
        return currentState == GameState.READY;
    }

    public boolean isHighScore() {
    return currentState == GameState.HIGHSCORE;
    }
    
    public void start() {
        currentState = GameState.RUNNING;
    }

    public boolean isGameOver() {
        return currentState == GameState.GAMEOVER;
    }
    
    public boolean isMenu() {
        return currentState == GameState.MENU;
    }

    public boolean isRunning() {
        return currentState == GameState.RUNNING;
    }
    
    public int getMidPointY() {
        return midPointY;
    }
    
    public void ready() {
        currentState = GameState.READY;
    }
    
    public enum GameState 
    {
        MENU, READY, RUNNING, GAMEOVER, HIGHSCORE
    }
}
