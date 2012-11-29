package com.psionic.upp;

import android.util.Log;

import com.PsichiX.XenonCoreDroid.XeApplication.Touches;
import com.PsichiX.XenonCoreDroid.XeAssets;
import com.PsichiX.XenonCoreDroid.XeUtils.Matrix;

public class Player extends Actor
{
	ForcesManager forces = new ForcesManager();
	
	public Player(XeAssets assets, int sheetResId, String subImage)
	{
		super(assets, sheetResId, subImage);
		forces.addForce("psychokinesis", new PsychokinesisForce());
	}
	
	@Override
	public void onCollisionWith(Actor actor)
	{
		actor.kill();
		Log.d("COLLISION","WITH PLAYER");
	}
	
	@Override
	public void update(float dt, Matrix cam)
	{
		forces.update(dt);
		super.update(dt, cam);
	}
	
	@Override
	public void input(Touches touches)
	{
		forces.input(touches);
	}
}
