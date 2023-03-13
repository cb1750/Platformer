package helper;

import com.badlogic.gdx.physics.box2d.*;

import static helper.Constants.PPM;

public class BodyHelperService {

    public static Body createBody(float x, float y, float width, float height, boolean isStatic, World world,boolean isSensor){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type= isStatic ? BodyDef.BodyType.StaticBody : BodyDef.BodyType.DynamicBody;
        //If body is stationary it becomes a static body, if not it becomes a dynamic one (think of a player)
        bodyDef.position.set(x/PPM, y/PPM);
        bodyDef.fixedRotation = true;
        Body body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width/2/PPM, height/2/PPM);

        FixtureDef fixtureDef = new FixtureDef();

        fixtureDef.shape = shape;
        fixtureDef.isSensor = isSensor;
        fixtureDef.friction = 0;
        body.createFixture(fixtureDef);
        shape.dispose();
        return body;
    }
}
