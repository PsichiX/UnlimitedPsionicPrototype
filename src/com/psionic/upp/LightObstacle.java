package com.psionic.upp;

import com.PsichiX.XenonCoreDroid.XeAssets;
import com.PsichiX.XenonCoreDroid.XeUtils.Matrix;

public class LightObstacle extends Actor {
	
	public LightObstacle(XeAssets assets){
		super(assets,R.raw.dynamics,"obstacle_light.png");
	}
	
	@Override
	public void update(float dt, Matrix cam){
		setGravityY(-gravityYconst*dt);
		super.update(dt, cam);
	}
	
	@Override
	public void onAttach(ActorsManager am){
		float [] pos = calculateMinMaxPosition();
		
		setPosition(pos[2], pos[1]);
		
		super.onAttach(am);
	}
}
