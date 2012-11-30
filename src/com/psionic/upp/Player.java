package com.psionic.upp;

import android.util.Log;

import com.PsichiX.XenonCoreDroid.XeApplication.Touches;
import com.PsichiX.XenonCoreDroid.XeAssets;
import com.PsichiX.XenonCoreDroid.XeUtils.Matrix;
import com.psionic.upp.helper.SpriteSheet;

public class Player extends Actor
{
	ForcesManager forces = new ForcesManager();
	
	public Player(XeAssets assets)
	{
		super(assets, R.raw.dynamics, "monster_fly.png");
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
		
		/*float[] minMaxPos = calculateMinMaxPosition();
		float my = minMaxPos[3] - 20.0f;//Math.max(10.0f, (getGravityY() + getMovement()[1]) * dt);
		if(getPositionY() > my)
		{
			SpriteSheet.SubImage sub = sheet.getSubImage("monster_stand.png");
			if(sub != null)
			{
				sub.apply(this);
				setOffsetFromSize(0.5f, 0.5f);
			}
		}
		else
		{
			SpriteSheet.SubImage sub = sheet.getSubImage("monster_fly.png");
			if(sub != null)
			{
				sub.apply(this);
				setOffsetFromSize(0.5f, 0.5f);
			}
		}*/
	}
	
	@Override
	public void input(Touches touches)
	{
		forces.input(touches);
	}
}
