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
import com.me.objetos.Cerros;
import com.me.objetos.Foco;
import com.me.objetos.Gato;
import com.me.objetos.Monedas;
import com.me.objetos.Nubes;
import com.me.objetos.Paisaje;
import com.me.objetos.Pandilla;
import com.me.objetos.Pasaje;
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
	
	final float TIME_TO_SPAWN_CERROS= 1.6f;// Tiempo en segundos para que aparezcan los cerros
	float timeToSpawnCERROS;
	
	final float TIME_TO_SPAWN_MONEDAS= .8f;// Tiempo en segundos para que aparezcan las monedas
	float timeToSpawnMONEDAS;
	
	final float TIME_TO_SPAWN_NUBES= 1.6f;// Tiempo en segundos para que aparezcan las nubes
	float timeToSpawnNUBES;
	
	final float TIME_TO_SPAWN_PAISAJE= 2.96f;// Tiempo en segundos para que aparezcan el paisaje
	float timeToSpawnPAISAJE;
	
	final float TIME_TO_SPAWN_POSTE= 1.6f;// Tiempo en segundos para que aparezcan el poste
	float timeToSpawnPOSTE;
	
	final float TIME_TO_SPAWN_REJILLAS= .8f;// Tiempo en segundos para que aparezcan las rejillas
	float timeToSpawnREJILLAS;
	
	final float TIME_TO_SPAWN_PLATAFORMAS= 1f;// Tiempo en segundos para que aparezcan las rejillas
	float timeToSpawnPLATAFORMAS;
	
	//----------------------------------
	final float TIME_TO_SPAWN_PASAJE= 4f;// Tiempo en segundos para que aparezcan las rejillas
	float timeToSpawnPASAJE;
	
	final float TIME_TO_SPAWN_FOCO= 4.6f;// Tiempo en segundos para que aparezcan las rejillas
	float timeToSpawnFOCO;
	
	final float TIME_TO_SPAWN_TUBERIA= 1.5f;// Tiempo en segundos para que aparezcan las rejillas
	float timeToSpawnTUBERIA;
	
	final float TIME_TO_SPAWN_TUBERIALARGA = 3.5f;// Tiempo en segundos para que aparezcan las rejillas
	float timeToSpawnTUBERIALARGA;
	
	final float TIME_TO_SPAWN_CLOACA= 1.5f;// Tiempo en segundos para que aparezcan las rejillas
	float timeToSpawnCLOACA;
	//----------------------------------
	public enum State
	{
		Running, GameOver
	}
	
State state;
Gato OGato;
Pandilla oPan;
World oWorldBox;
Array<Body> arrBodies;
Array<Cerros> arrCerros;
Array<Nubes> arrNubes;
Array<Monedas> arrMonedas;
Array<Paisaje> arrPaisaje;
Array<Poste> arrPoste;
Array<Rejillas> arrRejillas;
Array<Plataforma> arrPlataforma;
//_---------------------------
Array<Foco> arrFocos;
Array<Tuberia> arrTuberias;
Array<TuberiaLarga> arrTuberLargas;
Array<Rejilla> arrReji;
Array<Pasaje> arrPAseje;
//----------------------------

Random Oran;
int monedas;
public float time = 30;

public WorldGame()
{
	state = State.Running;
	arrBodies = new Array<Body>();
	arrCerros = new Array<Cerros>();
	arrNubes= new Array<Nubes>();
	arrMonedas = new Array<Monedas>();
	arrPaisaje = new Array<Paisaje>();
	arrPoste = new Array<Poste>();
	arrRejillas = new Array<Rejillas>();
	arrPlataforma = new Array<Plataforma>();
	//------------------------
	arrPAseje = new Array<Pasaje>();
	arrReji = new Array<Rejilla>();
	arrFocos = new Array<Foco>();
	arrTuberias = new Array<Tuberia>();
	arrTuberLargas = new Array<TuberiaLarga>();
	//------------------------
	Oran = new Random();
	oWorldBox = new World(new Vector2(0, -10),true);
	////
    oWorldBox.setContactListener(new Colisiones());
    ///
    
    crearGato();
    crearPandilla();
    crearCerros();
    crearNubes();
    crearMonedas();
    crearPaisaje();
    crearPoste();
    crearRejillas();
    crearPandilla();
    crearPiso(0.5f);
    crearPiso(HEIGHT);
  //  crearPlataforma(WIDTH+2,1.5f,true);
    //-----------------------
    crearReji();
    crearTuberias();
    crearTuberiasLargas();
    crearFocos();
    crearPasaje();
    //-----------------------
}


