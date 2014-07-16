package com.me.game;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.utils.Array;
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
import com.me.objetos.Gato.State;
import com.me.screens.Screens;

public class WorldGame {

	final float WIDTH = Screens.WORLD_WIDTH;
	final float HEIGHT = Screens.WORLD_HEIGHT;


	// ----------------------------------
	public enum State {
		Running, GameOver
	}

	
	State state;
	Gato OGato;
	Pandilla oPan;
	World oWorldBox;
	Array<Body> arrBodies;
	Array<Jet> arrJet;
	Array<Nubes> arrNubes;
	Array<Monedas> arrMonedas;
	Array<Plataforma> arrPlataforma;
	Array<Boos> arrBoos;
	// _---------------------------
	// ----------------------------
	Array<Barandal> arrBardanl;
	Array<BoteBasura> arrBoteBasura;
	Array<CajaCarton> arrCajacarton;
	Array<Edificio> arrEdificio;
	Array<Lata> arrLata;
	Array<Piso> arrPiso;
	Array<Poste> arrPoste;
	Array<Cuervo> arrCuervo;
	// ----------------------------
	// ----------------------------

	Random Oran;
	int monedas,boos, control;
	public boolean creacion;
	public int distancia;
	public float time = 60;
	public float timer ;
	public float plata_eliminadas=0;

	public WorldGame() {
		state = State.Running;
		// ---------------------------
		arrBardanl = new Array<Barandal>();
		arrBoteBasura = new Array<BoteBasura>();
		arrCajacarton = new Array<CajaCarton>();
		arrEdificio = new Array<Edificio>();
		arrLata = new Array<Lata>();
		arrPiso = new Array<Piso>();
		arrPoste = new Array<Poste>();
		arrJet = new Array<Jet>();
		// ---------------------------
		arrBodies = new Array<Body>();
		arrNubes = new Array<Nubes>();
		arrMonedas = new Array<Monedas>();
		arrPoste = new Array<Poste>();
		arrBoos = new Array<Boos>();
		arrPlataforma = new Array<Plataforma>();
		arrCuervo = new Array<Cuervo>();
		Oran = new Random();
		oWorldBox = new World(new Vector2(0, -10f), true);
		// //
		oWorldBox.setContactListener(new Colisiones());
		// /

		crearGato();
		crearTecho(10);
		CrearPisoTierra(3);
		crearPandilla();
		agregarPlataformas(0, 20);

	}	
	
	private void crearTecho(float x) {

		BodyDef tec = new BodyDef();
		tec.position.x = 0;
		tec.position.y = x;

		tec.type = BodyType.KinematicBody;
		Body oBody = oWorldBox.createBody(tec);

		EdgeShape shape = new EdgeShape();
		shape.set(0, 0, WIDTH*11, 0);

		FixtureDef fixture = new FixtureDef();
		fixture.shape = shape;
		fixture.density = 0f;
		fixture.restitution = 0;
		fixture.friction = 0;

		oBody.createFixture(fixture);

		oBody.setFixedRotation(true);
		oBody.setUserData("techo");
		shape.dispose();

	}
	public int ponsincial ;
	public int limite_i;
	
	private void  agregarPlataformas(int in ,int limi)
	{
		for(int i = in ; i<limi ;i++)
		{
			crearMonedas(i);
		    CrearPisoTierra(i*8f);
		    crearBooster(i*12);
		    crearEdficio(i*9);
		    crearPoste(i*7.9f);
		    crearBoteBasura(i*9, .6f);
		    crearCajaCarton(i*10, .42f);
		    crearLata(i*7);
		    crearNubes(i*7);
		    crearCuervo(i*1.5f);
		    if(Oran.nextInt(11)<5)
		    {
		    	if(Oran.nextInt(11)<5)
			    {
			    	if(Oran.nextInt(11)<5)
				    {

				    	crearPlataforma(i*4.1f, 3, true,false);	
				    }
			    	else
			    		crearPlataforma(i*4.1f, 3, true,true);
			    }
		    	crearPlataforma(i*4.1f, 1, true,false);
		    	if(Oran.nextInt(11)<5)
		    	{
		    		if(Oran.nextInt(11)<5)
				    {
		    			crearBoteBasura(i*4.1f, 1.5f); 
				    }
		    		else
		    		   crearCajaCarton(i*4.1f,1.4f);
			    }
		    }
		    else
		    {

		    	if(Oran.nextInt(11)<5)
			    {

			    	if(Oran.nextInt(11)<5)
				    {

				    	crearPlataforma(i*4.1f, 3, true,false);	
				    }
			    	else
			    		crearPlataforma(i*4.1f, 3, true,true);
			    }
		    	
		    	crearPlataforma(i*4.1f, 2, true,false);
		    	if(Oran.nextInt(11)<5)
			    {
		    		if(Oran.nextInt(11)<5)
		    		{
		    	       crearBoteBasura(i*4.1f, 2.5f); 
		    		}
		    		else
		    		   crearCajaCarton(i*4.1f,2.4f);
			    }
		    }
		}
    }

