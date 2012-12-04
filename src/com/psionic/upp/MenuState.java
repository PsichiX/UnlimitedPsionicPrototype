package com.psionic.upp;

import com.PsichiX.XenonCoreDroid.HighLevel.Graphics.Camera2D;
import com.PsichiX.XenonCoreDroid.HighLevel.Graphics.Image;
import com.PsichiX.XenonCoreDroid.HighLevel.Graphics.Material;
import com.PsichiX.XenonCoreDroid.HighLevel.Graphics.Scene;
import com.PsichiX.XenonCoreDroid.HighLevel.Graphics.Sprite;
import com.PsichiX.XenonCoreDroid.XeApplication.State;
import com.PsichiX.XenonCoreDroid.XeApplication.Touch;
import com.PsichiX.XenonCoreDroid.XeApplication.Touches;
import com.PsichiX.XenonCoreDroid.XeEcho.Music;

public class MenuState extends State {
	
	Camera2D cam;
	Scene scn;
	
	Sprite background;
	
	@Override
	public void onEnter()
	{
		Music m = getApplication().getEcho().loadMusic("menu_sound", R.raw.menu);
		m.play();
		
		scn = (Scene)getApplication().getAssets().get(R.raw.scene, Scene.class);
		cam = (Camera2D)scn.getCamera();
		cam.setViewPosition(cam.getViewWidth() * 0.5f, cam.getViewHeight() * 0.5f);
		
		Material matBg = (Material) getApplication().getAssets().get(R.raw.menu_bg_mat, Material.class);
		Image bgImg = (Image) getApplication().getAssets().get(R.drawable.menu_bg, Image.class);
		
		background = new Sprite(matBg);
		background.setSize(cam.getViewWidth(), cam.getViewHeight());
		
		scn.attach(background);
	}
	
	@Override
	public void onReload()
	{
		cam.setViewPosition(cam.getViewWidth() * 0.5f, cam.getViewHeight() * 0.5f);
	}

	@Override
	public void onUpdate()
	{
		float dt = getApplication().getTimer().getDeltaTime() * 0.001f;
		scn.update(dt);
	}
	
	
	@Override
	public void onInput(Touches ev)
	{
		Touch t = ev.getTouchByState(Touch.State.DOWN);
		
		if(t!= null){
			getApplication().pushState(new GameState());
		}

	}
	
	@Override
	public void onExit()
	{
		getApplication().getEcho().getMusic("menu_sound").stop();
		getApplication().getEcho().unloadAll();
		
		scn.detachAll();
		getApplication().getAssets().free(R.raw.menu_bg_mat);
		getApplication().getAssets().free(R.drawable.menu_bg);
	}
	
}
