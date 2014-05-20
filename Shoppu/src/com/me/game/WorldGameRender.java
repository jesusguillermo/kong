package com.me.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.esotericsoftware.spine.SkeletonRenderer;
import com.me.mygdxgame.Assets;
import com.me.objetos.Barandal;
import com.me.objetos.Boos;
import com.me.objetos.BoteBasura;
import com.me.objetos.CajaCarton;
import com.me.objetos.Cerros;
import com.me.objetos.Edificio;
import com.me.objetos.Foco;
import com.me.objetos.Gato;
import com.me.objetos.Lata;
import com.me.objetos.Monedas;
import com.me.objetos.Nubes;
import com.me.objetos.Paisaje;
import com.me.objetos.Pandilla;
import com.me.objetos.Pasaje;
import com.me.objetos.Piso;
import com.me.objetos.Plataforma;
import com.me.objetos.Poste;
import com.me.objetos.Rejilla;
import com.me.objetos.Rejillas;
import com.me.objetos.Tuberia;
import com.me.objetos.TuberiaLarga;
import com.me.screens.Screens;

public class WorldGameRender {

	SpriteBatch batcher;
	public  static OrthographicCamera oCam;
	Box2DDebugRenderer renderBox;
	WorldGame oWorld;
	SkeletonRenderer skelRenderer;

	public WorldGameRender(SpriteBatch batcher, WorldGame oWorld) {
		oCam = new OrthographicCamera(Screens.WORLD_WIDTH, Screens.WORLD_HEIGHT);
		oCam.position.set(Screens.WORLD_WIDTH / 2f, Screens.WORLD_HEIGHT / 2f,
				0);
		this.batcher = batcher;
		this.oWorld = oWorld;
		renderBox = new Box2DDebugRenderer();
		skelRenderer = new SkeletonRenderer();
	}

	public void render(float delta) {

		batcher.begin();
		batcher.draw(Assets.Fondo, 0, 0, 800, 480);
		batcher.end();
		renderbackground(delta);
		oCam.update();

		// matriz de proyeccion
		batcher.setProjectionMatrix(oCam.combined);
		batcher.begin();
		
		batcher.disableBlending();

		batcher.enableBlending();	
		
		//dibujarPiso(delta);
		dibujarNubes(delta);
		// ------------------------

		dibujarEdificio(delta);
		dibujarPoste(delta);
		dibujarPiso(delta);
		dibujarBote(delta);
		dibujarLata(delta);
		dibujarCajaCarton(delta);
		dibujarBoos(delta);
		dibujarMoneda(delta);
		// ------------------------

		dibujarPlataforma(delta);
		//dibujarMoneda(delta);
		dibujarGato(delta);	
		dibujarPandilla(delta);		
			

		batcher.end();
		//renderBox.render(oWorld.oWorldBox, oCam.combined);

	}

	private void dibujarCajaCarton(float delta) {
		int length = oWorld.arrCajacarton.size;

		for (int i = 0; i < length; i++) {
			CajaCarton oCaj = oWorld.arrCajacarton.get(i);
			TextureRegion keyframe;

			// true es que la aniimacion se repeteria muchas veces
			keyframe = Assets.cajacarton;

			batcher.draw(keyframe, oCaj.posicion.x - .45f,oCaj.posicion.y - .3f, .89f, .60f);
		}
	}

	private void dibujarBoos(float delta) {
		int length = oWorld.arrBoos.size;

		for (int i = 0; i < length; i++) {
			Boos oBoos = oWorld.arrBoos.get(i);
			TextureRegion keyframe;

			// true es que la aniimacion se repeteria muchas veces
			keyframe = Assets.Pescadito.getKeyFrame(oBoos.state_time,true);

			batcher.draw(keyframe, oBoos.posicion.x - .15f,
					oBoos.posicion.y - .18f, .32f, .39f);
		}
	}

	private void dibujarLata(float delta) {
		int length = oWorld.arrLata.size;

		for (int i = 0; i < length; i++) {
			Lata oLat = oWorld.arrLata.get(i);
			TextureRegion keyframe;

			// true es que la aniimacion se repeteria muchas veces
			keyframe = Assets.lata1;

			batcher.draw(keyframe, oLat.posicion.x - .27f,
					oLat.posicion.y - .12f, .54f, .23f);
		}
	}

	private void dibujarBote(float delta) {
		int length = oWorld.arrBoteBasura.size;

		for (int i = 0; i < length; i++) {
			BoteBasura oBot = oWorld.arrBoteBasura.get(i);
			TextureRegion keyframe;

			// true es que la aniimacion se repeteria muchas veces
			keyframe = Assets.botebasura;

			batcher.draw(keyframe, oBot.posicion.x -.75f,oBot.posicion.y - .44f, 1.07f, .88f);
		}
	}