private void crearPasaje() {
	float x = WIDTH + 3;
	float y = 1.6f;
	
	Pasaje oPas = new Pasaje(x, y);
	
	arrPAseje.add(oPas);

	BodyDef bd = new BodyDef();
	bd.type = BodyType.KinematicBody;
	bd.position.x = oPas.posicion.x;
	bd.position.y = oPas.posicion.y;

	Body oBody = oWorldBox.createBody(bd);

	PolygonShape shape = new PolygonShape();
	shape.setAsBox(2.74f, 2.37f);

	FixtureDef fixDef = new FixtureDef();
	fixDef.shape = shape;
	//para que cuando choque no lo tome como otra plataforma e impulse a nuestro personaje
	// que la moneda no choque con nada pero aun asi reciba eventos de colisiones
	fixDef.isSensor = true;

	oBody.createFixture(fixDef);

	oBody.setUserData(oPas);
}


private void crearFocos() {
	float x = WIDTH + 3;
	float y = 3f;

	Foco oFoco = new Foco(x, y);
	
	arrFocos.add(oFoco);

	BodyDef bd = new BodyDef();
	bd.type = BodyType.KinematicBody;
	bd.position.x = oFoco.posicion.x;
	bd.position.y = oFoco.posicion.y;

	Body oBody = oWorldBox.createBody(bd);

	PolygonShape shape = new PolygonShape();
	shape.setAsBox(.41f, .94f);

	FixtureDef fixDef = new FixtureDef();
	fixDef.shape = shape;
	//para que cuando choque no lo tome como otra plataforma e impulse a nuestro personaje
	// que la moneda no choque con nada pero aun asi reciba eventos de colisiones
	fixDef.isSensor = true;

	oBody.createFixture(fixDef);

	oBody.setUserData(oFoco);
}


private void crearTuberiasLargas() {
	float x = WIDTH + 3;
	float y = 2.4f;

	TuberiaLarga oTub = new TuberiaLarga(x, y);
	
	arrTuberLargas.add(oTub);

	BodyDef bd = new BodyDef();
	bd.type = BodyType.KinematicBody;
	bd.position.x = oTub.posicion.x;
	bd.position.y = oTub.posicion.y;

	Body oBody = oWorldBox.createBody(bd);

	PolygonShape shape = new PolygonShape();
	shape.setAsBox(.3f, 2.46f);

	FixtureDef fixDef = new FixtureDef();
	fixDef.shape = shape;
	//para que cuando choque no lo tome como otra plataforma e impulse a nuestro personaje
	// que la moneda no choque con nada pero aun asi reciba eventos de colisiones
	fixDef.isSensor = true;

	oBody.createFixture(fixDef);

	oBody.setUserData(oTub);
}


private void crearTuberias() {
	float x = WIDTH + 3;
	float y = 2.4f;

	Tuberia oTub = new Tuberia(x, y);
	
	arrTuberias.add(oTub);

	BodyDef bd = new BodyDef();
	bd.type = BodyType.KinematicBody;
	bd.position.x = oTub.posicion.x;
	bd.position.y = oTub.posicion.y;

	Body oBody = oWorldBox.createBody(bd);

	PolygonShape shape = new PolygonShape();
	shape.setAsBox(1.26f, 2.54f);

	FixtureDef fixDef = new FixtureDef();
	fixDef.shape = shape;
	//para que cuando choque no lo tome como otra plataforma e impulse a nuestro personaje
	// que la moneda no choque con nada pero aun asi reciba eventos de colisiones
	fixDef.isSensor = true;

	oBody.createFixture(fixDef);

	oBody.setUserData(oTub);
}


