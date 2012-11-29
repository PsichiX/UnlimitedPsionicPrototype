package com.psionic.upp;

import android.util.Log;

import com.PsichiX.XenonCoreDroid.HighLevel.Graphics.Camera2D;
import com.PsichiX.XenonCoreDroid.XeApplication.Touch;
import com.PsichiX.XenonCoreDroid.XeApplication.Touches;
import com.PsichiX.XenonCoreDroid.XeAssets;
import com.PsichiX.XenonCoreDroid.XeUtils.Matrix;
import com.PsichiX.XenonCoreDroid.HighLevel.Graphics.Sprite;
import com.psionic.upp.helper.SpriteSheet;

public class Actor extends Sprite {
	
	public static final float gravityYconst = 350.0f;
	
	float [] movement = new float[] { 0.0f, 0.0f };
	float gravityY = 0.0f;

	float radius;
	
	boolean canGravity = true;
	
	public Actor(XeAssets assets, int sheetResId, String subImage) {
		super(null);
		SpriteSheet sheet = (SpriteSheet)assets.get(sheetResId, SpriteSheet.class);
		sheet.getSubImage(subImage).apply(this);
		setOffsetFromSize(0.5f, 0.5f);
		
		radius = getHeight();
	}
	
	public void setMovement(float[] mv) {
		movement = mv;
	}
	
	public void setGravityY(float gv){
		gravityY = gv;
	}
	
	public float getGravityY()
	{
		return gravityY;
	}
	
	public void setCanGravity(boolean cg){
		canGravity = cg;
	}
	
	public void setRadius(float r){
		radius = r;
	}
	
	public float getRadius(){
		return radius;
	}
	
	@Override
	public void update(float dt, Matrix cam){
		
		Camera2D camera = (Camera2D)(getScene().getCamera());
			
		gravityY += gravityYconst * dt;
		
		float move_x = movement[0]*dt;
		float move_y = movement[1]*dt + gravityY * dt;
		
		setPosition(getPositionX() + move_x, getPositionY() + move_y); 
		
		if(getPositionY() > camera.getViewHeight() - getHeight()*0.5f){
			gravityY=0.0f;
			setPosition(getPositionX(),camera.getViewHeight() - getHeight()*0.5f);
		} else if(getPositionY() < getHeight()*0.5f){
			gravityY = 0.0f;
			setPosition(getPositionX(), getHeight()*0.5f);
		}
		
		if(getPositionX() < camera.getViewPositionX() - camera.getViewWidth() - getWidth())
			kill();
		
		super.update(dt, cam);
	}
	
	public void input(Touches touches)
	{
	}
	
	public void testCollisionWith(Actor actor)
	{
		float sum_r = radius + actor.getRadius();
		float dx = getPositionX() - actor.getPositionX();
		float dy = getPositionY() - actor.getPositionY();
		
		if(sum_r*sum_r > (dx*dx + dy*dy))
			actor.onCollisionWith(this);
		
		//Log.w("collision test","sum_r:" + sum_r);
		//Log.w("collision test","dx:" + dx + " dy:"+dy);
	}
	
	public void onCollisionWith(Actor actor)
	{
		Log.d("COLLISION","COLLISION");
	}
		
	public void kill()
	{
		GameState.hitAct.add(this);
	}

}
