package com.psionic.upp;


import com.PsichiX.XenonCoreDroid.XeApplication.State;
import com.PsichiX.XenonCoreDroid.XeApplication.Touches;
import com.PsichiX.XenonCoreDroid.HighLevel.Graphics.Camera2D;
import com.PsichiX.XenonCoreDroid.HighLevel.Graphics.Image;
import com.PsichiX.XenonCoreDroid.HighLevel.Graphics.Material;
import com.PsichiX.XenonCoreDroid.HighLevel.Graphics.Scene;
import com.PsichiX.XenonCoreDroid.HighLevel.Graphics.Sprite;

public class GameState extends State
{
	Camera2D cam;
	Scene scn;
	
	Background background;
	
	float distance;
	float speed = 100.0f;
	
	@Override
	public void onEnter()
	{
		scn = (Scene)getApplication().getAssets().get(R.raw.scene, Scene.class);
		cam = (Camera2D)scn.getCamera();
		cam.setViewPosition(cam.getViewWidth() * 0.5f, cam.getViewHeight() * 0.5f);
		
		background = new Background(getApplication().getAssets(),cam);
		
		distance = 0.0f;
		
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
		//float dt = 1.0f / 30.0f;
		float dt = getApplication().getTimer().getDeltaTime() * 0.001f;
		distance += dt * speed;
		background.setOnDistance(distance);
		
		scn.update(dt);
	}

	@Override
	public void onInput(Touches ev)
	{
	}

	@Override
	public void onExit()
	{
	}
}