private void crearReji() {
	float x = WIDTH + 3;
	float y = 2.4f;

	Rejilla oReji = new Rejilla(x, y);
	
	arrReji.add(oReji);

	BodyDef bd = new BodyDef();
	bd.type = BodyType.KinematicBody;
	bd.position.x = oReji.posicion.x;
	bd.position.y = oReji.posicion.y;

	Body oBody = oWorldBox.createBody(bd);

	PolygonShape shape = new PolygonShape();
	shape.setAsBox(1.02f, 1.02f);

	FixtureDef fixDef = new FixtureDef();
	fixDef.shape = shape;
	//para que cuando choque no lo tome como otra plataforma e impulse a nuestro personaje
	// que la moneda no choque con nada pero aun asi reciba eventos de colisiones
	fixDef.isSensor = true;

	oBody.createFixture(fixDef);

	oBody.setUserData(oReji);
}


private void crearPlataforma(float x, float y, boolean estado) {
	   Plataforma oPlata = new Plataforma(x, y,estado);
			BodyDef bd = new BodyDef();
			bd.type = BodyType.KinematicBody;
			bd.position.x = x;
			bd.position.y = y;

			Body oBody = oWorldBox.createBody(bd);

			PolygonShape shape = new PolygonShape();
			shape.setAsBox(.7f,  .1f);

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
	OGato = new Gato(Screens.WORLD_WIDTH/ 2,Screens.WORLD_HEIGHT/2);
	
	//para declarar el cuerpo necesitamos una definicion
	BodyDef bd = new BodyDef();
	bd.position.x = OGato.position.x;
	bd.position.y = OGato.position.y;
	
	bd.type = BodyType.DynamicBody;
	
	//creamos el cuerpo
	Body oBody = oWorldBox.createBody(bd);
	
	//haremos un rectangulo
	PolygonShape shape = new PolygonShape();
	shape.setAsBox(.3f, .2f);
	//necestamos una fixture
	FixtureDef fixture = new FixtureDef();
	fixture.shape = shape;
	oBody.createFixture(fixture);
	//para que no rote el cuadro
	oBody.setFixedRotation(true);
	oBody.setUserData(OGato);
}

private void crearPandilla() {

	float x = 2;
	float y = 1f;
	
		 oPan = new Pandilla(x, y,0);
			BodyDef bd = new BodyDef();
			bd.position.x = oPan.posicion.x;
			bd.position.y = oPan.posicion.y;
			
			bd.type = BodyType.KinematicBody;		
			//creamos el cuerpo
			Body oBody = oWorldBox.createBody(bd);
			
			//haremos una linea
			PolygonShape shape = new PolygonShape();
			shape.setAsBox(1, 0.5f);
			//necestamos una fixture
			FixtureDef fixture = new FixtureDef();
			fixture.shape = shape;
			fixture.friction = 0;
			oBody.createFixture(fixture);
			//para que no rote el cuadro
			oBody.setUserData(oPan);
			
}

private void crearRejillas() {
	float x = WIDTH + 3;
	float y = 0.7f;

	Rejillas oReji = new Rejillas(x, y);
	
	arrRejillas.add(oReji);

	BodyDef bd = new BodyDef();
	bd.type = BodyType.KinematicBody;
	bd.position.x = oReji.posicion.x;
	bd.position.y = oReji.posicion.y;

	Body oBody = oWorldBox.createBody(bd);

	PolygonShape shape = new PolygonShape();
	shape.setAsBox(.36f, .1f);

	FixtureDef fixDef = new FixtureDef();
	fixDef.shape = shape;
	//para que cuando choque no lo tome como otra plataforma e impulse a nuestro personaje
	// que la moneda no choque con nada pero aun asi reciba eventos de colisiones
	fixDef.isSensor = true;

	oBody.createFixture(fixDef);

	oBody.setUserData(oReji);
}

