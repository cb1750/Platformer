package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.MouseJoint;
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef;
import com.badlogic.gdx.physics.box2d.joints.WeldJointDef;
import helper.ListenerClass;
import helper.TileMapHelper;
import objects.player.Player;

import static com.badlogic.gdx.physics.box2d.JointDef.JointType.MouseJoint;
import static helper.Constants.PPM;

public class GameScreen extends ScreenAdapter {
    private  OrthographicCamera camera;
    private SpriteBatch batch;
    private World world;
    private Box2DDebugRenderer box2DDebugRenderer;

    private OrthogonalTiledMapRenderer orthogonalTiledMapRenderer;
    private TileMapHelper tileMapHelper;
    private ListenerClass listenerClass;

    //Game objects
    private Player player;
    private Player shadowPlayer;
    private  int playerCount;

   public GameScreen(OrthographicCamera camera){
    this.camera = camera;
    this.batch = new SpriteBatch();
    this.world = new World(new Vector2(0,-25f),false);
    world.setContactListener(listenerClass);
    this.box2DDebugRenderer = new Box2DDebugRenderer();

    this.tileMapHelper = new TileMapHelper(this);
    this.orthogonalTiledMapRenderer = tileMapHelper.setupMap();

}
   public void update(){
     world.step(1/60f,6,2);
     cameraUpdate();

     batch.setProjectionMatrix(camera.combined);
     orthogonalTiledMapRenderer.setView(camera);
     player.update();
     shadowPlayer.getBody().setTransform(player.getBody().getPosition(),0);





     if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
         Gdx.app.exit();
     }
    }
    private void cameraUpdate(){
       Vector3 position = camera.position;
       position.x = Math.round(player.getBody().getPosition().x * PPM * 10) / 10f;
       position.y = Math.round(player.getBody().getPosition().y * PPM * 10) /10f;
        camera.position.set(position);
       camera.update();

    }
    @Override
    public void render(float delta){
    this.update();

    Gdx.gl.glClearColor(0,0,0,1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    orthogonalTiledMapRenderer.render();

    batch.begin();
    //Render objects

    batch.end();
    box2DDebugRenderer.render(world,camera.combined.scl(PPM));
 }

    public World getWorld() {
        return world;
    }
    public void setPlayer(Player player){

       if(playerCount > 0) {
           this.shadowPlayer = player; //Creates a sensor player to see when it touches blocks
       }
       else {
           this.player = player;
           playerCount++;
       }


    }
}
