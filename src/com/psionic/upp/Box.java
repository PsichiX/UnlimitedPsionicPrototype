package com.psionic.upp;

import java.util.Random;

import com.PsichiX.XenonCoreDroid.XeApplication.Touch;
import com.PsichiX.XenonCoreDroid.XeAssets;
import com.PsichiX.XenonCoreDroid.XeApplication.Touches;
import com.PsichiX.XenonCoreDroid.XeUtils.Matrix;

public class Box extends Actor {

	private static String [] groundObstacles = { "obstacle_box.png", "barrel.png"};
	
	public static Box createRandBox(XeAssets assets)
	{
		Random r = new Random();
		String obstacleStr = groundObstacles[r.nextInt(groundObstacles.length)];
		// everything but box should have lower possibility to show
		if(!obstacleStr.equals(groundObstacles[0])) 
			obstacleStr = groundObstacles[r.nextInt(groundObstacles.length)];
		return new Box(assets,R.raw.dynamics,obstacleStr);
	}
	
	public Box(XeAssets assets){
		super(assets,R.raw.dynamics,"obstacle_box.png");
	}
	
	public Box(XeAssets assets, int sheetResId, String subImage) {
		super(assets, sheetResId, subImage);
		
	}
	
	@Override
	public void input(Touches touches)
	{
		Touch t = touches.getTouchByState(Touch.State.DOWN);
		if(t != null)
		{
			//float[] loc = 
		}
	}
	
	@Override
	public void update(float dt, Matrix cam){
		setGravityY(-gravityYconst*dt);
		super.update(dt, cam);
	}
	
	@Override
	public void onAttach(ActorsManager am){
		float [] pos = calculateMinMaxPosition();
		
		setPosition(pos[2]-getWidth(), pos[3]);
		
		;
		super.onAttach(am);
	}
	
	// TODO check conditions
//	public boolean intersetcsWith(Actor actor){
//		// (x1,y1) ------------ (x2,y2)
//		//    |                    |
//		//    |                    |
//		//    |                    |
//		// (x4,y4) ------------ (x3,y3)
//
//		float x1 = actor.getPositionX() - actor.getOffsetX();	float mx1 = getPositionX() - getOffsetX();
//		float y1 = actor.getPositionY() - actor.getOffsetY();	float my1 = getPositionY() - getOffsetY();
//
//		float x2 = x1 + actor.getWidth();						float mx2 = mx1 + getWidth();
//		float y2 = y1; 											float my2 = my1;
//
//		float x3 = x2; 											float mx3 = mx2;
//		float y3 = y2 + actor.getHeight(); 						float my3 = my2 + getHeight();
//
//		float x4 = y1;											float mx4 = my1;					
//		float y4 = y3;											float my4 = my3;
//		
//		
//		return false; // TODO
//	}
		
}