private void crearPoste() {
	float x = WIDTH + 3;
	float y = 1.6f;

	Poste oPos = new Poste(x, y);
	
	arrPoste.add(oPos);

	BodyDef bd = new BodyDef();
	bd.type = BodyType.KinematicBody;
	bd.position.x = oPos.posicion.x;
	bd.position.y = oPos.posicion.y;

	Body oBody = oWorldBox.createBody(bd);

	PolygonShape shape = new PolygonShape();
	shape.setAsBox(.07f, .39f);

	FixtureDef fixDef = new FixtureDef();
	fixDef.shape = shape;
	//para que cuando choque no lo tome como otra plataforma e impulse a nuestro personaje
	// que la moneda no choque con nada pero aun asi reciba eventos de colisiones
	fixDef.isSensor = true;

	oBody.createFixture(fixDef);

	oBody.setUserData(oPos);
}

private void crearPaisaje() {
	float x = WIDTH + 5;
	float y = 1.05f;

	Paisaje oPais = new Paisaje(x, y);
	
	arrPaisaje.add(oPais);

	BodyDef bd = new BodyDef();
	bd.type = BodyType.KinematicBody;
	bd.position.x = oPais.posicion.x;
	bd.position.y = oPais.posicion.y;

	Body oBody = oWorldBox.createBody(bd);

	PolygonShape shape = new PolygonShape();
	shape.setAsBox(.8f, .23f);

	FixtureDef fixDef = new FixtureDef();
	fixDef.shape = shape;
	//para que cuando choque no lo tome como otra plataforma e impulse a nuestro personaje
	// que la moneda no choque con nada pero aun asi reciba eventos de colisiones
	fixDef.isSensor = true;

	oBody.createFixture(fixDef);

	oBody.setUserData(oPais);
}

private void crearMonedas() {
	float x = Oran.nextFloat() * Screens.WORLD_WIDTH - .3f;
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
	shape.setAsBox(.76f, .13f);

	FixtureDef fixDef = new FixtureDef();
	fixDef.shape = shape;
	//para que cuando choque no lo tome como otra plataforma e impulse a nuestro personaje
	// que la moneda no choque con nada pero aun asi reciba eventos de colisiones
	fixDef.isSensor = true;

	oBody.createFixture(fixDef);

	oBody.setUserData(oNube);
}

private void crearCerros() {
	float x = WIDTH + 3;
	float y = 1.5f;

	Cerros oCerro = new Cerros(x, y);
	
	arrCerros.add(oCerro);

	BodyDef bd = new BodyDef();
	bd.type = BodyType.KinematicBody;
	bd.position.x = oCerro.posicion.x;
	bd.position.y = oCerro.posicion.y;

	Body oBody = oWorldBox.createBody(bd);

	PolygonShape shape = new PolygonShape();
	shape.setAsBox(.26f, .27f);

	FixtureDef fixDef = new FixtureDef();
	fixDef.shape = shape;
	//para que cuando choque no lo tome como otra plataforma e impulse a nuestro personaje
	// que la moneda no choque con nada pero aun asi reciba eventos de colisiones
	fixDef.isSensor = true;

	oBody.createFixture(fixDef);

	oBody.setUserData(oCerro);
}

