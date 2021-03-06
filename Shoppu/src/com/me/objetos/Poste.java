package com.me.objetos;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class Poste {
	public enum State
	{
		Normal
	}

		public int tipo = MathUtils.random(4);
		public State state;
		public Vector2 posicion;
		public Vector2 velocidad;
		public float state_time;
		public static float VELOCIDAD_X = -2.7f;
		
		public Poste(float x, float y){
			posicion = new Vector2(x,y);
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
}
