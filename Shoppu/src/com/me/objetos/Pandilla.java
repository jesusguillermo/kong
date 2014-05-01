package com.me.objetos;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.me.objetos.Gato.State;
import com.me.screens.Screens;

public class Pandilla {
	public Vector2 posicion;
	public float state_time;
	public Vector2 velocidad;
	
	public Pandilla(float x, float y,float vel_x)
	{
		posicion = new Vector2( x , y);
		velocidad = new Vector2(vel_x,0);
		state_time = 0;
	}
	public void update(Body body, float delta)
	{
		//actualizando las posiciones
		posicion.x = body.getPosition().x;
		posicion.y = body.getPosition().y;
	
		//Le damos la velocidad, a la derecha
		// velociad es mayor que 8
		// aqui es para que se muevan las plataformas y no se salga de las medidas del mundo
		
		body.setLinearVelocity(2.05f,velocidad.y);
		//body.setLinearVelocity(velocidad);
		state_time += delta;
	}

}