	private void crearCuervo(float x) {
		//float x = WIDTH + 3;
		float alt = Oran.nextInt(10);
		if(alt < 5)
		{
			alt = 5;
		}
				float y = Oran.nextFloat() + alt;

				Cuervo ocue = new Cuervo(x, y);

				arrCuervo.add(ocue);

				BodyDef bd = new BodyDef();
				bd.type = BodyType.KinematicBody;
				bd.position.x = ocue.posicion.x;
				bd.position.y = ocue.posicion.y;

				Body oBody = oWorldBox.createBody(bd);

				PolygonShape shape = new PolygonShape();
				shape.setAsBox(.445f, .215f);

				FixtureDef fixDef = new FixtureDef();
				fixDef.shape = shape;
				// para que cuando choque no lo tome como otra plataforma e impulse a
				// nuestro personaje
				// que la moneda no choque con nada pero aun asi reciba eventos de
				// colisiones
				fixDef.isSensor = true;

				oBody.createFixture(fixDef);

				oBody.setUserData(ocue);
	}

	private void crearJet(float x,float y) {

		Jet ojet = new Jet(x, y);

		arrJet.add(ojet);

		BodyDef bd = new BodyDef();
		bd.type = BodyType.KinematicBody;
		bd.position.x = ojet.posicion.x;
		bd.position.y = ojet.posicion.y;

		Body oBody = oWorldBox.createBody(bd);

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(.15f, .18f);

		FixtureDef fixDef = new FixtureDef();
		fixDef.shape = shape;
		// para que cuando choque no lo tome como otra plataforma e impulse a
		// nuestro personaje
		// que la moneda no choque con nada pero aun asi reciba eventos de
		// colisiones
		fixDef.isSensor = true;

		oBody.createFixture(fixDef);

		oBody.setUserData(ojet);
		
	}


	private void CrearPisoTierra(float x) {
		//float x = WIDTH + 4.5f;
		float y = 0.2f;

		Piso oPis = new Piso(x, y);

		arrPiso.add(oPis);

		BodyDef bd = new BodyDef();
		bd.type = BodyType.StaticBody;
		bd.position.x = oPis.posicion.x;
		bd.position.y = oPis.posicion.y;

		Body oBody = oWorldBox.createBody(bd);

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(4f, 0f);

		FixtureDef fixDef = new FixtureDef();
		fixDef.shape = shape;

		oBody.createFixture(fixDef);

		oBody.setUserData(oPis);
	}

	private void crearLata(float x) {
		//float x = WIDTH + 1f;
		float y = .3f;

		Lata oLat = new Lata(x, y);

		arrLata.add(oLat);

		BodyDef bd = new BodyDef();
		bd.type = BodyType.KinematicBody;
		bd.position.x = oLat.posicion.x;
		bd.position.y = oLat.posicion.y;

		Body oBody = oWorldBox.createBody(bd);

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(.27f, .12f);

		FixtureDef fixDef = new FixtureDef();
		
		fixDef.shape = shape;
		fixDef.isSensor = true;
	
		oBody.createFixture(fixDef);

		oBody.setUserData(oLat);

	}

