package com.psionic.upp;

import android.util.Log;

import com.PsichiX.XenonCoreDroid.HighLevel.Graphics.Camera2D;
import com.PsichiX.XenonCoreDroid.XeApplication.Touches;
import com.PsichiX.XenonCoreDroid.XeAssets;
import com.PsichiX.XenonCoreDroid.XeUtils.MathHelper;
import com.PsichiX.XenonCoreDroid.XeUtils.Matrix;
import com.PsichiX.XenonCoreDroid.HighLevel.Graphics.Sprite;
import com.psionic.upp.helper.SpriteSheet;

public class Actor extends Sprite {
	
	public static final float gravityYconst = 500.0f;
	
	private ActorsManager owner;
	protected SpriteSheet sheet;
	private float[] movement = new float[]{0.0f, 0.0f};
	private float gravityY = 0.0f;
	private float radius;
	
	public Actor(XeAssets assets, int sheetResId, String subImage)
	{
		super(null);
		sheet = (SpriteSheet)assets.get(sheetResId, SpriteSheet.class);
		sheet.getSubImage(subImage).apply(this);
		setOffsetFromSize(0.5f, 0.5f);
		
		radius = MathHelper.vecLength(getWidth() * 0.5f, getHeight() * 0.5f, 0.0f);
	}
	
	public void onAttach(ActorsManager man)
	{
		owner = man;
	}
	
	public void onDetach(ActorsManager man)
	{
		owner = null;
	}
	
	public ActorsManager getOwner()
	{
		return owner;
	}
	
	public float[] calculateMinMaxPosition()
	{
		Camera2D camera = (Camera2D)(getScene().getCamera());
		return new float[]{
			camera.getViewPositionX() - (camera.getViewWidth() - getWidth()) * 0.5f,
			getHeight() * 0.5f + 300.0f,
			camera.getViewPositionX() + (camera.getViewWidth() + getWidth()) * 0.5f,
			camera.getViewHeight() - getHeight() * 0.5f - 100.0f
		};
	}
	
	public void setMovement(float[] mv)
	{
		movement = mv;
	}
	
	public float[] getMovement()
	{
		return movement;
	}
	
	public void setGravityY(float gv)
	{
		gravityY = gv;
	}
	
	public float getGravityY()
	{
		return gravityY;
	}
	
	public void setRadius(float r){
		radius = r;
	}
	
	public float getRadius(){
		return radius;
	}
	
	@Override
	public void update(float dt, Matrix cam)
	{
		float[] minMaxPos = calculateMinMaxPosition();		
		
		gravityY += gravityYconst * dt;
		
		float move_x = movement[0]*dt;
		float move_y = movement[1]*dt + gravityY * dt;
		
		setPosition(getPositionX() + move_x, getPositionY() + move_y); 
		
		if(getPositionX() < minMaxPos[0] || getPositionX() > minMaxPos[2])
			kill();
		if(getPositionY() < minMaxPos[1])
		{
			gravityY = 0.0f;
			setPosition(getPositionX(), minMaxPos[1]);
		}
		else if(getPositionY() > minMaxPos[3])
		{
			gravityY = 0.0f;
			setPosition(getPositionX(), minMaxPos[3]);
		}
		
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
			onCollisionWith(actor);
	}
	
	public void onCollisionWith(Actor actor)
	{
		Log.d("COLLISION","COLLISION");
	}
		
	public void kill()
	{
		getOwner().detach(this);
	}

}