public void update(float delta, boolean jump) {
	oWorldBox.step(delta, 4, 8);
	timeToSpawnCERROS += delta;
	if (timeToSpawnCERROS >= TIME_TO_SPAWN_CERROS) {
		timeToSpawnCERROS-= TIME_TO_SPAWN_CERROS;
	//	crearCerros();
	}
	timeToSpawnMONEDAS += delta;
	if (timeToSpawnMONEDAS >= TIME_TO_SPAWN_MONEDAS) {
		timeToSpawnMONEDAS-= TIME_TO_SPAWN_MONEDAS;
		crearMonedas();
	}
	timeToSpawnNUBES += delta;
	if (timeToSpawnNUBES >= TIME_TO_SPAWN_NUBES) {
		timeToSpawnNUBES-= TIME_TO_SPAWN_NUBES;
		crearNubes();
	}
	timeToSpawnPAISAJE += delta;
	if (timeToSpawnPAISAJE >= TIME_TO_SPAWN_PAISAJE) {
		timeToSpawnPAISAJE-= TIME_TO_SPAWN_PAISAJE;
		//crearPaisaje(); 
	}
	timeToSpawnPOSTE += delta;
	if (timeToSpawnPOSTE>= TIME_TO_SPAWN_POSTE) {
		timeToSpawnPOSTE-= TIME_TO_SPAWN_POSTE;
		//crearPoste();
	}
	timeToSpawnREJILLAS += delta;
	if (timeToSpawnREJILLAS>= TIME_TO_SPAWN_REJILLAS) {
		timeToSpawnREJILLAS-= TIME_TO_SPAWN_REJILLAS;
		//crearRejillas();
		
	}
	timeToSpawnPLATAFORMAS += delta;
	if (timeToSpawnPLATAFORMAS>= TIME_TO_SPAWN_PLATAFORMAS) {
		timeToSpawnPLATAFORMAS-= TIME_TO_SPAWN_PLATAFORMAS;
		float y = Oran.nextInt(4);
		if(y == 0)
		{
			y = 1;
		}
		crearPlataforma(WIDTH + 2,y, true);
		//crearPlataforma(WIDTH + 2,1.5f, true);
	}
	//-----------------------------------------------------------------
	timeToSpawnPASAJE += delta;
	if (timeToSpawnPASAJE >= TIME_TO_SPAWN_PASAJE) {
		timeToSpawnPASAJE-= TIME_TO_SPAWN_PASAJE;
		crearPasaje();
	}	
	timeToSpawnFOCO+= delta;
	if (timeToSpawnFOCO >= TIME_TO_SPAWN_FOCO) {
		timeToSpawnFOCO-= TIME_TO_SPAWN_FOCO;
		crearFocos();
	}	
	timeToSpawnTUBERIA+= delta;
	if (timeToSpawnTUBERIA >= TIME_TO_SPAWN_TUBERIA) {
		timeToSpawnTUBERIA-= TIME_TO_SPAWN_TUBERIA;
		crearTuberias();
	}	
	timeToSpawnCLOACA += delta;
	if (timeToSpawnCLOACA >= TIME_TO_SPAWN_CLOACA) {
		timeToSpawnCLOACA-= TIME_TO_SPAWN_CLOACA;
		crearReji();
	}
	timeToSpawnTUBERIALARGA += delta;
	if (timeToSpawnTUBERIALARGA >= TIME_TO_SPAWN_TUBERIALARGA) {
		timeToSpawnTUBERIALARGA-= TIME_TO_SPAWN_TUBERIALARGA;
		crearTuberiasLargas();
	}
	
	//-----------------------------------------------------------------
	oWorldBox.getBodies(arrBodies);
	time -= delta;
	int lenght = arrBodies.size;
	Gdx.app.log("Cuerpos",lenght+"");
	for(int i = 0; i < lenght;i++)
	{
		Body body = arrBodies.get(i);
		if(body.getUserData() instanceof Gato)
		{
			updateGato(delta,body,jump);
		}
		if(body.getUserData() instanceof Cerros)
		{
			updateCerros(delta,body);
		}
		if(body.getUserData() instanceof Nubes)
		{
			updateNubes(delta,body);
		}
		if(body.getUserData() instanceof Monedas)
		{
			updateMonedas(delta,body);
		}
		if(body.getUserData() instanceof Paisaje)
		{
			updatePaisaje(delta,body);
		}
		if(body.getUserData() instanceof Pandilla)
		{
			updatePandilla(delta,body);
		}
		if(body.getUserData() instanceof Poste)
		{
			updatePoste(delta,body);
		}
		if(body.getUserData() instanceof Rejillas)
		{
			updateRejillas(delta,body);
		}
		if(body.getUserData() instanceof Plataforma)
		{
			updatePlataforma(delta,body);
		}
		if(body.getUserData() instanceof Rejilla)
		{
			updateReji(delta,body);
		}
		if(body.getUserData() instanceof Foco)
		{
			updateFoco(delta,body);
		}
		if(body.getUserData() instanceof Tuberia)
		{
			updatePTuberia(delta,body);
		}
		if(body.getUserData() instanceof TuberiaLarga)
		{
			updateTuberiaLarga(delta,body);
		}
		if(body.getUserData() instanceof Pasaje)
		{
			updatePasaje(delta,body);
		}
		if(OGato.state == Gato.State.muerto)
		{
			state = State.GameOver;
		}
		
	}
	
}

