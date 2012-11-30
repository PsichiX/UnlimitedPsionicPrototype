package com.psionic.upp;

import com.PsichiX.XenonCoreDroid.XeAssets;
import com.PsichiX.XenonCoreDroid.HighLevel.Graphics.Camera2D;
import com.PsichiX.XenonCoreDroid.HighLevel.Graphics.Image;
import com.PsichiX.XenonCoreDroid.HighLevel.Graphics.Material;
import com.PsichiX.XenonCoreDroid.HighLevel.Graphics.Sprite;

public class Background extends Sprite {

	float size;
	
	public Background(Material mat) {
		super(mat);
	}
	
	public Background(XeAssets assets, Camera2D cam){
		super(null);
		/*Material bgMat = (Material) assets.get(R.raw.material,Material.class);
		Image bgImage = (Image) assets.get(R.drawable.background,Image.class);
		size = (float)bgImage.getTexture().getWidth();
		setMaterial(bgMat);
		setSize(cam.getViewWidth(), cam.getViewHeight());
		setTextureScaleFromImageAspect(bgImage, true);
		setOffsetFromSize(0.5f, 0.5f);*/
	}
	
	public void setOnDistance(float distance) {
		
		float d = (size != 0.0f) ? distance/size : 0.0f;
		
		setTextureOffset(d, 0.0f);
	}

}
