package com.me.objetos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.esotericsoftware.spine.Skeleton;
import com.me.mygdxgame.Assets;

public class Gato {

	// ---------------jason
	public Skeleton skel;

	// ---------------jason

	public enum State {
		standing, saltando, cayendo, muerto, boos, fly, corriendo
	}

	public static float ACELERACION_Y = 55f;
	public static float VELOCIDAD_MIN_Y = -2f;
	public static float VELOCIDAD_MAX_X = 2F;
	final private int MAX_ANGLE_DEGREES = 20;

	public State state;
	public float statetime;
	public int lado;
	public int vida;
	public int skin = MathUtils.random(1);
	float timer;
	public float angleRad;

	public static final float TIEMPO_MUERTO = .75f;

	public Vector2 position;
	public Vector2 velocidad;

	public Gato(float x, float y) {

		skel = new Skeleton(Assets.skelGatoData);
		if (skin == 0) {
			skel.setSkin("nyancat");
		} else
			skel.setSkin("original");
		skel.setToSetupPose();

		// TODO Auto-generated constructor stub
		position = new Vector2(x, y);
		statetime = 0;
		state = State.cayendo;
		velocidad = new Vector2();
		vida = 4;

	}

	public void update(float delta, Body body, boolean primer, boolean segundo,
			float time) {
		//Gdx.app.log("Estado",state+"");
		position.x = body.getPosition().x;
		position.y = body.getPosition().y;
		velocidad = body.getLinearVelocity();
		statetime += delta;

		if (state != State.muerto && vida != 0) {
			// body.setLinearVelocity(velocidad.x,velocidad.y);
			if (state == State.boos) {

				timer += delta;
				if (timer >= .5f) {
					timer -= 3;
					state = State.standing;
					statetime = 0;
				}
				if (segundo) {
					body.setLinearVelocity(10, 5);
				}

				else
					body.setLinearVelocity(10, velocidad.y);
				return;
			}

			if (primer && state != State.fly && state != State.cayendo && state != State.saltando) 
			{
				state = State.corriendo;
				statetime = 0;
				// para que sale mas
				body.setLinearVelocity(2, velocidad.y);
				

			}
			if (segundo && state != State.saltando && primer && state != State.fly && state != State.cayendo) {

				state = State.saltando;
				statetime = 0;
				body.setLinearVelocity(2, 5);
				Assets.sSaltar.play();
			}
			if (state == State.saltando)
			{
				if (velocidad.y < 0 && state != State.cayendo) {
					state = State.cayendo;
					statetime = 0;
				}
			}
			if (state == State.fly) 
			{
				if (velocidad.y > 3) 
				{
					velocidad.y = 3;
					body.setLinearVelocity(velocidad);
				}
				if (velocidad.y < VELOCIDAD_MIN_Y) {
					velocidad.y = VELOCIDAD_MIN_Y;
					body.setLinearVelocity(velocidad);
				}

				// Gdx.app.log("velidad en y entes del anfgulo"+velocidad.y,""
				// );
				if (velocidad.y < 0) {
					angleRad = velocidad.y * MAX_ANGLE_DEGREES
							/ VELOCIDAD_MIN_Y * -1;
				}
				if (velocidad.y > 0) {
					angleRad = velocidad.y * MAX_ANGLE_DEGREES / 3;
				}

				angleRad = (float) Math.toRadians(angleRad);

			}

			if (time < 1) {
				state = State.muerto;
				statetime = 0;
			}

		} else {
			body.setLinearVelocity(0, -5);
		}
		// body.setLinearVelocity(acelx*5,velocidad.y);
	}

	public void jump()
	{
		if (state == State.cayendo || state == State.fly
				|| state == State.corriendo) {
			state = State.standing;
			statetime = 0;
		}
	}

	public void hit() {
		if (state != State.muerto) {
			Assets.oPium.play();
			state = State.muerto;
			statetime = 0;
		}

		// siempre que cambiamos de un estado a otro reiniciamos el tiempo
	}

	public void Booster() {
		if (state != State.muerto) {
			state = State.boos;
			statetime = 0;
		}
		// siempre que cambiamos de un estado a otro reiniciamos el tiempo
	}

	public void Fly() {
		if (state != State.muerto) {
			state = State.fly;
			statetime = 0;
		}
		// siempre que cambiamos de un estado a otro reiniciamos el tiempo
	}
}
