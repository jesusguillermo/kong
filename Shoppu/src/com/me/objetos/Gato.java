package com.me.objetos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.esotericsoftware.spine.Skeleton;
import com.me.mygdxgame.Assets;

public class Gato {
	
	//---------------jason
	public Skeleton skel;
	//---------------jason
	
	public enum State {
		standing,saltando, cayendo, muerto,boos, fly
	}

	public State state;
	public float statetime;
	public boolean jump;
	public int lado;
	float timer;
	
	public static final float TIEMPO_MUERTO = .75f;

	public Vector2 position;
	public Vector2 velocidad;

	public Gato(float x, float y) {
		// TODO Auto-generated constructor stub
		position = new Vector2(x,y);
		statetime = 0;
		state = State.cayendo;
		velocidad = new Vector2();

		skel = new Skeleton(Assets.skelGatoData);
		skel.setSkin("original");
	}

	public void update(float delta, Body body, boolean jump,float time) {
		
		
		position.x = body.getPosition().x;
		position.y = body.getPosition().y;
		velocidad = body.getLinearVelocity();
		statetime+=delta;
		
		if(state != State.muerto)
		{
			body.setLinearVelocity(2,velocidad.y);
			if (jump && state ==State.standing) 
			{
				jump = false;	
				state = State.saltando;
				statetime = 0;
				//para que sale mas		
				body.setLinearVelocity(velocidad.x, 5);
			}
			
			if(state == State.boos  )
			{
				timer+= delta;
				if (timer >= .5f) 
				{
					if(jump)
					body.setLinearVelocity(10, velocidad.y);
					timer-= 3;	 
					state = State.standing;
					statetime = 0;	
				 }
				body.setLinearVelocity(10, velocidad.y);
			   
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
		else
		{
			body.setLinearVelocity(0,-5);
			
		}
		
		
		
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
	public void Fly()
	{
		if(state != State.muerto)
		{
		   state = State.fly;
		   statetime = 0;
		}
		//siempre que cambiamos de un estado a otro reiniciamos el tiempo 
	}
}