	private void crearEdficio(float x) {
		//float x = WIDTH + 3;
		float y = 3.2f;

		Edificio oEd = new Edificio(x, y);

		arrEdificio.add(oEd);

		BodyDef bd = new BodyDef();
		bd.type = BodyType.KinematicBody;
		bd.position.x = oEd.posicion.x;
		bd.position.y = oEd.posicion.y;

		Body oBody = oWorldBox.createBody(bd);

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(1.4f, 1.81f);

		FixtureDef fixDef = new FixtureDef();
		fixDef.shape = shape;
		fixDef.isSensor = true;

		oBody.createFixture(fixDef);

		oBody.setUserData(oEd);

	}

	private void crearCajaCarton(float x,float y) {

		//float x = WIDTH + 3f;
		//float y = .42f;

		CajaCarton oCaj = new CajaCarton(x, y);

		arrCajacarton.add(oCaj);

		BodyDef bd = new BodyDef();
		bd.type = BodyType.KinematicBody;
		bd.position.x = oCaj.posicion.x;
		bd.position.y = oCaj.posicion.y;

		Body oBody = oWorldBox.createBody(bd);

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(0.4f, .2f);

		FixtureDef fixDef = new FixtureDef();
		fixDef.shape = shape;
		//fixDef.isSensor = true;

		oBody.createFixture(fixDef);

		oBody.setUserData(oCaj);
	}

	private void crearBoteBasura(float x,float y) {
		//float x = WIDTH + 3f;
		//y = .6f;

		BoteBasura oBot = new BoteBasura(x, y);

		arrBoteBasura.add(oBot);

		BodyDef bd = new BodyDef();
		bd.type = BodyType.KinematicBody;
		bd.position.x = oBot.posicion.x;
		bd.position.y = oBot.posicion.y;

		Body oBody = oWorldBox.createBody(bd);

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(.2f, .3f);

		FixtureDef fixDef = new FixtureDef();
		fixDef.shape = shape;
		//fixDef.isSensor = true;

		oBody.createFixture(fixDef);

		oBody.setUserData(oBot);
	}

	private void crearPlataforma(float x, float y, boolean estado,boolean jet) {
		
		Plataforma oPlata = new Plataforma(x, y, estado);
		BodyDef bd = new BodyDef();
		bd.type = BodyType.KinematicBody;
		bd.position.x = x;
		bd.position.y = y;

		Body oBody = oWorldBox.createBody(bd);

		PolygonShape shape = new PolygonShape();
		//shape.setAsBox(.7f, .1f);
		shape.setAsBox(1.3f, .15f);

		FixtureDef fixDef = new FixtureDef();
		fixDef.shape = shape;
		fixDef.friction = 0;

		oBody.createFixture(fixDef);
		oBody.setUserData(oPlata);
		arrPlataforma.add(oPlata);
		if(jet)			
		{
			crearJet(x, y+0.5f);
		}
	}

	private void crearPiso(float y) {
		BodyDef bd = new BodyDef();
		bd.position.x = 0;
		bd.position.y = y;

		bd.type = BodyType.StaticBody;
		Body oBody = oWorldBox.createBody(bd);

		EdgeShape shape = new EdgeShape();
		shape.set(0, 0, WIDTH, 0);

		FixtureDef fixture = new FixtureDef();
		fixture.shape = shape;
		fixture.density = 0f;
		fixture.restitution = 0f;
		fixture.friction = 0f;

		oBody.createFixture(fixture);

		oBody.setFixedRotation(true);

		oBody.setUserData("pared");
		shape.dispose();
	}