private void updatePasaje(float delta, Body body) {
	Pasaje obj = (Pasaje) body.getUserData();
	if(obj.posicion.x <= -2)
	{
		arrPAseje.removeValue(obj, true);
		oWorldBox.destroyBody(body);
		return;
	}
	obj.update(body, delta);
	body.setLinearVelocity(obj.VELOCIDAD_X, 0);
}


private void updateTuberiaLarga(float delta, Body body) {
	TuberiaLarga obj = (TuberiaLarga) body.getUserData();
	if(obj.posicion.x <= -2)
	{
		arrTuberLargas.removeValue(obj, true);
		oWorldBox.destroyBody(body);
		return;
	}
	obj.update(body, delta);
	body.setLinearVelocity(obj.VELOCIDAD_X, 0);
}


private void updatePTuberia(float delta, Body body) {
	Tuberia obj = (Tuberia) body.getUserData();
	if(obj.posicion.x <= -2)
	{
		arrTuberias.removeValue(obj, true);
		oWorldBox.destroyBody(body);
		return;
	}
	obj.update(body, delta);
	body.setLinearVelocity(obj.VELOCIDAD_X, 0);
}


private void updateFoco(float delta, Body body) {
	Foco obj = (Foco) body.getUserData();
	if(obj.posicion.x <= -2)
	{
		arrFocos.removeValue(obj, true);
		oWorldBox.destroyBody(body);
		return;
	}
	obj.update(body, delta);
	body.setLinearVelocity(obj.VELOCIDAD_X, 0);
}


private void updateReji(float delta, Body body) {
	Rejilla obj = (Rejilla) body.getUserData();
	if(obj.posicion.x <= -2)
	{
		arrReji.removeValue(obj, true);
		oWorldBox.destroyBody(body);
		return;
	}
	obj.update(body, delta);
	body.setLinearVelocity(obj.VELOCIDAD_X, 0);
}


private void updatePlataforma(float delta, Body body) {
	Plataforma obj = (Plataforma) body.getUserData();
	if(obj.posicion.x <= -2)
	{
		arrPlataforma.removeValue(obj, true);
		oWorldBox.destroyBody(body);
		return;
	}
	obj.update(body, delta);
	body.setLinearVelocity(obj.VELOCIDAD_X, 0);
	
}



private void updateRejillas(float delta, Body body) {
	Rejillas obj = (Rejillas) body.getUserData();
	if(obj.posicion.x <= -2)
	{
		arrRejillas.removeValue(obj, true);
		oWorldBox.destroyBody(body);
		return;
	}
	obj.update(body, delta);
	body.setLinearVelocity(obj.VELOCIDAD_X, 0);
}


private void updatePoste(float delta, Body body) {
	Poste obj = (Poste) body.getUserData();
	if(obj.posicion.x <= -2)
	{
		arrPoste.removeValue(obj, true);
		oWorldBox.destroyBody(body);
		return;
	}
	obj.update(body, delta);
	body.setLinearVelocity(obj.VELOCIDAD_X, 0);
}


