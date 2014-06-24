package com.me.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.me.mygdxgame.Assets;
import com.me.mygdxgame.MainShoppu;
import com.me.screens.MainMenu;
import com.me.screens.Screens;
import com.sun.corba.se.impl.encoding.CodeSetConversion.BTCConverter;

public class GameScreen extends Screens {

	Button btTryAgain, btLeaderBoards, btShop;
	Image background, BoteBasura;
	
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
			InicializarGameOver();
			
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

		stage.addActor(btTryAgain);
		stage.addActor(BoteBasura);
	}

	private void updaterunning(float delta) {
		if (Gdx.input.isTouched() || Gdx.input.isKeyPressed(Keys.SPACE))
			jump = true;
		 
		
		oWorld.update(delta,jump);
		if(oWorld.state == WorldGame.State.GameOver)
		{
		//	state = State.gameover;
			setGameOver();
		}	
		jump = false;
	}

	private void setGameOver() 
	{
		state = State.gameover;
		stage.addActor(background);
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
	private void InicializarGameOver()
	{
		background = new Image(Assets.fondogameover);
		background.setSize(802, 482);
		background.setPosition(-1,-1);
		
		BoteBasura = new Image(Assets.botegameover);
		BoteBasura.setSize(258, 137);
		BoteBasura.setPosition(SCREEN_WIDTH - BoteBasura.getWidth(),  0);
		
		btTryAgain = new Button(new TextureRegionDrawable(Assets.btntyagain));
		btTryAgain.setSize(271, 261);
		btTryAgain.setPosition(SCREEN_WIDTH - btTryAgain.getWidth(), 80);

		btTryAgain.addListener(new InputListener()
		{
			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				btTryAgain.setY(btTryAgain.getY() + 3);
				game.setScreen(new GameScreen(game));
			}
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				btTryAgain.setY(btTryAgain.getY() - 3);
				return true;
			}
		});		
	}

	private void drawrunning(float delta) {
		Assets.Font.draw(batcher, "Monedas: " + oWorld.monedas, 0, oCam.position.y);
		Assets.Font.draw(batcher, "TIME  :  " + (int)oWorld.time, oCam.position.x-30, oCam.position.y*2);
		Assets.Font.draw(batcher, "Distancia  :  " + (int)oWorld.distancia, 0, oCam.position.y-10);
		
		
	}

	private void drawready(float delta) {
		Assets.Font.draw(batcher, "Toca para iniciar", 0, 200); 
	}

}