	private void dibujarEdificio(float delta) {
		int length = oWorld.arrEdificio.size;

		for (int i = 0; i < length; i++) {
			Edificio oEdi = oWorld.arrEdificio.get(i);
			TextureRegion keyframe;

			// true es que la aniimacion se repeteria muchas veces
			keyframe = Assets.edificio2;

			batcher.draw(keyframe, oEdi.posicion.x - 1.4f,
					oEdi.posicion.y - 1.81f, 3.04f, 3.62f);
		}
	}

	private void dibujarPiso(float delta) {
		int length = oWorld.arrPiso.size;

		for (int i = 0; i < length; i++) {
			Piso oPais = oWorld.arrPiso.get(i);
			TextureRegion keyframe;

			// true es que la aniimacion se repeteria muchas veces
			keyframe = Assets.pisotierra;

			batcher.draw(keyframe, oPais.posicion.x,oPais.posicion.y-.2f, 8.03f, 2.21f);
		}
	}

	private void dibujarPlataforma(float delta) {
		int length = oWorld.arrPlataforma.size;

		for (int i = 0; i < length; i++) {
			Plataforma oPla = oWorld.arrPlataforma.get(i);
			TextureRegion keyframe;

			// true es que la aniimacion se repeteria muchas veces
			keyframe = Assets.Plataforma;

			batcher.draw(keyframe, oPla.posicion.x - 1.3f,
					oPla.posicion.y - 0.15f, 2.64f, .53f);
		}
	}

	private void dibujarPandilla(float delta) {

		TextureRegion keyframe = Assets.Panadilla.getKeyFrame(
				oWorld.oPan.state_time, true);

		batcher.draw(keyframe, oWorld.oPan.posicion.x - 1f,
				oWorld.oPan.posicion.y - 0.5f, 2f, 1f);

	}

	private void dibujarGato(float delta) {
		Gato obj = oWorld.OGato;
		
		com.esotericsoftware.spine.Animation Anikeyframe;
		boolean loop;
		if (obj.state == Gato.State.saltando)
		{
			Anikeyframe = Assets.aniJump;
			loop = false;
		} 
		else if (obj.state == Gato.State.cayendo) 
		{
			Anikeyframe = Assets.aniFall;
			loop = false;
		} 
		else if (obj.state == Gato.State.muerto)
		{
			Anikeyframe = Assets.anicrash;
			loop = false;
		} 
		else if (obj.state == Gato.State.boos) 
		{
			Anikeyframe = Assets.aniRun;
			loop = true;
		}
		else if (obj.state == Gato.State.fly) 
		{
			Anikeyframe = Assets.aniFly;
			loop = true;
		}
		else
		{
			Anikeyframe = Assets.aniRun;
			loop = true;
		}
		Gdx.app.log("delta", delta+"");
		Anikeyframe.apply(obj.skel, oWorld.OGato.statetime, oWorld.OGato.statetime, loop, null);
		obj.skel.setX(obj.position.x);
		obj.skel.setY(obj.position.y - 0.25f);
		obj.skel.update(delta);
		obj.skel.updateWorldTransform();
		skelRenderer.draw(batcher, obj.skel);
	}

	private void dibujarMoneda(float delta) {
		int length = oWorld.arrMonedas.size;

		for (int i = 0; i < length; i++) {
			Monedas oMon = oWorld.arrMonedas.get(i);
			TextureRegion keyframe;

			// true es que la aniimacion se repeteria muchas veces
			keyframe = Assets.moneda.getKeyFrame(oMon.state_time, true);
			batcher.draw(keyframe, oMon.posicion.x - 0.2f,oMon.posicion.y - 0.2f, 0.2f, .2f);
		}
	}

	private void dibujarPoste(float delta) {
		int length = oWorld.arrPoste.size;

		for (int i = 0; i < length; i++) {
			Poste oPos = oWorld.arrPoste.get(i);
			TextureRegion keyframe;

			// true es que la aniimacion se repeteria muchas veces
			keyframe = Assets.posteluz;

			batcher.draw(keyframe, oPos.posicion.x - .41f,oPos.posicion.y -1.7f, 1.05f, 3.46f);
			
		}
	}

	private void dibujarNubes(float delta) {
		int length = oWorld.arrNubes.size;

		for (int i = 0; i < length; i++) {
			Nubes oNube = oWorld.arrNubes.get(i);
			TextureRegion keyframe;
			// true es que la aniimacion se repeteria muchas veces
			keyframe = Assets.Nubes;

			batcher.draw(keyframe, oNube.posicion.x - 3.8f,
					oNube.posicion.y - 0.55f, 7.6f, 1.3f);
		}
	}

	private void renderbackground(float delta) 
	{
		Assets.parallaxFondo.render(delta);		
	}

}
