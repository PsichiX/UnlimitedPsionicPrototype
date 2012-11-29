package com.psionic.upp;


import java.util.ArrayList;
import java.util.Random;

import android.util.Log;

import com.PsichiX.XenonCoreDroid.XeApplication.State;
import com.PsichiX.XenonCoreDroid.XeApplication.Touch;
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
	
	boolean isFly = false;
	
	float distance;
	float speed = 100.0f;
	
	Material rocketMat;
	Image rocketImg;
	
	Actor player;
	
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
		player = new Actor(mat,actImg);

		actors.add(player);
		player.setPosition(cam.getViewWidth() * 0.5f, cam.getViewHeight() * 0.5f);
		scn.attach(background);
		scn.attach(player);
		
		rocketMat = (Material) getApplication().getAssets().get(R.raw.rocket_material, Material.class);
		rocketImg = (Image) getApplication().getAssets().get(R.drawable.rocket, Image.class);
		
		
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
		Random r = new Random();
		
//		float rf = r.nextFloat() * 100;
//		
//		if(rf > 80){
//			Actor enemy = new Actor(rocketMat,rocketImg);
//			enemy.setPosition(cam.getViewWidth() - , arg1)
//		}
		
		if(isFly)
			player.setGravityY(player.getGravityY() - 10.0f);
		
		for(Actor actA : actors){
			for(Actor actB : actors){
				if(actA != actB)
					actA.testCollisionWith(actB);
			}
		}
		
		scn.update(dt);
	}

	@Override
	public void onInput(Touches ev)
	{
		Touch t = ev.getTouchByState(Touch.State.DOWN);
		if(t != null)
			isFly = true;
		t = ev.getTouchByState(Touch.State.UP);
		if(t != null)
			isFly = false;
	}

	@Override
	public void onExit()
	{
		actors.clear();
	}
}
