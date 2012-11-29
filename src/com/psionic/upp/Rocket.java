package com.psionic.upp;

import com.PsichiX.XenonCoreDroid.XeAssets;
import com.PsichiX.XenonCoreDroid.HighLevel.Graphics.Camera2D;
import com.PsichiX.XenonCoreDroid.HighLevel.Graphics.Image;
import com.PsichiX.XenonCoreDroid.HighLevel.Graphics.Material;
import com.PsichiX.XenonCoreDroid.XeUtils.Matrix;

public class Rocket extends Actor
{
	public Rocket(XeAssets assets, int sheetResId, String subImage) {
		super(assets, sheetResId, subImage);
	}
	
	public void update(float dt, Matrix cam){
		setGravityY(-gravityYconst*dt);
		super.update(dt, cam);
	}
}