	private void crearGato() 
	{
		OGato = new Gato(Screens.WORLD_WIDTH / 2, Screens.WORLD_HEIGHT / 2);

		// para declarar el cuerpo necesitamos una definicion
		BodyDef bd = new BodyDef();
		bd.position.x = OGato.position.x;
		bd.position.y = OGato.position.y;

		bd.type = BodyType.DynamicBody;

		// creamos el cuerpo
		Body oBody = oWorldBox.createBody(bd);

		//////////////////////////////////////////
		// haremos un rectangulo
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(.22f , .25f );
		
		// necestamos una fixture
		FixtureDef fixture = new FixtureDef();
		fixture.shape = shape;
		oBody.createFixture(fixture);
	
		oBody.createFixture(fixture).setUserData("cuerpo");
		////////////////////////////////
		//rectangulo para los pies
		PolygonShape shapePies = new PolygonShape();
		shapePies.setAsBox(.22f,.1f,new Vector2(0,-.19f) , 0);
		
		fixture.shape = shapePies;
		fixture.isSensor=true;
	  
		oBody.createFixture(fixture).setUserData("pies");
		
		/////////////////////////////////////////
		PolygonShape shapeFrente = new PolygonShape();
		shapeFrente.setAsBox(.1f,.22f,new Vector2(.19f,0) , 0);
		
		fixture.shape = shapeFrente;
		fixture.isSensor=true;
	  
		oBody.createFixture(fixture).setUserData("frente");
		
		
		// para que no rote el cuadro
		oBody.setFixedRotation(true);
		oBody.setUserData(OGato);
	}

	
	private void crearPandilla() {

		float x = 1;
		float y = .4f;

		oPan = new Pandilla(x, y, 0);
		BodyDef bdp = new BodyDef();
		bdp.position.x = oPan.posicion.x;
		bdp.position.y = oPan.posicion.y;

		bdp.type = BodyType.KinematicBody;
		// creamos el cuerpo
		Body oBody = oWorldBox.createBody(bdp);

		// haremos una linea
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(.5f, .5f);
		// necestamos una fixture
		FixtureDef fixture = new FixtureDef();
		fixture.shape = shape;
		fixture.friction = 0;
		oBody.createFixture(fixture);
		// para que no rote el cuadro
		oBody.setUserData(oPan);

	}

	private void crearBooster(float x) {
		//float x = WIDTH + 3;
		float y = 0.7f;

		Boos oBoos = new Boos(x, y);

		arrBoos.add(oBoos);

		BodyDef bd = new BodyDef();
		bd.type = BodyType.KinematicBody;
		bd.position.x = oBoos.posicion.x;
		bd.position.y = oBoos.posicion.y;

		Body oBody = oWorldBox.createBody(bd);

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(.15f, .18f);

		FixtureDef fixDef = new FixtureDef();
		fixDef.shape = shape;
		// para que cuando choque no lo tome como otra plataforma e impulse a
		// nuestro personaje
		// que la moneda no choque con nada pero aun asi reciba eventos de
		// colisiones
		fixDef.isSensor = true;

		oBody.createFixture(fixDef);

		oBody.setUserData(oBoos);
	}

	private void crearPoste(float x) {
		//float x = WIDTH + 3;
		float y = 3.07f;

		Poste oPos = new Poste(x, y);

		arrPoste.add(oPos);

		BodyDef bd = new BodyDef();
		bd.type = BodyType.KinematicBody;
		bd.position.x = oPos.posicion.x;
		bd.position.y = oPos.posicion.y;

		Body oBody = oWorldBox.createBody(bd);

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(.525f, 1.76f);

		FixtureDef fixDef = new FixtureDef();
		fixDef.shape = shape;
		// para que cuando choque no lo tome como otra plataforma e impulse a
		// nuestro personaje
		// que la moneda no choque con nada pero aun asi reciba eventos de
		// colisiones
		fixDef.isSensor = true;

		oBody.createFixture(fixDef);

		oBody.setUserData(oPos);
	}

	private void crearMonedas(float x) {
		float y = Oran.nextFloat() * (2*Screens.WORLD_WIDTH - .3f);


		Monedas oMon = new Monedas(x, y);

		arrMonedas.add(oMon);

		BodyDef bd = new BodyDef();
		bd.type = BodyType.KinematicBody;
		bd.position.x = oMon.posicion.x;
		bd.position.y = oMon.posicion.y;

		Body oBody = oWorldBox.createBody(bd);

		CircleShape shape = new CircleShape();
		shape.setRadius(.1f);

		FixtureDef fixDef = new FixtureDef();
		fixDef.shape = shape;
		fixDef.isSensor = true;

		oBody.createFixture(fixDef);

		oBody.setUserData(oMon);
	}

