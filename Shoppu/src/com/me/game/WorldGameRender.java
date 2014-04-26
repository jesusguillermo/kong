package com.me.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
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
	OrthographicCamera oCam;
	Box2DDebugRenderer renderBox;
	WorldGame oWorld;
	
	public WorldGameRender(SpriteBatch batcher, WorldGame oWorld) {
		oCam = new OrthographicCamera(Screens.WORLD_WIDTH, Screens.WORLD_HEIGHT);
		oCam.position.set(Screens.WORLD_WIDTH/2f,Screens.WORLD_HEIGHT/2f, 0);
		this.batcher = batcher;
		this.oWorld = oWorld;
		renderBox = new Box2DDebugRenderer();
	}

	public void render(float delta) {
		
		batcher.begin();
		batcher.draw(Assets.Fondo, 0, 0, 800, 480);
		batcher.end();
		
		//renderbackground(delta);
		oCam.update();
		
		//matriz de proyeccion
		batcher.setProjectionMatrix(oCam.combined);
		batcher.begin();
		dibujarPiso(delta);
		dibujarNubes(delta);
		dibujarPaisaje(delta);
		dibujarCerro(delta);
		dibujarRejillas(delta);
		//------------------------
		
		 dibujarEdificio(delta);
		 dibujarPiso(delta);
		 dibujarBarandal(delta);
		 dibujarBote(delta);
		 dibujarLata(delta);
		 dibujarPoste(delta);
		 dibujarCajaCarton(delta);
		 dibujarBoos(delta);
		 /*
		dibujarTuberia(delta);
		dibujarTuberiaLarga(delta);
		dibujarFoco(delta);
		dibujarPasaje(delta);
		dibujarCloaca(delta);
		*/
		//------------------------

			dibujarPlataforma(delta);
		dibujarMoneda(delta);
		dibujarPandilla(delta);
		dibujarGato(delta);
		
		batcher.end();
		
		renderBox.render(oWorld.oWorldBox, oCam.combined);
	}

	private void dibujarCajaCarton(float delta) {
		int length = oWorld.arrCajacarton.size;
		
		for(int i = 0; i < length;i++){
			CajaCarton oCaj = oWorld.arrCajacarton.get(i);
			TextureRegion keyframe;

			//true es que la aniimacion se repeteria muchas veces
			keyframe = Assets.cajacarton;

		    batcher.draw(keyframe, oCaj.posicion.x - .55f, oCaj.posicion.y -.37f, 1.11f , .74f );
		}
	}

	private void dibujarBoos(float delta) {
		int length = oWorld.arrBoos.size;
		
		for(int i = 0; i < length;i++){
			Boos oBoos= oWorld.arrBoos.get(i);
			TextureRegion keyframe;

			//true es que la aniimacion se repeteria muchas veces
			keyframe = Assets.btnEmpezar;

		    batcher.draw(keyframe, oBoos.posicion.x - .55f, oBoos.posicion.y -.37f, 1.11f , .74f );
		}
	}
	private void dibujarLata(float delta) {
int length = oWorld.arrLata.size;
		
		for(int i = 0; i < length;i++){
			Lata oLat = oWorld.arrLata.get(i);
			TextureRegion keyframe;

			//true es que la aniimacion se repeteria muchas veces
			keyframe = Assets.lata1;

		    batcher.draw(keyframe, oLat.posicion.x - .27f, oLat.posicion.y -.12f, .54f , .23f );
		}
	}

	private void dibujarBote(float delta) {
		int length = oWorld.arrBoteBasura.size;
		
		for(int i = 0; i < length;i++){
			BoteBasura oBot = oWorld.arrBoteBasura.get(i);
			TextureRegion keyframe;

			//true es que la aniimacion se repeteria muchas veces
			keyframe = Assets.botebasura;

		    batcher.draw(keyframe, oBot.posicion.x - .65f, oBot.posicion.y -.54f, 1.33f , 1.09f );
		}
	}

	private void dibujarBarandal(float delta) {
int length = oWorld.arrBardanl.size;
		
		for(int i = 0; i < length;i++){
			Barandal oBar = oWorld.arrBardanl.get(i);
			TextureRegion keyframe;

			//true es que la aniimacion se repeteria muchas veces
			keyframe = Assets.Barandal;

		    batcher.draw(keyframe, oBar.posicion.x - 4.01f, oBar.posicion.y -.91f, 8.02f , 1.83f );
		}
	}

	private void dibujarEdificio(float delta) {
int length = oWorld.arrEdificio.size;
		
		for(int i = 0; i < length;i++){
			Edificio oEdi = oWorld.arrEdificio.get(i);
			TextureRegion keyframe;

			//true es que la aniimacion se repeteria muchas veces
			keyframe = Assets.edificio2;

		    batcher.draw(keyframe, oEdi.posicion.x - 1.4f, oEdi.posicion.y -2.25f, 3.8f , 4.52f );
		}
	}

	private void dibujarCloaca(float delta) {
		int length = oWorld.arrReji.size;
		
		for(int i = 0; i < length;i++){
			Rejilla oReji = oWorld.arrReji.get(i);
			TextureRegion keyframe;

			//true es que la aniimacion se repeteria muchas veces
			keyframe = Assets.Rejilla1;

		    batcher.draw(keyframe, oReji.posicion.x - .51f, oReji.posicion.y -.51f, 1.02f , 1.02f );
		}
	}

	private void dibujarPasaje(float delta) {
		int length = oWorld.arrPAseje.size;
		
		for(int i = 0; i < length;i++){
			Pasaje oPas = oWorld.arrPAseje.get(i);
			TextureRegion keyframe;

			//true es que la aniimacion se repeteria muchas veces
			keyframe = Assets.Pasaje;

		    batcher.draw(keyframe, oPas.posicion.x - 1.37f, oPas.posicion.y -1.185f, 2.74f , 2.37f );
		}
	}

	private void dibujarFoco(float delta) {
		int length = oWorld.arrFocos.size;
		
		for(int i = 0; i < length;i++){
			Foco oFoco = oWorld.arrFocos.get(i);
			TextureRegion keyframe;

			//true es que la aniimacion se repeteria muchas veces
			keyframe = Assets.Foco;

		    batcher.draw(keyframe, oFoco.posicion.x - 0.205f, oFoco.posicion.y -.47f, .41f , .94f );
		}
	}

	private void dibujarTuberiaLarga(float delta) {
		int length = oWorld.arrTuberLargas.size;
		
		for(int i = 0; i < length;i++){
			TuberiaLarga oTub = oWorld.arrTuberLargas.get(i);
			TextureRegion keyframe;

			//true es que la aniimacion se repeteria muchas veces
			keyframe = Assets.Tuberialarga;

		    batcher.draw(keyframe, oTub.posicion.x - 0.30f, oTub.posicion.y -2.46f, .60f , 4.92f );
		}
	}

	private void dibujarTuberia(float delta) {
		int length = oWorld.arrTuberias.size;
		
		for(int i = 0; i < length;i++){
			Tuberia oTub = oWorld.arrTuberias.get(i);
			TextureRegion keyframe;

			//true es que la aniimacion se repeteria muchas veces
			keyframe = Assets.Tuberia;

		    batcher.draw(keyframe, oTub.posicion.x - 1.26f, oTub.posicion.y -2.54f, 2.52f , 5.08f );
		}
	}

	private void dibujarPiso(float delta) {
		int length = oWorld.arrPiso.size;
		
		for(int i = 0; i < length;i++){
			Piso oPais = oWorld.arrPiso.get(i);
			TextureRegion keyframe;

			//true es que la aniimacion se repeteria muchas veces
			keyframe = Assets.pisotierra;

		    batcher.draw(keyframe, oPais.posicion.x - 4f, oPais.posicion.y -.7f, 8f , 1.58f );
		}
	}

	private void dibujarPlataforma(float delta) {
		int length = oWorld.arrPlataforma.size;
		
		for(int i = 0; i < length;i++){
			Plataforma oPla = oWorld.arrPlataforma.get(i);
			TextureRegion keyframe;

			//true es que la aniimacion se repeteria muchas veces
			keyframe = Assets.Plataforma;

		    batcher.draw(keyframe, oPla.posicion.x - 0.4f, oPla.posicion.y - 0.1f, 0.8f , .2f );
		}
	}

	private void dibujarPandilla(float delta) {
		
		TextureRegion keyframe = Assets.Panadilla.getKeyFrame(oWorld.oPan.state_time,true);		
		
		batcher.draw(keyframe, oWorld.oPan.posicion.x - 1f, oWorld.oPan.posicion.y -0.5f, 2f,1f);
		
	}

	private void dibujarGato(float delta) {
TextureRegion keyframe;
		
		
		if(oWorld.OGato.state == Gato.State.saltando)
		{
			keyframe = Assets.Kuro.getKeyFrame(oWorld.OGato.statetime, true);
		}
		else if (oWorld.OGato.state == Gato.State.cayendo)
		{
			keyframe = Assets.Kuro.getKeyFrame(oWorld.OGato.statetime, true);
		}
		else if(oWorld.OGato.state ==Gato.State.muerto)
		{
			keyframe = Assets.Kuro.getKeyFrame(oWorld.OGato.statetime, true);
		}
		else
			keyframe = Assets.Kuro.getKeyFrame(oWorld.OGato.statetime, true);
		
		
				batcher.draw(keyframe, oWorld.OGato.position.x - 0.35f , oWorld.OGato.position.y-0.35f, +.7f, .7f);
	
	}

	private void dibujarMoneda(float delta) {
		int length = oWorld.arrMonedas.size;
		
		for(int i = 0; i < length;i++){
			Monedas oMon = oWorld.arrMonedas.get(i);
			TextureRegion keyframe;

			//true es que la aniimacion se repeteria muchas veces
			keyframe = Assets.Moneda;

		    batcher.draw(keyframe, oMon.posicion.x - 0.2f, oMon.posicion.y - 0.2f, 0.4f , .4f );
		}
	}

	private void dibujarRejillas(float delta) {
		int length = oWorld.arrRejillas.size;
		
		for(int i = 0; i < length;i++){
			Rejillas oReji = oWorld.arrRejillas.get(i);
			TextureRegion keyframe;

			//true es que la aniimacion se repeteria muchas veces
			keyframe = Assets.Rejillas;

		    batcher.draw(keyframe, oReji.posicion.x - 0.75f, oReji.posicion.y -.25f, 1.5f , 0.5f );
		}
	}

	private void dibujarPoste(float delta) {
		int length = oWorld.arrPoste.size;
		
		for(int i = 0; i < length;i++){
			Poste oPos = oWorld.arrPoste.get(i);
			TextureRegion keyframe;

			//true es que la aniimacion se repeteria muchas veces
			keyframe = Assets.postemadera;

		    batcher.draw(keyframe, oPos.posicion.x- .41f , oPos.posicion.y -1.81f, 0.83f , 3.62f );
		}
	}

	private void dibujarCerro(float delta) {
		int length = oWorld.arrCerros.size;
		
		for(int i = 0; i < length;i++){
			Cerros oCerro = oWorld.arrCerros.get(i);
			TextureRegion keyframe;

			//true es que la aniimacion se repeteria muchas veces
			keyframe = Assets.Cerro;

		    batcher.draw(keyframe, oCerro.posicion.x - 1.3f, oCerro.posicion.y - 1.35f, 2.6f , 2.7f );
		}
	}

	private void dibujarPaisaje(float delta) {
		int length = oWorld.arrPaisaje.size;
		
		for(int i = 0; i < length;i++){
			Paisaje oPais = oWorld.arrPaisaje.get(i);
			TextureRegion keyframe;

			//true es que la aniimacion se repeteria muchas veces
			keyframe = Assets.paisaje;

		    batcher.draw(keyframe, oPais.posicion.x - 4f, oPais.posicion.y -1.15f, 8f , 2.3f );
		}		
	}

	private void dibujarNubes(float delta) {
		int length = oWorld.arrNubes.size;
		
		for(int i = 0; i < length;i++){
			Nubes oNube = oWorld.arrNubes.get(i);
			TextureRegion keyframe;

			//true es que la aniimacion se repeteria muchas veces
			keyframe = Assets.Nubes;

		    batcher.draw(keyframe, oNube.posicion.x - 3.8f, oNube.posicion.y - 0.55f, 7.6f , 1.3f );
		}
	}

	private void renderbackground(float delta) {
		Assets.parallaxFondo.render(delta);
	}

}
