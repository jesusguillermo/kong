package com.me.objetos;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class Monedas {
	public enum State
	{
		Normal, Agarrada
	}
		public State state;
		public Vector2 posicion;
		public Vector2 velocidad;
		public float state_time;

		public static float VELOCIDAD_X = -2.7f;
		
		public Monedas(float x, float y){
			posicion = new Vector2(x,y);
			/*
			velocidad = new Vector2(x,y);
			state = State.Normal;
			state_time = 0;
			*/
		}
		
		public void init(float x, float y) {
			posicion.set(x, y);
			state_time = 0;
			state = State.Normal;
		}
		
		public void update(Body  body, float delta) {
			//actualizando las posiciones
					posicion.x = body.getPosition().x;
					posicion.y = body.getPosition().y;
										
					state_time += delta;
		}
		public void Hit(){
			state_time = 0;
			state = State.Agarrada;		
		}
}
