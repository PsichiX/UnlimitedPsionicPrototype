package com.psionic.upp;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import android.util.Log;

import com.PsichiX.XenonCoreDroid.XeApplication.State;
import com.PsichiX.XenonCoreDroid.XeApplication.Touch;
import com.PsichiX.XenonCoreDroid.XeApplication.Touches;
import com.PsichiX.XenonCoreDroid.XeUtils.CommandQueue;
import com.PsichiX.XenonCoreDroid.XeUtils.RunnableQueue;
import com.PsichiX.XenonCoreDroid.HighLevel.Graphics.Camera2D;
import com.PsichiX.XenonCoreDroid.HighLevel.Graphics.Image;
import com.PsichiX.XenonCoreDroid.HighLevel.Graphics.Material;
import com.PsichiX.XenonCoreDroid.HighLevel.Graphics.Scene;
import com.PsichiX.XenonCoreDroid.HighLevel.Graphics.Sprite;
import com.PsichiX.XenonCoreDroid.XeEcho.*;
import com.psionic.upp.helper.SpriteSheet;

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
	
	float accel = 0.0f;
	
	//public static RunnableQueue rq = new RunnableQueue();
	
	public static ArrayList<Actor> hitAct = new ArrayList<Actor>();
	
	Player player;
	
	public static ArrayList<Actor> actors = new ArrayList<Actor>();
	
	@Override
	public void onEnter()
	{
		Music m = getApplication().getEcho().loadMusic("background_sound", R.raw.am_bg_sound);
		m.play();
		
		scn = (Scene)getApplication().getAssets().get(R.raw.scene, Scene.class);
		cam = (Camera2D)scn.getCamera();
		cam.setViewPosition(cam.getViewWidth() * 0.5f, cam.getViewHeight() * 0.5f);
		
		// dynamic actors spritesheet
		//SpriteSheet sheet = (SpriteSheet)getApplication().getAssets().get(R.raw.dynamics, SpriteSheet.class);
		
		// background scroll
		background = new Background(getApplication().getAssets(),cam);
		scn.attach(background);
		
		distance = 0.0f;
		
		player = new Player(getApplication().getAssets(), R.raw.dynamics, "proto.png");
		scn.attach(player);
		
		/*Material mat = (Material) getApplication().getAssets().get(R.raw.proto_material, Material.class);
		Image actImg = (Image) getApplication().getAssets().get(R.drawable.proto, Image.class);
		player = new Actor(mat,actImg);

		actors.add(player);
		player.setPosition(cam.getViewWidth() * 0.5f, cam.getViewHeight() * 0.5f);
		scn.attach(background);
		scn.attach(player);
		
		rocketMat = (Material) getApplication().getAssets().get(R.raw.rocket_material, Material.class);
		rocketImg = (Image) getApplication().getAssets().get(R.drawable.rocket, Image.class);*/

//		Material mat = (Material)getApplication().getAssets().get(R.raw.material, Material.class);
//		Sprite spr = new Sprite(mat);
//		SpriteSheet sheet = (SpriteSheet)getApplication().getAssets().get(R.raw.spritesheet, SpriteSheet.class);
//		SpriteSheet.SubImage sub = sheet.getSubImage("schroom.png");
//		sub.apply(spr);
//		
//		scn.attach(spr);
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
		
		accel += dt;
		
		cam.setViewPosition(cam.getViewWidth()*0.5f + distance, cam.getViewHeight()*0.5f);
		background.setPosition(cam.getViewPositionX(), cam.getViewPositionY());
		
		player.setPosition(distance + player.getWidth(), player.getPositionY());
		if(isFly)
			player.setGravityY(player.getGravityY() - 50.0f);
		
//		for(Actor actA : actors){
//			for(Actor actB : actors){
//				if(actA != actB && )
//					actA.testCollisionWith(actB);
//			}
		
		for(Actor a: actors)
		{
			a.testCollisionWith(player);
		}
		
		scn.update(dt);
		
		if(accel > 4.0f){
			Rocket rocket = new Rocket(getApplication().getAssets(), R.raw.dynamics, "rocket.png");
			rocket.setPosition(cam.getViewPositionX() + cam.getViewWidth()*0.5f + rocket.getWidth(), new Random().nextFloat() * cam.getViewHeight());
			rocket.setMovement(new float[] {-5.0f * new Random().nextFloat()*50,0.0f});
			actors.add(rocket);
			scn.attach(rocket);
			
			accel = 0.0f;
		}
		
		Iterator<Actor> it = actors.iterator();
		
		while(it.hasNext()){
			Actor a = (Actor) it.next();
			if(a.getPositionX() < cam.getViewPositionX() - cam.getViewWidth() - a.getWidth())
			{
				scn.detach(a);
				it.remove();
			}
		}
		
		Iterator<Actor> itHitAct = hitAct.iterator();
		while(itHitAct.hasNext()){
			Actor a = (Actor) itHitAct.next();
			scn.detach(a);
			itHitAct.remove();
		}
		
		hitAct.clear();
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
		getApplication().getEcho().getMusic("background_sound").stop();
		getApplication().getEcho().unloadAll();
		actors.clear();
		
		scn.detachAll();
	}
}
