package com.psionic.upp;


import java.util.ArrayList;

import android.util.Log;

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
	
	public static ArrayList<Actor> actors = new ArrayList<Actor>();
	
	@Override
	public void onEnter()
	{
		scn = (Scene)getApplication().getAssets().get(R.raw.scene, Scene.class);
		cam = (Camera2D)scn.getCamera();
		cam.setViewPosition(cam.getViewWidth() * 0.5f, cam.getViewHeight() * 0.5f);
		
		background = new Background(getApplication().getAssets(),cam);
		
		distance = 0.0f;
		
		Material mat = (Material) getApplication().getAssets().get(R.raw.proto_material, Material.class);
		Image actImg = (Image) getApplication().getAssets().get(R.drawable.proto, Image.class);
		Actor a = new Actor(mat,actImg);
		actors.add(a);
		a.setPosition(cam.getViewWidth() * 0.5f, cam.getViewHeight() * 0.5f);
		scn.attach(background);
		scn.attach(a);
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
		
		for(Actor actA : actors){
			for(Actor actB : actors){
				if(actA != actB)
					if(actA.collisionWith(actB)){
						Log.d("COLLISION","COLLISION");
					}
			}
		}
		
		scn.update(dt);
	}

	@Override
	public void onInput(Touches ev)
	{
	}

	@Override
	public void onExit()
	{
		actors.clear();
	}
}
