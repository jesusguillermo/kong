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
	// ----------------------------
	// ----------------------------

	Random Oran;
	int monedas,boos;
	public float time = 30;

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
		// ---------------------------
		arrBodies = new Array<Body>();
		arrNubes = new Array<Nubes>();
		arrMonedas = new Array<Monedas>();
		arrPoste = new Array<Poste>();
		arrBoos = new Array<Boos>();
		arrPlataforma = new Array<Plataforma>();
		Oran = new Random();
		oWorldBox = new World(new Vector2(0, -10), true);
		// //
		oWorldBox.setContactListener(new Colisiones());
		// /

		crearGato();
		crearPandilla();
		crearNubes();
		agregarPlataformas(0, 15);
		/*
		
		crearMonedas();
		crearPandilla();
		
		crearPiso(HEIGHT);

		// --------------------------
		crearBarandal();
		crearBoteBasura();
		crearCajaCarton();
		crearEdficio();
		crearLata();
		CrearPisoTierra();
		crearPoste();*/

	}
	
	public int ponsincial ;
	public int limite_i;
	
	private void  agregarPlataformas(int in ,int limi)
	{
		/*		
		crearMonedas();		
		crearPiso(0.5f);
		crearPiso(HEIGHT);

		// --------------------------
		crearBarandal();
		crearBoteBasura();
		crearCajaCarton();
		crearEdficio();
		crearLata();
		CrearPisoTierra();
		crearPoste();*/
		//Gdx.app.log("posicion en i : ", ""+ponsincial);
		//Gdx.app.log("limite en i : ", ""+limite_i);
		ponsincial = in;
		limite_i= limi;
		for(int i = in ; i<limi ;i++)
		{
		    CrearPisoTierra(i*7.5f);
		    crearBooster(i*12);
		    crearEdficio(i*9);
		    crearPoste(i*8);
		    crearBoteBasura(i*9);
		    crearCajaCarton(i*10);
		    crearLata(i*7);
		    crearPlataforma(i*4, 1, true);
			
		}
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

	private void crearCajaCarton(float x) {

		//float x = WIDTH + 3f;
		float y = .9f;

		CajaCarton oCaj = new CajaCarton(x, y);

		arrCajacarton.add(oCaj);

		BodyDef bd = new BodyDef();
		bd.type = BodyType.KinematicBody;
		bd.position.x = oCaj.posicion.x;
		bd.position.y = oCaj.posicion.y;

		Body oBody = oWorldBox.createBody(bd);

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(0.45f, .3f);

		FixtureDef fixDef = new FixtureDef();
		fixDef.shape = shape;
		fixDef.isSensor = true;

		oBody.createFixture(fixDef);

		oBody.setUserData(oCaj);
	}

	private void crearBoteBasura(float x) {
		//float x = WIDTH + 3f;
		float y = 1.3f;

		BoteBasura oBot = new BoteBasura(x, y);

		arrBoteBasura.add(oBot);

		BodyDef bd = new BodyDef();
		bd.type = BodyType.KinematicBody;
		bd.position.x = oBot.posicion.x;
		bd.position.y = oBot.posicion.y;

		Body oBody = oWorldBox.createBody(bd);

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(.535f, .44f);

		FixtureDef fixDef = new FixtureDef();
		fixDef.shape = shape;
		fixDef.isSensor = true;

		oBody.createFixture(fixDef);

		oBody.setUserData(oBot);
	}

	private void crearBarandal(float x) {
		//float x = WIDTH + 4.5f;
		float y = 1.8f;

		Barandal oBar = new Barandal(x, y);

		arrBardanl.add(oBar);

		BodyDef bd = new BodyDef();
		bd.type = BodyType.KinematicBody;
		bd.position.x = oBar.posicion.x;
		bd.position.y = oBar.posicion.y;

		Body oBody = oWorldBox.createBody(bd);

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(4.01f, .91f);

		FixtureDef fixDef = new FixtureDef();
		fixDef.shape = shape;
		fixDef.isSensor = true;

		oBody.createFixture(fixDef);

		oBody.setUserData(oBar);
	}

	private void crearPlataforma(float x, float y, boolean estado) {
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

	private void crearGato() {
		OGato = new Gato(Screens.WORLD_WIDTH / 2, Screens.WORLD_HEIGHT / 2);

		// para declarar el cuerpo necesitamos una definicion
		BodyDef bd = new BodyDef();
		bd.position.x = OGato.position.x;
		bd.position.y = OGato.position.y;

		bd.type = BodyType.DynamicBody;

		// creamos el cuerpo
		Body oBody = oWorldBox.createBody(bd);

		// haremos un rectangulo
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(.22f , .25f );
		// necestamos una fixture
		FixtureDef fixture = new FixtureDef();
		fixture.shape = shape;
		oBody.createFixture(fixture);
		// para que no rote el cuadro
		oBody.setFixedRotation(true);
		oBody.setUserData(OGato);
	}

	private void crearPandilla() {

		float x = 2;
		float y = .5f;

		oPan = new Pandilla(x, y, 0);
		BodyDef bd = new BodyDef();
		bd.position.x = oPan.posicion.x;
		bd.position.y = oPan.posicion.y;

		bd.type = BodyType.KinematicBody;
		// creamos el cuerpo
		Body oBody = oWorldBox.createBody(bd);

		// haremos una linea
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(1, 0.5f);
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
		//float x = Oran.nextFloat() * Screens.WORLD_WIDTH - .3f;
		float y = 1.5f;

		Monedas oMon = new Monedas(x, y);

		arrMonedas.add(oMon);

		BodyDef bd = new BodyDef();
		bd.type = BodyType.KinematicBody;
		bd.position.x = oMon.posicion.x;
		bd.position.y = oMon.posicion.y;

		Body oBody = oWorldBox.createBody(bd);

		CircleShape shape = new CircleShape();
		shape.setRadius(.2f);

		FixtureDef fixDef = new FixtureDef();
		fixDef.shape = shape;
		fixDef.isSensor = true;

		oBody.createFixture(fixDef);

		oBody.setUserData(oMon);
	}

	private void crearNubes() {
		float x = WIDTH + 5;
		float y = 3.5f;

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

	public void update(float delta, boolean jump) {
		oWorldBox.step(delta, 4, 8);		
		// -----------------------------------------------------------------
		oWorldBox.getBodies(arrBodies);
		time -= delta;
		int lenght = arrBodies.size;
		Gdx.app.log("Cuerpos", lenght + "");
		for (int i = 0; i < lenght; i++) {
			Body body = arrBodies.get(i);
			if (body.getUserData() instanceof Gato) {
				updateGato(delta, body, jump);
			}
			if (body.getUserData() instanceof Nubes) {
				updateNubes(delta, body);
			}
			if (body.getUserData() instanceof Monedas) {
				updateMonedas(delta, body);
			}
			if (body.getUserData() instanceof Pandilla) {
				updatePandilla(delta, body);
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
			// -------------------------------------
			if (OGato.state == Gato.State.muerto) {
				state = State.GameOver;
			}

		}

	}
	private void updateBoos(float delta, Body body) {
		Boos obj = (Boos) body.getUserData();
		if (obj.posicion.x <= WorldGameRender.oCam.position.x-4) {
			arrBoos.removeValue(obj, true);
			oWorldBox.destroyBody(body);
			return;
		}
		obj.update(body, delta);
		body.setLinearVelocity(obj.VELOCIDAD_X, 0);

	}

	private void updateLata(float delta, Body body) {
		Lata obj = (Lata) body.getUserData();
		if (obj.posicion.x <= WorldGameRender.oCam.position.x-4)
		{
			arrLata.removeValue(obj, true);
			oWorldBox.destroyBody(body);
			return;
		}
		obj.update(body, delta);
		

	}

	private void updatePiso(float delta, Body body) {
		Piso obj = (Piso) body.getUserData();
		if (obj.posicion.x <= WorldGameRender.oCam.position.x-10)
		{
			arrPiso.removeValue(obj, true);
			oWorldBox.destroyBody(body);
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
			return;
		}
		obj.update(body, delta);
		
	}
	

	private void updateBarandal(float delta, Body body)
	{
		Barandal obj = (Barandal) body.getUserData();
		if (obj.posicion.x <= WorldGameRender.oCam.position.x-4)
		{
			arrBardanl.removeValue(obj, true);
			oWorldBox.destroyBody(body);
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
			return;
		}
		obj.update(body, delta);
		body.setLinearVelocity(obj.VELOCIDAD_X, 0);
	}

	
	private void updatePoste(float delta, Body body) 
	{
		Poste obj = (Poste) body.getUserData();
		if (obj.posicion.x <= WorldGameRender.oCam.position.x-4)
		{
			arrPoste.removeValue(obj, true);
			oWorldBox.destroyBody(body);
			return;
		}
		//body.setLinearVelocity(obj.VELOCIDAD_X, 0);
		obj.update(body, delta);
		
	}


	private void updatePandilla(float delta, Body body) 
	{
		
		Gdx.app.log("posicion del gato"+OGato.position.x,"   posicion de la pandilla"+ oPan.posicion.x);	
		
		oPan.update(body, delta);
if(oPan.posicion.x < WorldGameRender.oCam.position.x-4)
		{
			oPan.posicion.x=OGato.position.x - 4;
			Gdx.app.log("ifopan","");
			
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
			// aqui tambien podria ir el int monedas
			return;
		}

		// Gdx.app.log("posicion monedas:",obj.posicion.x+"");
		if (obj.posicion.x <= -2) {
			arrMonedas.removeValue(obj, true);
			oWorldBox.destroyBody(body);
			return;
		}
		// Gdx.app.log("Arreglo de monedas:",arrGAS.size+"");
		obj.update(body, delta);
		body.setLinearVelocity(obj.VELOCIDAD_X, 0);

	}

	private void updateNubes(float delta, Body body) {
		Nubes obj = (Nubes) body.getUserData();
		if (obj.posicion.x <= -10) {
			arrNubes.removeValue(obj, true);
			oWorldBox.destroyBody(body);
			return;
		}
		obj.update(body, delta);
		
	}

	private void updateGato(float delta, Body body, boolean jump) {
		OGato.update(delta, body, jump, time);
		
		//actualizar camara para q siga al gato
	    if(body.getPosition().x>WIDTH/2 )//&& body.getPosition().x >posCam)
		{
	    	WorldGameRender.oCam.position.set( body.getPosition().x,HEIGHT/2, 0);
		
		}

	}

	public class Colisiones implements ContactListener {

		@Override
		public void beginContact(Contact contact) {
			// Choca pelota con el piso
			// sacando los objetos que chocarn del contact
			Fixture a = contact.getFixtureA();
			Fixture b = contact.getFixtureB();
			if (a.getBody().getUserData() instanceof Gato) {
				ContactoPersonaje(a, b);
			} else if (b.getBody().getUserData() instanceof Gato) {
				ContactoPersonaje(b, a);
			}
		}

		private void ContactoPersonaje(Fixture Personaje, Fixture otracosa) {

			Gato oGato = (Gato) Personaje.getBody().getUserData();

			Object Ootracosa = otracosa.getBody().getUserData();

			if (Ootracosa instanceof Piso)
			{
				oGato.jump();
			}
			if (Ootracosa instanceof Plataforma) {
				oGato.jump();
			}
			if (Ootracosa instanceof Monedas) {
				Monedas obj = (Monedas) Ootracosa;
				obj.Hit();
				monedas++;
				time++;
			}
			if (Ootracosa instanceof Pandilla) {
				oGato.hit();
			}
			if (Ootracosa instanceof Boos) {
				oGato.Booster();
				boos++;
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