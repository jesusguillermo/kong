package com.me.objetos;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class Plataforma {
	public enum State
	{
		normal,rompible,rompiendo
	}
	public State state;
	public float state_time;
	public Vector2 posicion;
	public Vector2 velocity;

	public static float VELOCIDAD_X = -2.7f;
	
	public Plataforma(float x, float y, Boolean estado)
	{		
		posicion = new Vector2(x,y);
		if(estado)
		   state= State.normal;
		else
		   state= State.rompible;	
		
		/*if(state == State.normal)
			velocity= new Vector2(2,0);
		else
			velocity= new Vector2(0,0);*/
	}
	
	public void update (Body oBody, float delta)
	{
		posicion.x = oBody.getPosition().x;
		posicion.y = oBody.getPosition().y;
		
		/*if(posicion.x>Screens.WORLD_WIDTH-.2f)
			velocity= new Vector2(-2,0);
		
		if(posicion.x<0+.15f)
			velocity= new Vector2(2,0);*/
		
			
		state_time+=delta;
	}

	public void hit()
	{
		if(state== State.rompible)
		{
			state= State.rompiendo;
			state_time=0;
		}
	}
}