	private void crearNubes(float x) {
		//float x = WIDTH + 5;
		float alt = Oran.nextInt(10);
		if(alt < 5)
		{
			alt = Oran.nextInt(10);
		}
		float y = Oran.nextFloat() + alt;

		Nubes oNube = new Nubes(x, y);

		arrNubes.add(oNube);

		BodyDef bd = new BodyDef();
		bd.type = BodyType.KinematicBody;
		bd.position.x = oNube.posicion.x;
		bd.position.y = oNube.posicion.y;

		Body oBody = oWorldBox.createBody(bd);

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(.9f, .32f);

		FixtureDef fixDef = new FixtureDef();
		fixDef.shape = shape;
		// para que cuando choque no lo tome como otra plataforma e impulse a
		// nuestro personaje
		// que la moneda no choque con nada pero aun asi reciba eventos de
		// colisiones
		fixDef.isSensor = true;

		oBody.createFixture(fixDef);

		oBody.setUserData(oNube);
	}
    int eliminados;
	public void update(float delta,boolean primer,boolean segundo) {
		oWorldBox.step(delta, 4, 8);		
		// -----------------------------------------------------------------
		oWorldBox.getBodies(arrBodies);
		time -= delta;
		timer +=delta;
		int lenght = arrBodies.size;
	//	Gdx.app.log("Cuerpos", lenght + "");
		
		for (int i = 0; i < lenght; i++) {
			Body body = arrBodies.get(i);
			if (body.getUserData() instanceof Gato) {
				updateGato(delta,body,primer,segundo);
			}
			if (body.getUserData() instanceof Nubes) {
				updateNubes(delta, body);
			}
			if (body.getUserData() instanceof Monedas) {
				updateMonedas(delta, body);
			}
			if (body.getUserData() instanceof Pandilla) {
				updatePandilla(delta, body, primer);
			}
			if (body.getUserData() instanceof Poste) {
				updatePoste(delta, body);
			}
			// -------------------------------------
			if (body.getUserData() instanceof Barandal) {
				updateBarandal(delta, body);
			}
			if (body.getUserData() instanceof BoteBasura) {
				updateBoteBasura(delta, body);
			}
			if (body.getUserData() instanceof CajaCarton) {
				updatecajaCarton(delta, body);
			}
			if (body.getUserData() instanceof Edificio) {
				updateEdificio(delta, body);
			}
			if (body.getUserData() instanceof Piso) {
				updatePiso(delta, body);
			}
			if (body.getUserData() instanceof Lata) {
				updateLata(delta, body);
			}
			if (body.getUserData() instanceof Boos) {
				updateBoos(delta, body);
			}
			if (body.getUserData() instanceof Plataforma) {
				updatePlataforma(delta, body);
			}
			if (body.getUserData() instanceof Jet) {
				updateJet(delta, body);
			}
			if (body.getUserData() instanceof Cuervo) {
				updateCuervo(delta, body);
			}

			if(creacion && control == 1)
			{
				Gdx.app.log("entre","");
			  ponsincial= distancia;
			  limite_i=   distancia + 20;
			  
			 agregarPlataformas(ponsincial, limite_i);
			 creacion = false;
			}
			// -------------------------------------
			if (OGato.state == Gato.State.muerto && OGato.statetime >= Gato.TIEMPO_MUERTO)
			{
				state = State.GameOver;
			}
		}
	}
	private void updateCuervo(float delta, Body body) {
		Cuervo obj = (Cuervo) body.getUserData();
		
		if (obj.posicion.x <= WorldGameRender.oCam.position.x-4)
		{
			arrCuervo.removeValue(obj, true);
			oWorldBox.destroyBody(body);
			eliminados++;
			return;
		}
		body.setLinearVelocity(obj.VELOCIDAD_X, 0);
		obj.update(body, delta);
		
	}

	private void updateJet(float delta, Body body) {
		Jet obj = (Jet) body.getUserData();
		
		if (obj.state ==Jet.State.agarrado) {
			// destruye el cuerpo
			oWorldBox.destroyBody(body);

			// quita el obejto del arreglo
			arrJet.removeValue(obj, true);
			// aqui tambien podria ir el int monedas
			return;
		}

		
		if (obj.posicion.x <= WorldGameRender.oCam.position.x-4) {
			arrJet.removeValue(obj, true);
			oWorldBox.destroyBody(body);
			eliminados++;
			return;
		}
		obj.update(body, delta);		
	}


