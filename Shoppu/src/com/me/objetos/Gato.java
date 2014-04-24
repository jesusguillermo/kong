package com.me.objetos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class Gato {
	public enum State {
		standing,saltando, cayendo, muerto,boos
	}

	public State state;
	public float statetime;
	public boolean jump;
	public int lado;

	public Vector2 position;
	public Vector2 velocidad;

	public Gato(float x, float y) {
		// TODO Auto-generated constructor stub
		position = new Vector2(x,y);
		statetime = 0;
		state = State.cayendo;
		velocidad = new Vector2();

	}

	public void update(float delta, Body body, boolean jump,float time) {
		
		
		position.x = body.getPosition().x;
		position.y = body.getPosition().y;
		velocidad = body.getLinearVelocity();
		
		if(state != State.muerto)
		{
			body.setLinearVelocity(velocidad.x,velocidad.y);
			if (jump && state ==State.standing) 
			{
				//jump = false;	
				state = State.saltando;
				statetime = 0;
				//para que sale mas		
				body.setLinearVelocity(velocidad.x, 5);
			}
			float timer=0;
			if(state == State.boos)
			{
			   /*timer=+delta;
			   Gdx.app.log("tiempoodelta", ""+delta);
			   if(timer>3)
			   {*/
			     state = State.standing;
			     statetime = 0;	
			   
			  // }			  
			    //Cerros.VELOCIDAD_X=-5;
			    //Nubes.VELOCIDAD_X=-5;
			    //Rejillas.VELOCIDAD_X=-5;
			    //Foco.VELOCIDAD_X=-5;
			    //Poste.VELOCIDAD_X=-5;
			}		
			
			if(state == State.saltando)
				if (velocidad.y < 0 && state != State.cayendo)
				{
					state = State.cayendo;
					statetime = 0;
				}
			
			if(time < 1)
			{
				state= State.muerto;
			    statetime=0;
			}
			
		}
		
		statetime+=delta;
		//body.setLinearVelocity(acelx*5,velocidad.y);

	}

	public void jump() {
		if (state == State.cayendo) 
		{				
			state = State.standing;	
			statetime = 0;
		}
						}
	public void hit()
	{
		if(state != State.muerto)
		{
		   state = State.muerto;
		   statetime = 0;
		}
		//siempre que cambiamos de un estado a otro reiniciamos el tiempo 
	}
	public void Booster()
	{
		if(state != State.muerto)
		{
		   state = State.boos;
		   statetime = 0;
		}
		//siempre que cambiamos de un estado a otro reiniciamos el tiempo 
	}
}
