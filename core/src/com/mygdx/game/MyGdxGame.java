package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class MyGdxGame extends ApplicationAdapter {
	private FitViewport viewport;
	private int random = 0;
	private boolean durdur, sınırda=false;
	float timer=0f;
	public OrthographicCamera camera;
	Music sound;
	private Vector2 playerFirstPos, enemyPlayerFirstPos, ballFirstPos;

	BitmapFont font,font2;
	SpriteBatch batch;
	Texture img1,img2,img3,img4,img5,img6;
	Sprite sprite1,sprite2,sprite3,sprite4;
	World world;
	Body solKose,sagKose,ustKose,altKose,body1,body2,body3,body4,body5,body6,body7;
	int width=0;
	float axes=0;

	private int enemyGoalCounter, playerGoalCounter=0;
	private boolean goal = false;
	Vector2 directionVector;
	Vector3 touchCoordinate;

	int direction=-1;
	@Override
	public void create () {

		camera = new OrthographicCamera(480/50,800/50);
		viewport = new FitViewport(480/50,800/50,camera);

		font = new BitmapFont();
		font2 = new BitmapFont();
		font.setColor(Color.RED);
		font2.setColor(Color.BLUE);
		touchCoordinate = new Vector3(0,0,0);
		sound = Gdx.audio.newMusic(Gdx.files.internal("taktak.wav"));

		batch = new SpriteBatch();
		img1 = new Texture("sphere2.png");
		img2 = new Texture("sphere2.png");
		img3 = new Texture("ball.png");
		img4 = new Texture("layout.png");

		sprite1 = new Sprite(img1);
		sprite2 = new Sprite(img2);
		sprite3 = new Sprite(img3);
		sprite4 = new Sprite(img4);

		sprite1.setPosition(camera.viewportWidth/2,camera.viewportHeight/2-6.5f);
		sprite2.setPosition(camera.viewportWidth/2,camera.viewportHeight/2+6.5f);
		sprite3.setPosition(camera.viewportWidth/2,camera.viewportHeight/2-0.5f);
		sprite4.setPosition(viewport.getScreenX(),viewport.getScreenY());

		world = new World(new Vector2(0, 0),true);
		////////////////////////////////////////////
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.DynamicBody;
		bodyDef.position.set(sprite1.getX(),sprite1.getY());
		playerFirstPos = new Vector2(sprite1.getX(),sprite1.getY());

		body1 = world.createBody(bodyDef);
		CircleShape shape = new CircleShape();
		shape.setRadius(1f);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.restitution=0.6f;
		body1.createFixture(fixtureDef);
		shape.dispose();
		//////////////////////////////////////////////
		BodyDef bodyDef2 = new BodyDef();
		bodyDef2.type = BodyDef.BodyType.DynamicBody;
		bodyDef2.position.set(sprite2.getX(),sprite2.getY());
		playerFirstPos = new Vector2(sprite2.getX(),sprite2.getY());

		body2 = world.createBody(bodyDef2);
		CircleShape shape2 = new CircleShape();
		shape2.setRadius(1f);
		FixtureDef fixtureDef2 = new FixtureDef();
		fixtureDef2.shape = shape2;
		fixtureDef.restitution=0.6f;
		body2.createFixture(fixtureDef2);
		shape2.dispose();
		/////////////////////////////////////////////////
		BodyDef bodyDef3 = new BodyDef();
		bodyDef3.type = BodyDef.BodyType.DynamicBody;
		bodyDef3.position.set(sprite3.getX(),sprite3.getY());
		ballFirstPos = new Vector2(sprite3.getX(),sprite3.getY());

		body3 = world.createBody(bodyDef3);
		CircleShape shape3 = new CircleShape();
		shape2.setRadius(0.75f);
		FixtureDef fixtureDef3 = new FixtureDef();
		fixtureDef3.shape = shape3;
		fixtureDef3.density = 0.1f;
		fixtureDef3.restitution=0.95f;
		body3.createFixture(fixtureDef3);
		shape3.dispose();
		////////////////////////////////////////////////////
		BodyDef bodyDef4 = new BodyDef();
		bodyDef4.type = BodyDef.BodyType.StaticBody;

		body4 = world.createBody(bodyDef4);
		EdgeShape shape4 = new EdgeShape();
		shape4.set(0.265f,0,0.265f,25); // sol taraf
		FixtureDef fixtureDef4 = new FixtureDef();
		fixtureDef4.shape = shape4;
		body4.createFixture(fixtureDef4);
		shape4.dispose();
		//////////////////////////////////////////////////
		BodyDef bodyDef5 = new BodyDef();
		bodyDef5.type = BodyDef.BodyType.StaticBody;

		body5 = world.createBody(bodyDef5);
		EdgeShape shape5 = new EdgeShape();
		shape5.set(8.75f,0,8.75f,25); // sag taraf
		FixtureDef fixtureDef5 = new FixtureDef();
		fixtureDef5.shape = shape5;
		body5.createFixture(fixtureDef5);
		shape5.dispose();
		///////////////////////////////////////////////////
		BodyDef bodyDef6 = new BodyDef();
		bodyDef6.type = BodyDef.BodyType.StaticBody;

		body6 = world.createBody(bodyDef6);
		EdgeShape shape6 = new EdgeShape();
		shape6.set(0.25f,0.28f,10f,0.28f); // alt collider
		FixtureDef fixtureDef6 = new FixtureDef();
		fixtureDef6.shape = shape6;
		body6.createFixture(fixtureDef6);
		shape6.dispose();
		////////////////////////////////////////////////////
		BodyDef bodyDef7 = new BodyDef();
		bodyDef7.type = BodyDef.BodyType.StaticBody;

		body7 = world.createBody(bodyDef7);
		EdgeShape shape7 = new EdgeShape();
		shape7.set(0.25f,15.75f,50f,15.75f); // üst collider
		FixtureDef fixtureDef7 = new FixtureDef();
		fixtureDef7.shape = shape7;
		body7.createFixture(fixtureDef7);
		shape7.dispose();
	}
	@Override
	public void resize(int width,int height){
		viewport.update(width,height,true);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		world.step(1f/60f, 6, 2);
		camera.update();
		batch.setProjectionMatrix(camera.combined);

		AI();
		play();
		inputController(); // A function for keyboard input controlling
		math(); // For Collision controlling

		batch.begin();
		batch.draw(sprite4,0,0,viewport.getWorldWidth(),viewport.getWorldHeight());
		batch.draw(sprite1,body1.getPosition().x-1f,body1.getPosition().y-1f,2,2);
		batch.draw(sprite2,body2.getPosition().x-1f,body2.getPosition().y-1f,2,2);
		batch.draw(sprite3,body3.getPosition().x-1f,body3.getPosition().y-1f,2,2);
		batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
		world.dispose();
	}

	void play() {

	}

	void inputController() {
		if(Gdx.input.isTouched()){
			Vector2 directionVector = new Vector2(0,0);
			touchCoordinate.x = Gdx.input.getX();
			touchCoordinate.y = Gdx.input.getY();
			camera.unproject(touchCoordinate);

			double distance = Math.pow(0.5f,2)-(Math.pow(((body1.getPosition().x)-touchCoordinate.x),2)+Math.pow(((body1.getPosition().y)-touchCoordinate.y),2));
			Vector2 direction = null;
			float _distance = Vector2.dst(touchCoordinate.x,touchCoordinate.y,body1.getPosition().x,body1.getPosition().y);

			Vector2 getPosition = new Vector2(body1.getPosition().x,body1.getPosition().y);
			Vector2 touch;
			touch = new Vector2(touchCoordinate.x,touchCoordinate.y);
			direction = touch.sub(getPosition).nor();

			if(distance>0.95f){
				Vector2 vel = body1.getLinearVelocity();
				vel.x=0;
				vel.y=0;
				body1.setLinearVelocity(vel);
			}else if(distance==0){

			}else{
				if(body1.getPosition().y<camera.viewportHeight/2) {
					float speed = _distance * 50f;
					Vector2 vel = body1.getLinearVelocity();
					body1.setLinearVelocity(direction.scl(speed));
				}else{
					Vector2 vel = body1.getLinearVelocity();
					vel.x=0;
					vel.y=0;
					body1.setLinearVelocity(vel);
					body1.setTransform(body1.getPosition().x,camera.viewportHeight/2-0.01f,0);
				}
			}
		}else{
			Vector2 vel = body1.getLinearVelocity();
			vel.x=0;
			vel.y=0;
			body1.setLinearVelocity(vel);
		}
		double distance2 = Math.pow(2/2,2)-(Math.pow(((sprite1.getX()+2/2)-(sprite3.getX()+1/1)),2)+Math.pow(((sprite1.getY()+2/2)-sprite3.getY()-1/1),2));


		if(distance2>-1.98f){
			sound.play();
		}
	}

	void math() {
		goalSystem();
	}

	void restartPositions(){
		body1.setTransform(sprite1.getX(),sprite1.getY(),0);
		body2.setTransform(sprite2.getX(),sprite2.getY(),0);
		body3.setTransform(sprite3.getX(),sprite3.getY(),0);
		body3.setLinearVelocity(new Vector2(0,0));
		goal = false;
	}


	void goalSystem() {
		if(!goal) {
			if(body3.getPosition().y < camera.viewportHeight/2-6.75f) {
				if(body3.getPosition().x >= (camera.viewportWidth/2-2f) && (body3.getPosition().x <= (camera.viewportWidth/2+2f))) {
					goal=true;
					enemyGoalCounter++;
					restartPositions();
				}
			}
			if(body3.getPosition().y > camera.viewportHeight/2+6.75f) {
				if(body3.getPosition().x >= (camera.viewportWidth/2-2f) && (body3.getPosition().x <= (camera.viewportWidth/2+2f))) {
					goal=true;
					enemyGoalCounter++;
					restartPositions();
				}
			}
		}
	}
	void AI(){
		Array<Vector2> randomPositions = new Array<Vector2>();
		randomPositions.add(new Vector2(camera.viewportWidth/2, camera.viewportHeight/2+6.35f));

		if(!sınırda) {
			goTO(randomPositions.get(0).x, randomPositions.get(0).y);
		}
		Vector2 ballPos = new Vector2((body3.getPosition().x),(body3.getPosition().y));
		if(ballPos.y > camera.viewportHeight/2+0.5f){
			sınırda=true;
			////////////////////////////////////
			double distance = Math.pow(0.5f/2,2)-(Math.pow((body2.getPosition().x-body3.getPosition().x),2)+Math.pow(body2.getPosition().y-body3.getPosition().y,2));
			Vector2 direction = null;
			float _distance = Vector2.dst(body3.getPosition().x,body3.getPosition().y,body2.getPosition().x,body2.getPosition().y);

			Vector2 spritePosition = new Vector2(body2.getPosition().x,body2.getPosition().y);
			Vector2 touch;
			touch = new Vector2(body3.getPosition().x,body3.getPosition().y);
			direction = touch.sub(spritePosition).nor();

			float speed = 0.5f;
			speed *= _distance*1.2f;
			Vector2 vel = body2.getLinearVelocity();
			if(distance<1f && distance > 0.3f){
				Vector2 negativeDirection = spritePosition.sub(touch).nor();
				body2.applyForceToCenter(negativeDirection.scl(100/_distance),true);
			}else{
				if(distance<-5f) {
					body2.setLinearVelocity(direction.scl(15f));
				}
				if(distance>-1.98f){
					sound.play();
				}
			}
		}else{
			sınırda=false;
		}
	}

	void goTO(float x, float y){
		Vector2 randomPosition = new Vector2(x,y);

		Vector2 directionVector = new Vector2(0,0);

		double distance = Math.pow(0.5f/2,2)-(Math.pow(((body2.getPosition().x)-randomPosition.x),2)+Math.pow(((body2.getPosition().y)-randomPosition.y),2));
		Vector2 direction = null;
		float _distance = Vector2.dst(randomPosition.x,randomPosition.y,body2.getPosition().x,body2.getPosition().y);

		Vector2 spritePosition = new Vector2(body2.getPosition().x,body2.getPosition().y);
		Vector2 touch;
		touch = new Vector2(randomPosition.x,randomPosition.y);
		direction = touch.sub(spritePosition).nor();

		float speed = 1;
		speed *= _distance*2f;
		Vector2 vel = body2.getLinearVelocity();
		body2.setLinearVelocity(direction.scl(speed*_distance));
	}
}