	private void updateBoos(float delta, Body body) {
		Boos obj = (Boos) body.getUserData();
		
		if (obj.state ==Boos.State.agarrado) {
			// destruye el cuerpo
			oWorldBox.destroyBody(body);

			// quita el obejto del arreglo
			arrBoos.removeValue(obj, true);
			// aqui tambien podria ir el int monedas
			return;
		}

		
		if (obj.posicion.x <= WorldGameRender.oCam.position.x-4) {
			arrBoos.removeValue(obj, true);
			oWorldBox.destroyBody(body);
			eliminados++;
			return;
		}
		obj.update(body, delta);
		

	}

	private void updateLata(float delta, Body body) {
		Lata obj = (Lata) body.getUserData();
		if (obj.posicion.x <= WorldGameRender.oCam.position.x-4)
		{
			arrLata.removeValue(obj, true);
			oWorldBox.destroyBody(body);
			eliminados++;
			return;
		}
		obj.update(body, delta);
		

	}

	private void updatePiso(float delta, Body body) {
		Piso obj = (Piso) body.getUserData();
		if (obj.posicion.x <= WorldGameRender.oCam.position.x-30)
		{
			arrPiso.removeValue(obj, true);
			oWorldBox.destroyBody(body);
			eliminados++;
			return;
		}
		obj.update(body, delta);
	}

	private void updateEdificio(float delta, Body body) {
		Edificio obj = (Edificio) body.getUserData();

		if (obj.posicion.x <= WorldGameRender.oCam.position.x-10)
		{
			arrEdificio.removeValue(obj, true);
			oWorldBox.destroyBody(body);
			eliminados++;
			return;
		}
		obj.update(body, delta);

	}

	private void updatecajaCarton(float delta, Body body) 
	{
		CajaCarton obj = (CajaCarton) body.getUserData();
		if (obj.posicion.x <= WorldGameRender.oCam.position.x-4)
		{
			arrCajacarton.removeValue(obj, true);
			oWorldBox.destroyBody(body);
			eliminados++;
			return;
			
		}		
		obj.update(body, delta);
	}
	

	private void updateBoteBasura(float delta, Body body) 
	{
		BoteBasura obj = (BoteBasura) body.getUserData();
		if (obj.posicion.x <= WorldGameRender.oCam.position.x-4)
		{
			arrBoteBasura.removeValue(obj, true);
			oWorldBox.destroyBody(body);
			eliminados++;
			return;
		}
		obj.update(body, delta);		
	}
	

	private void updateBarandal(float delta, Body body)
	{
		Barandal obj = (Barandal) body.getUserData();
		if (obj.posicion.x <= WorldGameRender.oCam.position.x+30)
		{
			arrBardanl.removeValue(obj, true);
			oWorldBox.destroyBody(body);
			eliminados++;
			return;
		}
		obj.update(body, delta);
		
	}
	

	private void updatePlataforma(float delta, Body body) 
	{
		Plataforma obj = (Plataforma) body.getUserData();
		if (obj.posicion.x <= WorldGameRender.oCam.position.x-4)
		{
			arrPlataforma.removeValue(obj, true);
			oWorldBox.destroyBody(body);
			plata_eliminadas++;
			return;
		}
		obj.update(body, delta);
		
	}

	
	private void updatePoste(float delta, Body body) 
	{
		Poste obj = (Poste) body.getUserData();
		if (obj.posicion.x <= WorldGameRender.oCam.position.x-4)
		{
			arrPoste.removeValue(obj, true);
			oWorldBox.destroyBody(body);
			eliminados++;
			return;
		}
		//body.setLinearVelocity(obj.VELOCIDAD_X, 0);
		obj.update(body, delta);
		
	}

   
	private void updatePandilla(float delta, Body body, boolean jump) 
	{
		
//		Gdx.app.log("posicion del gato"+OGato.position.x,"   posicion de la pandilla"+ oPan.posicion.x);	
		if (OGato.state != Gato.State.muerto)
		{
			oPan.update(body, delta, jump);
			
			if(oPan.posicion.x < WorldGameRender.oCam.position.x-4)
			{
				oPan.posicion.x=OGato.position.x - 4;				
				//Gdx.app.log("ifopan","");
			}			
			
			//Gdx.app.log("timer",""+timer);
			if (timer >= .5f) 
			{
				//body.setTransform(oPan.posicion.x, OGato.position.y, 0);
				timer = 0;
			    //oPan.posicion.y=OGato.position.y;
			}
//			if(jump)
//				body.setLinearVelocity(2.3f, 5);
			//body.setTransform(oPan.posicion.x, OGato.position.y, 0);
//			else
				
				
			
		}
	}

