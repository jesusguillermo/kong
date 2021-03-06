package com.me.game;

import javax.swing.JEditorPane;

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
import com.me.objetos.Cuervo;
import com.me.objetos.Edificio;
import com.me.objetos.Gato;
import com.me.objetos.Jet;
import com.me.objetos.Lata;
import com.me.objetos.Monedas;
import com.me.objetos.Nubes;
import com.me.objetos.Pandilla;
import com.me.objetos.Piso;
import com.me.objetos.Plataforma;
import com.me.objetos.Poste;
import com.me.screens.Screens;

public class WorldGameRender {

	SpriteBatch batcher;
	public  static OrthographicCamera oCam;
	Box2DDebugRenderer renderBox;
	WorldGame oWorld;
	SkeletonRenderer skelRenderer;
	boolean a ;

	public WorldGameRender(SpriteBatch batcher, WorldGame oWorld) {
		oCam = new OrthographicCamera(Screens.WORLD_WIDTH, Screens.WORLD_HEIGHT);
		oCam.position.set(Screens.WORLD_WIDTH / 2f, Screens.WORLD_HEIGHT / 2f,
				0);
		this.batcher = batcher;
		this.oWorld = oWorld;
		renderBox = new Box2DDebugRenderer();
		skelRenderer = new SkeletonRenderer();
		 a =true ;
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
		dibujarJey(delta);
		dibujarCuervo(delta);
		dibujarMoneda(delta);
		// ------------------------

		dbujarvida(delta);
		dibujarPlataforma(delta);
		dibujarGato(delta);	
		dibujarPandilla(delta);		
			

		batcher.end();
		
	renderBox.render(oWorld.oWorldBox, oCam.combined);

	}

	private void dbujarvida(float delta) {
		
	}

	private void dibujarCuervo(float delta) {
		int length = oWorld.arrCuervo.size;

		for (int i = 0; i < length; i++) {
			Cuervo oCue = oWorld.arrCuervo.get(i);
			TextureRegion keyframe;

			// true es que la aniimacion se repeteria muchas veces
			keyframe = Assets.sergio.getKeyFrame(oCue.state_time,true);

			batcher.draw(keyframe, oCue.posicion.x -.445f,oCue.posicion.y - .215f, .89f, .43f);
		}
	}

