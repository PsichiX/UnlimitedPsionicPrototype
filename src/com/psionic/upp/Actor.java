package com.psionic.upp;

import com.PsichiX.XenonCoreDroid.XeUtils.Matrix;
import com.PsichiX.XenonCoreDroid.HighLevel.Graphics.Image;
import com.PsichiX.XenonCoreDroid.HighLevel.Graphics.Material;
import com.PsichiX.XenonCoreDroid.HighLevel.Graphics.Sprite;

public class Actor extends Sprite {
	
	public static final float gravityYconst= 1.0f;
	
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
	}
	
	public void setMovement(float[] mv) {
		movement = mv;
	}
	
	public void setGravityY(float gv){
		gravityY = gv;
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
		
		if(!canGravity)
			gravityY = 0.0f;
		else
			gravityY += gravityYconst * dt;
		
		float move_x = movement[0]*dt;
		float move_y = movement[1]*dt + gravityY * dt;
		
		setPosition(getPositionX() + move_x, getPositionY() + move_y); 
		
		super.update(dt, cam);
	}
	
	public boolean collisionWith(Actor actor){
		float sum_r = radius + actor.getRadius();
		float dx = getPositionX() - actor.getPositionX();
		float dy = getPositionY() - actor.getPositionY();
		
		return sum_r*sum_r > (dx*dx + dy*dy);
	}

}
