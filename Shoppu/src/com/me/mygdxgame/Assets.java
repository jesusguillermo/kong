package com.me.mygdxgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.spine.SkeletonData;
import com.esotericsoftware.spine.SkeletonJson;
import com.me.parallax.ParallaxBackground;
import com.me.parallax.ParallaxLayer;
import com.sun.java.swing.plaf.motif.resources.motif;

public class Assets {
	
	public static com.esotericsoftware.spine.Animation aniRun;
	public static com.esotericsoftware.spine.Animation aniJump;
	public static com.esotericsoftware.spine.Animation aniFall ;
	public static com.esotericsoftware.spine.Animation aniFly ;
	public static com.esotericsoftware.spine.Animation anicrash ;
	public static SkeletonData skelGatoData;
	
	public static BitmapFont Font;
	
	//----------------------Numeros
	public static AtlasRegion cero;
	public static AtlasRegion uno;
	public static AtlasRegion dos;
	public static AtlasRegion tres;
	public static AtlasRegion cuatro;
	public static AtlasRegion cinco;
	public static AtlasRegion seis;
	public static AtlasRegion siete;
	public static AtlasRegion ocho;
	public static AtlasRegion nueve;
	public static AtlasRegion m;
	//----------------------Numeros
	public static AtlasRegion volando;

	public static AtlasRegion pelusa;
	public static AtlasRegion Titulo;
	public static AtlasRegion btnLeaderBoard;
	public static AtlasRegion btnEmpezar;
	public static AtlasRegion btntyagain;
	public static AtlasRegion fondogameover;
	public static AtlasRegion botegameover;
	public static AtlasRegion Nubes;
	public static AtlasRegion Rejillas;
	public static AtlasRegion paisaje;
	public static AtlasRegion Plataforma;
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
	public static AtlasRegion edificio3;
	public static AtlasRegion edificio4;
	public static AtlasRegion edificio5;
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
	public static AtlasRegion posteluz;
	public static AtlasRegion postecat;
	public static AtlasRegion postecatgrumpy;
	public static AtlasRegion postecuervo;
	public static AtlasRegion Jet;
	
	//-----------------------------------
	public static AtlasRegion vida;
	public static AtlasRegion vidavacia;
	//-----------------------------------
	
	public static AtlasRegion Fondo;
	public static AtlasRegion kuro_run;
	
	public static Animation Peleando;
	public static Animation Panadilla;
	public static Animation Kuro;	
	public static Animation Pescadito;
	public static Animation moneda;
	public static Animation sergio;

	public static ParallaxBackground parallaxFondo;
	public static ParallaxBackground parallaxMPFondo;
	
	//-------------------------------Sonidos
	
	public static Sound sSaltar;
	public static Sound fFly;
	public static Sound oMoneda;
	public static Sound oGato;
	public static Sound oClick;
	public static Sound oCuervo;
	public static Sound oCaja;
	public static Sound oBote;
	public static Sound oPium;
	public static Sound oListen;
	//Para la musica
	
	public static Music Musica;
	public static Music MusicaTime;
	