	private void updateMonedas(float delta, Body body) {
		Monedas obj = (Monedas) body.getUserData();
		// el .3 es por el tiempo qeu dura la animacion
		if (obj.state == Monedas.State.Agarrada) {
			// destruye el cuerpo
			oWorldBox.destroyBody(body);

			// quita el obejto del arreglo
			arrMonedas.removeValue(obj, true);
			eliminados++;
			// aqui tambien podria ir el int monedas
			return;
		}

		// Gdx.app.log("posicion monedas:",obj.posicion.x+"");
		if (obj.posicion.x <= WorldGameRender.oCam.position.x-4)		
		{
			arrMonedas.removeValue(obj, true);
			oWorldBox.destroyBody(body);
			eliminados++;
			return;
		}
		// Gdx.app.log("Arreglo de monedas:",arrGAS.size+"");
		obj.update(body, delta);
		//body.setLinearVelocity(obj.VELOCIDAD_X, 0);

	}

	private void updateNubes(float delta, Body body) {
		Nubes obj = (Nubes) body.getUserData();
		if (obj.posicion.x <= WorldGameRender.oCam.position.x-10) {
			arrNubes.removeValue(obj, true);
			oWorldBox.destroyBody(body);
			eliminados++;
			return;
		}
		obj.update(body, delta);
		
	}

	private void updateGato(float delta, Body body, boolean primer,boolean segundo) {
		
			OGato.update(delta, body, primer,segundo, time);
			//actualizar camara para q siga al gato
		if (OGato.state != Gato.State.muerto)
		{
			
			if(body.getPosition().x>WIDTH/2 )//&& body.getPosition().x >posCam)
			{
				WorldGameRender.oCam.position.set( body.getPosition().x, body.getPosition().y,0);
			}
			if(body.getPosition().y<Screens.WORLD_HEIGHT/2)//&& body.getPosition().x >posCam)
			{
				WorldGameRender.oCam.position.set( body.getPosition().x,Screens.WORLD_HEIGHT/2,0);
			}
			//para que la camara siga al gato
			if(body.getPosition().y>Screens.WORLD_HEIGHT + Screens.WORLD_HEIGHT/6f)//&& body.getPosition().x >posCam)
			{
				WorldGameRender.oCam.position.set( body.getPosition().x,Screens.WORLD_HEIGHT + 0.5f + Screens.WORLD_HEIGHT/2,0);
			}
			//para que el gato no traspase la altura del segundo mundo
			if(body.getPosition().y>9.6f )//&& body.getPosition().x >posCam)
			{
				body.setTransform(body.getPosition().x, 9.6f, 0);
			}
			if (primer && OGato.state == Gato.State.fly)
			{
				body.applyForceToCenter(0, Gato.ACELERACION_Y, true);
				if (body.getLinearVelocity().y < Gato.VELOCIDAD_MIN_Y)
				{
					body.setLinearVelocity(0, Gato.VELOCIDAD_MIN_Y);
				}
				// Assets.playSound(Assets.wing);
				else
				body.setLinearVelocity(body.getLinearVelocity().x, body.getLinearVelocity().y);
			}
		distancia = (int) body.getPosition().x;	
		if(distancia % 20 == 0)
		{
			creacion = true;
			control++;
		}
		}
		Gdx.app.log("state",OGato.state +"");
		//Gdx.app.log("vida_gato",OGato.vida+"");
		//Gdx.app.log("controles",control+"");
	//	Gdx.app.log("dis",distancia % 20 +"");
		//Gdx.app.log("tray",distancia +"");
	//	Gdx.app.log("band",creacion +"");
		
	}

	public class Colisiones implements ContactListener {

		@Override
		public void beginContact(Contact contact) 
		{
			Fixture a = contact.getFixtureA();
			Fixture b = contact.getFixtureB();			

			if ( a.getUserData() =="frente" ) 
			{
				ContactoPersonaje(a, b);
			}
			else if (b.getUserData() =="frente"  ) // instanceof Gato) 
			{
				ContactoPersonaje(b, a);				
			}
			
			if ( a.getUserData() =="pies" ) 
			{
				ContactoPersonaje2(a, b);
				
			}
			else if (b.getUserData() =="pies"  ) // instanceof Gato) 
			{
				ContactoPersonaje2(b, a);
				
			}
		}

