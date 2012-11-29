package com.psionic.upp;


import com.PsichiX.XenonCoreDroid.XeApplication.State;
import com.PsichiX.XenonCoreDroid.XeApplication.Touches;
import com.PsichiX.XenonCoreDroid.HighLevel.Graphics.Camera2D;
import com.PsichiX.XenonCoreDroid.HighLevel.Graphics.Scene;

public class GameState extends State
{
	Camera2D cam;
	Scene scn;

	@Override
	public void onEnter()
	{
		scn = (Scene)getApplication().getAssets().get(R.raw.scene, Scene.class);
		cam = (Camera2D)scn.getCamera();
		cam.setViewPosition(cam.getViewWidth() * 0.5f, cam.getViewHeight() * 0.5f);
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
