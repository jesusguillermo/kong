package com.me.mygdxgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Vector2;
import com.me.parallax.ParallaxBackground;
import com.me.parallax.ParallaxLayer;
import com.sun.java.swing.plaf.motif.resources.motif;

public class Assets {
	
	public static BitmapFont Font;

	public static AtlasRegion btnEmpezar;
	public static AtlasRegion Nubes;
	public static AtlasRegion Poste;
	public static AtlasRegion Rejillas;
	public static AtlasRegion paisaje;
	public static AtlasRegion Plataforma;
	public static AtlasRegion Moneda;
	public static AtlasRegion Cerro;
	public static AtlasRegion PisoAlcantarilla;	
	
	//-----------------------------------
	public static AtlasRegion Pasaje;
	public static AtlasRegion Tuberia;
	public static AtlasRegion Tuberialarga;
	public static AtlasRegion Rejilla1;
	public static AtlasRegion Rejilla2;
	public static AtlasRegion Foco;		
	
	//-----------------------------------
	//Escenario CAllejon
	public static AtlasRegion Barandal;
	public static AtlasRegion botebasura;
	public static AtlasRegion cajacarton;
	public static AtlasRegion edificio1;
	public static AtlasRegion edificio2;
	public static AtlasRegion lata1;
	public static AtlasRegion lata2;
	public static AtlasRegion lata3;
	public static AtlasRegion lata4;
	public static AtlasRegion nube1;
	public static AtlasRegion nube2;
	public static AtlasRegion nube3;
	public static AtlasRegion nube4;
	public static AtlasRegion nube5;
	public static AtlasRegion nube6;
	public static AtlasRegion nube7;
	public static AtlasRegion pisocemento;
	public static AtlasRegion pisotierra;
	public static AtlasRegion postecemento;
	public static AtlasRegion postemadera;
	
	//-----------------------------------
	
	public static AtlasRegion Fondo;
	
	public static Animation Peleando;
	public static Animation Panadilla;
	public static Animation Kuro;	

	public static ParallaxBackground parallaxFondo;
	
	public static void Cargar()
	{
		Font = new BitmapFont();
		
		TextureAtlas atlas = new TextureAtlas(
				Gdx.files.internal("data/kurito.txt"));		

		AtlasRegion K1 = atlas.findRegion("1");
		AtlasRegion K2 = atlas.findRegion("2");
		AtlasRegion K3 = atlas.findRegion("3");
		AtlasRegion K4 = atlas.findRegion("4");
		AtlasRegion K5 = atlas.findRegion("5");
		
		Kuro = new Animation(0.20f, K1,K2,K3,K4,K5);
		
		AtlasRegion P1 = atlas.findRegion("a");
		AtlasRegion P2 = atlas.findRegion("b");
		AtlasRegion P3 = atlas.findRegion("c");
		AtlasRegion P4 = atlas.findRegion("d");
		AtlasRegion P5 = atlas.findRegion("e");
		
		Panadilla = new Animation(0.20f, P1,P2,P3,P4,P5);
		
		AtlasRegion B1 = atlas.findRegion("peleas");
		AtlasRegion B2 = atlas.findRegion("peleas1");
		AtlasRegion B3 = atlas.findRegion("peleas2");
		AtlasRegion B4 = atlas.findRegion("peleas3");
		
		Peleando = new Animation(0.10f, B1,B2,B3,B4);
		
		PisoAlcantarilla = atlas.findRegion("pisoalcantarilla");
		btnEmpezar = atlas.findRegion("btnEmpezar");
		Nubes = atlas.findRegion("nubes");
		Poste = atlas.findRegion("poste");
		Rejillas = atlas.findRegion("rejillas");
		paisaje = atlas.findRegion("paisaje");
		Plataforma = atlas.findRegion("plataforma");
		Moneda = atlas.findRegion("siguiente");
		Cerro = atlas.findRegion("cerros");
		Fondo = atlas.findRegion("cielos");

		//-----------------------------------
		Pasaje = atlas.findRegion("pasaje");
		Tuberia = atlas.findRegion("tuberia");
		Tuberialarga = atlas.findRegion("tuberialarga");
		Rejilla1 = atlas.findRegion("cloaca");
		Rejilla2 = atlas.findRegion("cloaca2");
		Foco = atlas.findRegion("foco");
		//-----------------------------------
		Barandal = atlas.findRegion("barandal");
		botebasura = atlas.findRegion("botebasura");
		cajacarton = atlas.findRegion("cajacarton");
		edificio1 = atlas.findRegion("edificio1");
		edificio2 = atlas.findRegion("edificio2");
		lata1 = atlas.findRegion("lata1");
		lata2 = atlas.findRegion("lata2");
		lata3 = atlas.findRegion("lata3");
		lata4 = atlas.findRegion("lata4");
		nube1 = atlas.findRegion("nube1");
		nube2 = atlas.findRegion("nube2");
		nube3 = atlas.findRegion("nube3");
		nube4 = atlas.findRegion("nube4");
		nube5 = atlas.findRegion("nube5");
		nube6 = atlas.findRegion("nube6");
		nube7 = atlas.findRegion("nube7");
		pisocemento = atlas.findRegion("pisocemento");
		pisotierra = atlas.findRegion("pisoparallax");
		postecemento = atlas.findRegion("postecemento");
		postemadera = atlas.findRegion("postemadera");
		
		//-----------------------------------
		ParallaxLayer floor = new ParallaxLayer(atlas.findRegion("pisotierra"), new Vector2(24, 0), new Vector2(0, 0), new Vector2(-1, 700), 874, 96);
		ParallaxLayer as[] = new ParallaxLayer[] { floor };

		parallaxFondo = new ParallaxBackground(as, 800, 480, new Vector2(10, 0));

		
	}
	
}
