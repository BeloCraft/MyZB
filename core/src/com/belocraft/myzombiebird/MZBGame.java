package com.belocraft.myzombiebird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.belocraft.mzbhelpers.AssetLoader;
import com.belocraft.screens.SplashScreen;

public class MZBGame extends Game{
	SpriteBatch batch;
	Texture img;
	
	@Override
	public void create () {            
            AssetLoader.load();
            setScreen(new SplashScreen(this));
	}
        
        @Override
        public void dispose()
        {
            super.dispose();
            AssetLoader.dispose();
        }
}