	private void dibujarJey(float delta) {
		int length = oWorld.arrJet.size;

		for (int i = 0; i < length; i++) {
			Jet objJet = oWorld.arrJet.get(i);
			TextureRegion keyframe;

			// true es que la aniimacion se repeteria muchas veces
			keyframe = Assets.Jet;

			batcher.draw(keyframe, objJet.posicion.x - .13f,
					objJet.posicion.y - .11f, .25f, .22f);
		/*	batcher.draw(keyframe, objJet.posicion.x - .15f,
					objJet.posicion.y - .18f, .32f, .39f);
					*/
		}
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
			if(oEdi.num == 0)
			{
			// true es que la aniimacion se repeteria muchas veces
			keyframe = Assets.edificio1;
			batcher.draw(keyframe, oEdi.posicion.x - 1.54f,
					oEdi.posicion.y - 2.68f, 3.08f, 5.36f);
			return;
			}
			else if(oEdi.num == 1)
			{
	// true es que la aniimacion se repeteria muchas veces
				keyframe = Assets.edificio2;
				batcher.draw(keyframe, oEdi.posicion.x - 1.775f,
						oEdi.posicion.y - 2.35f, 3.55f, 4.70f);
				return;
			}else if(oEdi.num == 2)
			{
	// true es que la aniimacion se repeteria muchas veces
				keyframe = Assets.edificio3;
				batcher.draw(keyframe, oEdi.posicion.x - 1.98f,
						oEdi.posicion.y - 2.35f, 3.96f, 4.7f);
				return;
			}else if(oEdi.num == 3)
			{
	// true es que la aniimacion se repeteria muchas veces
				keyframe = Assets.edificio4;
				batcher.draw(keyframe, oEdi.posicion.x - 0.815f,
						oEdi.posicion.y - 2f, 1.63f, 4.00f);
				return;
			}
			else 
			{
	// true es que la aniimacion se repeteria muchas veces
				keyframe = Assets.edificio5;
				batcher.draw(keyframe, oEdi.posicion.x - 1.245f,
						oEdi.posicion.y - 2.74f, 2.49f, 5.48f);
				return;
			}
			
		}
	}

	private void dibujarPiso(float delta) {
		int length = oWorld.arrPiso.size;

	    for (int i = 0; i < length; i++) 
		{
			Piso oPais = oWorld.arrPiso.get(i);
			TextureRegion keyframe;

			// true es que la aniimacion se repeteria muchas veces
			keyframe = Assets.pisotierra;

			batcher.draw(keyframe, oPais.posicion.x-4f,oPais.posicion.y-.2f, 8.03f, 2.21f);
		}
	}

	private void dibujarPlataforma(float delta) {
		int length = oWorld.arrPlataforma.size;

		for (int i = 0; i < length; i++) {
			Plataforma oPla = oWorld.arrPlataforma.get(i);
			TextureRegion keyframe;

			// true es que la aniimacion se repeteria muchas veces
			keyframe = Assets.Plataforma;

			batcher.draw(keyframe, oPla.posicion.x - 1.32f,
					oPla.posicion.y - 0.17f, 2.64f, .53f);
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
		
		if (obj.state == Gato.State.fly) 
		{
			batcher.draw(Assets.volando, obj.position.x - .355f,obj.position.y -.395f,.35f,.35f, .71f, .79f,1, 1, (float) Math.toDegrees(obj.angleRad));
			//batcher.draw(keyFrame, obj.position.x - .4f, obj.position.y -.4f, .5f, .5f, .8f, .8f, 1, 1, (float) Math.toDegrees(obj.angleRad));
			return;
			//Anikeyframe = Assets.aniFly;
			//loop = true;
		}
		
		else if (obj.state == Gato.State.corriendo)
		{
			Anikeyframe = Assets.aniRun;
			loop = true;
		}
		else if (obj.state == Gato.State.saltando)
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
			if(a)
			{
				a=false;
				Gdx.input.vibrate(100);
				
			}
		} 
		else if (obj.state == Gato.State.boos) 
		{
			Anikeyframe = Assets.aniRun;
			loop = true;
		}
		else
		{
			Anikeyframe = Assets.aniRun;
			loop = true;
		}
		
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
			batcher.draw(keyframe, oMon.posicion.x - 0.2f,oMon.posicion.y - 0.2f, 0.3f, .3f);
		}
	}

	private void dibujarPoste(float delta) {
		int length = oWorld.arrPoste.size;

		for (int i = 0; i < length; i++) {
			Poste oPos = oWorld.arrPoste.get(i);
			TextureRegion keyframe;

			if(oPos.tipo == 0)
			{
				keyframe = Assets.posteluz;
				batcher.draw(keyframe, oPos.posicion.x - .33f,
						oPos.posicion.y - 2.245f, .66f, 4.49f);
				return;
			}
			else if (oPos.tipo == 1) 
			{
				keyframe = Assets.postecat;
				batcher.draw(keyframe, oPos.posicion.x - 0.56f,
						oPos.posicion.y - 2.435f, 1.12f, 4.87f);
				return;
			}
			else if (oPos.tipo == 2) 
			{
				keyframe = Assets.postecuervo;
				batcher.draw(keyframe, oPos.posicion.x - 0.515f,
						oPos.posicion.y - 2.39f, 1.03f, 4.78f);
				return;
			}
			else if (oPos.tipo == 3) 
			{
				keyframe = Assets.postecatgrumpy;
				batcher.draw(keyframe, oPos.posicion.x - .5f,
						oPos.posicion.y - 2.43f, 1f, 4.86f);
				return;
			}
			else 
			{
				keyframe = Assets.postecat;
				batcher.draw(keyframe, oPos.posicion.x - .56f,
					oPos.posicion.y -2.435f, 1.12f, 4.87f);
				return;
			}
			
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