		private void ContactoPersonaje2(Fixture Personaje, Fixture otracosa)
		{
			Gato oGato = (Gato) Personaje.getBody().getUserData();

			Object Ootracosa = otracosa.getBody().getUserData();
			
			if (Ootracosa instanceof Piso)
			{
				oGato.jump();
			}
			if (Ootracosa instanceof Plataforma) 
			{
				oGato.jump();
			}
			if (Ootracosa instanceof Pandilla) {
				oGato.jump();
			}
			if (Ootracosa instanceof BoteBasura) {
				oGato.jump();
			}
			if (Ootracosa instanceof CajaCarton) {
				oGato.jump();
			}
			if (Ootracosa instanceof Pandilla) {
				oGato.hit();
			}
		}

		private void ContactoPersonaje(Fixture Personaje, Fixture otracosa) 
		{
			Gato oGato = (Gato) Personaje.getBody().getUserData();

			Object Ootracosa = otracosa.getBody().getUserData();

			
			if (Ootracosa instanceof Monedas) {
				Monedas obj = (Monedas) Ootracosa;
				obj.Hit();
				monedas++;
				time++;
			}
			if (Ootracosa instanceof BoteBasura) 
			{
				if(oGato.vida <= 1)
				{
					oGato.hit();
				}
				else
				{
					oGato.vida--;
					otracosa.setSensor(true);
				}
				
				
			}
			if (Ootracosa instanceof Cuervo) {
				if(oGato.vida <= 1)
				{
					oGato.hit();
				}
				else
				{
					oGato.vida--;
					otracosa.setSensor(true);
				}
			}
			if (Ootracosa instanceof CajaCarton) {
				
				if(oGato.vida <= 1)
				{
					oGato.hit();
				}
				else
				{
					oGato.vida--;
					otracosa.setSensor(true);
				}
			}
			if (Ootracosa instanceof Boos) {
				Boos obj = (Boos) Ootracosa;
				boos++;
				obj.Hit();
				if(boos==1)
				{
					oGato.Booster();
					boos=0;
				}
			}
			if (Ootracosa instanceof Jet) {
				Jet obj = (Jet) Ootracosa;
				obj.Hit();
				oGato.Fly();
			}
		}

		// estan seprados los objetos, despues de toparon
		@Override
		public void endContact(Contact contact) {
			// TODO Auto-generated method stub

		}

		// llama antes de que vayan a chocar, pero de que si choquen
		@Override
		public void preSolve(Contact contact, Manifold oldManifold) {
			Fixture a = contact.getFixtureA();
			Fixture b = contact.getFixtureB();
			if (a.getBody().getUserData() instanceof Gato) {
				PreSolveContactoPersonaje(a, b, contact);
			} else if (b.getBody().getUserData() instanceof Gato) {
				PreSolveContactoPersonaje(b, a, contact);
			}
		}

		private void PreSolveContactoPersonaje(Fixture Personaje,
				Fixture OotraCosa, Contact contacto) {
			Gato OGato = (Gato) Personaje.getBody().getUserData();

			Object otracosa = OotraCosa.getBody().getUserData();
			if (otracosa instanceof Plataforma) {
				// .7 y .1 x y y
				// kuro .3 x y .15 en y
				// tocando por abajo la vamos a atravesar, si la estamos tocando
				// por arriba no la vamos a atravesar
				// Calcular la posicoin del mono el y - radio
				float posicion_personaje = OGato.position.y - .15f;
				// Calcular la posicion de la plataforma que seria y + 0.09 y la
				// plataforma mide .1 la mitad y .2 toda en y
				float posicion_plataforma = ((Plataforma) otracosa).posicion.y + .13f;
				if (posicion_personaje < posicion_plataforma) {
					// no existira el concacto y podra atravezar la plataforma
					// desde abajo
					contacto.setEnabled(false);
				}
			}

		}

		// Ya chocaron, fuerza de colision, direccion.
		@Override
		public void postSolve(Contact contact, ContactImpulse impulse) {
			// TODO Auto-generated method stub

		}
	}

}