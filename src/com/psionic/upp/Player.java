package com.psionic.upp;

import android.util.Log;

import com.PsichiX.XenonCoreDroid.XeAssets;
import com.PsichiX.XenonCoreDroid.HighLevel.Graphics.*;
import com.psionic.upp.helper.SpriteSheet;

public class Player extends Actor
{
	public Player(XeAssets assets, int sheetResId, String subImage)
	{
		super(assets, sheetResId, subImage);
	}
	
	@Override
	public void onCollisionWith(Actor actor){
		actor.hitted();
		Log.d("COLLISION","WITH PLAYER");
		
	}
}