private void updatePandilla(float delta, Body body) {
oPan.update(body, delta);
	
}


private void updatePaisaje(float delta, Body body) {
	Paisaje obj = (Paisaje) body.getUserData();
	if(obj.posicion.x <= -5)
	{
		arrPaisaje.removeValue(obj, true);
		oWorldBox.destroyBody(body);
		return;
	}
	obj.update(body, delta);
	body.setLinearVelocity(obj.VELOCIDAD_X, 0);
}


private void updateMonedas(float delta, Body body) {
	Monedas obj = (Monedas) body.getUserData();
	// el .3 es por el tiempo qeu dura la animacion
	if (obj.state == Monedas.State.Agarrada) {
		// destruye el cuerpo
		oWorldBox.destroyBody(body);
		
		// quita el obejto del arreglo
		arrMonedas.removeValue(obj, true);
		//aqui tambien podria ir el int monedas
		return;
	}

	//Gdx.app.log("posicion monedas:",obj.posicion.x+"");
	if (obj.posicion.x <= -2)
	{	
		arrMonedas.removeValue(obj, true);
		oWorldBox.destroyBody(body);
		return;
	}
	//Gdx.app.log("Arreglo de monedas:",arrGAS.size+"");
	obj.update(body, delta);
	body.setLinearVelocity(obj.VELOCIDAD_X, 0);
	
}


private void updateNubes(float delta, Body body) {
	Nubes obj = (Nubes) body.getUserData();
	if(obj.posicion.x <= -10)
	{
		arrNubes.removeValue(obj, true);
		oWorldBox.destroyBody(body);
		return;
	}
	obj.update(body, delta);
	body.setLinearVelocity(obj.VELOCIDAD_X, 0);
}


private void updateCerros(float delta, Body body) {
	Cerros obj = (Cerros) body.getUserData();
	if(obj.posicion.x <= -2)
	{
		arrCerros.removeValue(obj, true);
		oWorldBox.destroyBody(body);
		return;
	}
	obj.update(body, delta);
	body.setLinearVelocity(obj.VELOCIDAD_X, 0);
}


private void updateGato(float delta, Body body, boolean jump) {
	OGato.update(delta, body,jump,time);	
	
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

		if(Ootracosa.equals("pared"))
		{
			oGato.jump();
		}		
		if(Ootracosa instanceof Plataforma)
		{
			oGato.jump();
		}
		if (Ootracosa instanceof Monedas)
		{
			Monedas obj = (Monedas) Ootracosa;
			obj.Hit();
			monedas++;
			time++;
		}
		if(Ootracosa instanceof Pandilla)
		{
			oGato.hit();
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
			PreSolveContactoPersonaje(a, b,contact);
		} else if (b.getBody().getUserData() instanceof Gato) {
			PreSolveContactoPersonaje(b, a,contact);
		}
	}

	private void PreSolveContactoPersonaje(Fixture Personaje, Fixture OotraCosa, Contact contacto) {
		Gato OGato = (Gato) Personaje.getBody().getUserData();

		Object otracosa = OotraCosa.getBody().getUserData();
		 if (otracosa instanceof Plataforma) {
			 //.7 y .1 x y y
			 //kuro .3 x y .15 en y
			 //tocando por abajo la vamos a atravesar, si la estamos tocando por arriba no la vamos a atravesar
			 //Calcular la posicoin del mono el y - radio 
			float posicion_personaje = OGato.position.y - .2f;
			//Calcular la posicion de la plataforma que seria y + 0.09 y la plataforma mide .1 la mitad y .2 toda en y
			float posicion_plataforma = ((Plataforma) otracosa).posicion.y + .1f;
			 if(posicion_personaje < posicion_plataforma){
				 //no existira el concacto y podra atravezar la plataforma desde abajo
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