	//--------------------------------------
	public static void Cargar()
	{
		Font = new BitmapFont();
		
		TextureAtlas atlas = new TextureAtlas(
				Gdx.files.internal("data/kurito.txt"));
		
		TextureAtlas atlas2 = new TextureAtlas(
				Gdx.files.internal("data/kurito2.txt"));	
		
		
		 SkeletonJson jason = new SkeletonJson(atlas);
		 //En la escala se multiplica 40 por 0.01 para que dibuje a mi gato a las medidas del) mundo 4.8
		 jason.setScale(0.007f);
		 skelGatoData = jason.readSkeletonData(Gdx.files.internal("data/skeleton.json"));
		 aniRun= skelGatoData.findAnimation("run");
		 aniJump = skelGatoData.findAnimation("jump");
		 aniFall = skelGatoData.findAnimation("fall");
		 aniFly = skelGatoData.findAnimation("fly");
		 anicrash = skelGatoData.findAnimation("crash");
		 
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
		
		AtlasRegion F1 = atlas.findRegion("fish");
		AtlasRegion F2 = atlas.findRegion("1fish");
		
		Pescadito = new Animation(0.30f, F1,F2);
		
		AtlasRegion C1 = atlas.findRegion("ave");
		AtlasRegion C2 = atlas.findRegion("ave1");
		
		sergio = new Animation(0.1f,C1,C2);
		
		AtlasRegion mo1 = atlas.findRegion("mon1");
		AtlasRegion mo2 = atlas.findRegion("mon2");
		AtlasRegion mo3 = atlas.findRegion("mon3");
		AtlasRegion mo4 = atlas.findRegion("mon4");
		AtlasRegion mo5 = atlas.findRegion("mon5");
		AtlasRegion mo6 = atlas.findRegion("mon6");
		AtlasRegion mo7 = atlas.findRegion("mon7");
		AtlasRegion mo8 = atlas.findRegion("mon8");
		AtlasRegion mo9 = atlas.findRegion("mon9");
		AtlasRegion mo10 = atlas.findRegion("mon10");
		
		moneda = new Animation(0.05f, mo1,mo2,mo3,mo4,mo5,mo6,mo7,mo8,mo9,mo10);
		
		Jet = atlas.findRegion("listen");
		

		cero = atlas2.findRegion("cero");
		uno = atlas2.findRegion("uno");
		dos = atlas2.findRegion("dos");
		tres = atlas2.findRegion("tres");
		cuatro = atlas2.findRegion("cuatro");
		cinco = atlas2.findRegion("cinco");
		seis = atlas2.findRegion("seis");
		siete = atlas2.findRegion("siete");
		ocho = atlas2.findRegion("ocho");
		nueve = atlas2.findRegion("nueve");	
		m = atlas2.findRegion("m");
		

		volando = atlas.findRegion("volando");
		
		Titulo = atlas2.findRegion("titulo");
		pelusa = atlas.findRegion("pelusa");
		posteluz = atlas.findRegion("postemaderasolo");
		postecat = atlas.findRegion("postemadera");
		postecatgrumpy = atlas.findRegion("postemaderagrump");
		postecuervo = atlas.findRegion("postemaderacuervo");
		kuro_run = atlas.findRegion("kurofood");
		PisoAlcantarilla = atlas.findRegion("pisoalcantarilla");
		btnEmpezar = atlas.findRegion("btnEmpezar");
		btnLeaderBoard = atlas.findRegion("botonleaderboard");
		btntyagain = atlas.findRegion("botonbote");
		fondogameover = atlas.findRegion("fondogameover");
		botegameover = atlas.findRegion("botebasuragameover");
		Nubes = atlas.findRegion("nubes");
		Rejillas = atlas.findRegion("rejillas");
		paisaje = atlas.findRegion("paisaje");
		Plataforma = atlas.findRegion("plataforma1");
		Fondo = atlas2.findRegion("fondo");

		//-----------------------------------
		Barandal = atlas.findRegion("barandal");
		botebasura = atlas.findRegion("botebasura");
		cajacarton = atlas.findRegion("cajacarton");
		edificio1 = atlas.findRegion("edificio2sn");
		edificio2 = atlas.findRegion("edificio3sn");
		edificio3 = atlas.findRegion("edificio4sn");
		edificio4 = atlas.findRegion("edificio5sn");
		edificio5 = atlas.findRegion("edificio6sn");
		
		//-----------------------------------
		vida = atlas.findRegion("heart");
		vidavacia = atlas.findRegion("heartvacio");
		//-----------------------------------
		lata1 = atlas.findRegion("lata1");
		lata2 = atlas.findRegion("lata2");
		lata3 = atlas.findRegion("lata3");
		lata4 = atlas.findRegion("lata4");
		pisocemento = atlas.findRegion("pisocemento");
		pisotierra = atlas.findRegion("pisoparalax");
		postecemento = atlas.findRegion("postecemento");
		
		//-----------------------------------
		ParallaxLayer fondoparalax = new ParallaxLayer(atlas2.findRegion("fondo"), new Vector2(3, 0), new Vector2(0, 100), new Vector2(-1, 700), 801, 480);
		ParallaxLayer edifBlanco = new ParallaxLayer(atlas.findRegion("fondoedificioazulmar"), new Vector2(3, 0), new Vector2(0, 100), new Vector2(-1, 700), 968, 348);
		ParallaxLayer edifNegro = new ParallaxLayer(atlas.findRegion("fondoedificioazul"), new Vector2(4, 0), new Vector2(0, 0), new Vector2(-3, 700), 1918, 464);
		
		ParallaxLayer as[] = new ParallaxLayer[] {edifBlanco,edifNegro};
		
		parallaxFondo = new ParallaxBackground(as,800, 480, new Vector2(10, 0));	
		
		//--------------------------------Menu Principal
		ParallaxLayer edifazul = new ParallaxLayer(atlas.findRegion("fondoedificioazulmar"), new Vector2(3, 0), new Vector2(0, 100), new Vector2(-1, 700), 968, 348);
		ParallaxLayer edifazulmar = new ParallaxLayer(atlas.findRegion("fondoedificioazul"), new Vector2(4, 0), new Vector2(0, 0), new Vector2(-3, 700), 1918, 464);
		ParallaxLayer pisoparallax = new ParallaxLayer(atlas.findRegion("pisoparalax"), new Vector2(4, 0), new Vector2(0, 0), new Vector2(-3, 700), 801, 218);
		
		ParallaxLayer mp[] = new ParallaxLayer[] {edifazul,edifazulmar,pisoparallax};
		
		parallaxMPFondo = new ParallaxBackground(mp,800, 480, new Vector2(10, 0));	
		
		sSaltar = Gdx.audio.newSound(Gdx.files.internal( "data/Sonidos/jump.mp3"));
		oMoneda = Gdx.audio.newSound(Gdx.files.internal( "data/Sonidos/coin.mp3"));
		oGato = Gdx.audio.newSound(Gdx.files.internal( "data/Sonidos/meow.mp3"));
		fFly = Gdx.audio.newSound(Gdx.files.internal("data/Sonidos/volar.wav"));
		oClick = Gdx.audio.newSound(Gdx.files.internal("data/Sonidos/boton.mp3"));
		oCuervo = Gdx.audio.newSound(Gdx.files.internal("data/Sonidos/cuervo.mp3"));
		oCaja = Gdx.audio.newSound(Gdx.files.internal("data/Sonidos/golpecarton.mp3"));
		oBote = Gdx.audio.newSound(Gdx.files.internal("data/Sonidos/golpebote.mp3"));
		oPium = Gdx.audio.newSound(Gdx.files.internal("data/Sonidos/piumpium.mp3"));
		oListen = Gdx.audio.newSound(Gdx.files.internal("data/Sonidos/listen.mp3"));
		
		Musica = Gdx.audio.newMusic(Gdx.files.internal("data/Sonidos/GameOn.mp3"));
		MusicaTime = Gdx.audio.newMusic(Gdx.files.internal("data/Sonidos/NoReturn.mp3"));
		MusicaTime.setLooping(true);
		Musica.setLooping(true);
		Musica.play();
		
	}
	
}
