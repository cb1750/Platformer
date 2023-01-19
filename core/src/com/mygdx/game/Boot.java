package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class Boot extends Game {
	public static Boot INSTANCE;
	private int widthScreen, heightScreen;
	OrthographicCamera orthographicCamera;
	public Boot(){
		INSTANCE = this;
	}

	@Override
	public void create() {
this.widthScreen = Gdx.graphics.getWidth();
this.heightScreen = Gdx.graphics.getHeight();
this.orthographicCamera = new OrthographicCamera();
this.orthographicCamera.setToOrtho(false,widthScreen,heightScreen);
setScreen(new GameScreen(orthographicCamera));
	}
}
