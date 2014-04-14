package com.me.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.me.mygdxgame.Assets;
import com.me.mygdxgame.MainShoppu;
import com.me.screens.MainMenu;
import com.me.screens.Screens;
import com.sun.corba.se.impl.encoding.CodeSetConversion.BTCConverter;

public class GameScreen extends Screens {

	public enum State {ready,running,gameover}
	WorldGame oWorld;
	WorldGameRender renderer;
	public State state;
	boolean jump;
	
	
	
	public GameScreen(MainShoppu game) {
		super(game);
			oWorld = new WorldGame();
			renderer = new WorldGameRender(batcher,oWorld);
			state = State.ready;
	}

	@Override
	public void update(float delta) {
		switch (state) {
		case ready:
			updateready(delta);
			break;
		case running:
			updaterunning(delta);
			break;
		default:
		case gameover:
			updategameover(delta);
			break;
		}
	}

	private void updategameover(float delta) {
		if(Gdx.input.isTouched())
		{
			game.setScreen(new MainMenu(game));
		}
	}

	private void updaterunning(float delta) {
		if (Gdx.input.isTouched() || Gdx.input.isKeyPressed(Keys.SPACE))
			jump = true;
		 
		
		oWorld.update(delta,jump);
		if(oWorld.state == WorldGame.State.GameOver)
		{
			state = State.gameover;
		}	
		jump = false;
	}

	private void updateready(float delta) {
		if(Gdx.input.isTouched())
		{
			state = State.running;
		}
	}

	@Override
	public void draw(float delta) {
		renderer.render(delta);
		oCam.update();
		batcher.setProjectionMatrix(oCam.combined);
		batcher.begin();
		
		switch (state) {
		case ready:
			drawready(delta);
			break;
		case running:
			drawrunning(delta);
			break;
		default:
		case gameover:
			drawgameover(delta);
			break;
		}
		batcher.end();
	}

	private void drawgameover(float delta) {
		Assets.Font.draw(batcher, "Haz perdido", 100, 200);		
		
	}

	private void drawrunning(float delta) {
		Assets.Font.draw(batcher, "Monedas: " + oWorld.monedas, 0, oCam.position.y);
		Assets.Font.draw(batcher, "TIME  :  " + (int)oWorld.time, oCam.position.x-30, oCam.position.y*2);

		
	}

	private void drawready(float delta) {
		Assets.Font.draw(batcher, "Toca para iniciar", 0, 200); 
	}

}
