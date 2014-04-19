package com.me.screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.me.game.GameScreen;
import com.me.mygdxgame.Assets;
import com.me.mygdxgame.MainShoppu;

public class MainMenu extends Screens{

	ImageButton btnEmpezar;
	public MainMenu(final MainShoppu game) {
		super(game);
		btnEmpezar = new ImageButton(new TextureRegionDrawable(Assets.btnEmpezar));
		btnEmpezar.setPosition(SCREEN_WIDTH / 2f  - btnEmpezar.getWidth() /2f, SCREEN_HEIGHT / 2f - btnEmpezar.getHeight()/2f);
		btnEmpezar.addListener(new InputListener(){
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				btnEmpezar.setY(btnEmpezar.getY() - 3);
				return true;
			}
			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				btnEmpezar.setY(btnEmpezar.getY() + 3);
				game.setScreen(new GameScreen(game));
			}
		});
		
		stage.addActor(btnEmpezar);
		
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(float delta) {
	//	Assets.parallaxFondo.render(delta);
		batcher.begin();
		batcher.draw(Assets.Fondo, 0, 0, 800, 480);
		batcher.end();
	}

}
