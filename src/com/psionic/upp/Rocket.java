package com.psionic.upp;

import java.util.Random;

import com.PsichiX.XenonCoreDroid.XeAssets;
import com.PsichiX.XenonCoreDroid.XeUtils.Matrix;

public class Rocket extends Actor
{
	
	public Rocket(XeAssets assets){
		super(assets,R.raw.dynamics,"rocket.png");
	}
	
	public Rocket(XeAssets assets, int sheetResId, String subImage) {
		super(assets, sheetResId, subImage);
	}
	
	@Override
	public void update(float dt, Matrix cam){
		setGravityY(-gravityYconst*dt);
		super.update(dt, cam);
	}
	
	@Override
	public void onAttach(ActorsManager am){
		// fixed x, ranodm y
		float [] pos = calculateMinMaxPosition();
		Random rand = new Random();
		float randPosY = pos [1] + (pos[3] - pos[1]) * rand.nextFloat();
		setPosition(pos[2], randPosY);
		
		super.onAttach(am);
	}
	
	@Override
	public void onCollisionWith(Actor actor)
	{
		kill();
	}
}
