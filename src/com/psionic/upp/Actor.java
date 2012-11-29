package com.psionic.upp;

import android.util.Log;

import com.PsichiX.XenonCoreDroid.HighLevel.Graphics.Camera2D;
import com.PsichiX.XenonCoreDroid.XeUtils.Matrix;
import com.PsichiX.XenonCoreDroid.HighLevel.Graphics.Image;
import com.PsichiX.XenonCoreDroid.HighLevel.Graphics.Material;
import com.PsichiX.XenonCoreDroid.HighLevel.Graphics.Sprite;

public class Actor extends Sprite {
	
	public static final float gravityYconst = 350.0f;
	
	float [] movement = new float[] { 0.0f, 0.0f };
	float gravityY = 0.0f;

	float radius;
	
	boolean canGravity = true;
	
	public Actor(Material mat) {
		super(mat);
	}
	
	public Actor(Material mat, Image img){
		super(mat);
		setSizeFromImage(img);
		setOffsetFromSize(0.5f, 0.5f);
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
			setPosition(getPositionX(), getHeight()*0.5f + 2.0f);
		}
		
		super.update(dt, cam);
	}
	
	public void testCollisionWith(Actor actor){
		float sum_r = radius + actor.getRadius();
		float dx = getPositionX() - actor.getPositionX();
		float dy = getPositionY() - actor.getPositionY();
		
		if(sum_r*sum_r > (dx*dx + dy*dy))
			actor.onCollisionWith(this);
	}
	
	public void onCollisionWith(Actor actor){
		Log.d("COLLISION","COLLISION");
	}

}
