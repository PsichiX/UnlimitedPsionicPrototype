package com.psionic.upp;

import com.PsichiX.XenonCoreDroid.XeApplication.State;
import com.PsichiX.XenonCoreDroid.XeApplication.Touch;
import com.PsichiX.XenonCoreDroid.XeApplication.Touches;
import com.PsichiX.XenonCoreDroid.HighLevel.Graphics.Camera2D;
import com.PsichiX.XenonCoreDroid.XeEcho.*;

public class GameState extends State
{
	BackgroundChain.Pattern[] bgPatterns;
	ActorsManager actors;
	Player player;
	
	boolean isFly = false;
	float distance;
	float speed = 100.0f;
	float accel = 0.0f;
	
	@Override
	public void onEnter()
	{
		Music m = getApplication().getEcho().loadMusic("background_sound", R.raw.am_bg_sound);
		m.play();
		
		actors = new ActorsManager(getApplication().getAssets(), R.raw.scene);
		Camera2D cam = (Camera2D)actors.getScene().getCamera();
		
		bgPatterns = new BackgroundChain.Pattern[]{
			new BackgroundChain.Pattern("bg_floor.png", -68.0f, 0.0f, 212.0f, 0.0f, 281.0f, 170.0f),
			new BackgroundChain.Pattern("bg_ground.jpg", 0.0f, 0.0f, 456.0f, 0.0f, 458.0f, 175.0f),
			new BackgroundChain.Pattern("bg_roof_cables.png", -68.0f, 0.0f, 424.0f, 0.0f, 493.0f, 304.0f),
			new BackgroundChain.Pattern("bg_roof_lights.png", -68.0f, 0.0f, 212.0f, 0.0f, 281.0f, 170.0f),
			new BackgroundChain.Pattern("bg_wall.jpg", 0.0f, 0.0f, cam.getViewWidth(), 0.0f, cam.getViewWidth(), 585.0f),
			new BackgroundChain.Pattern("bg_wall_down.jpg", 0.0f, 0.0f, cam.getViewWidth(), 0.0f, cam.getViewWidth(), 60.0f),
			new BackgroundChain.Pattern("bg_wall_up.jpg", -68.0f, 0.0f, 210.0f, 0.0f, 212.0f, 59.0f)
		};
		
		distance = 0.0f;
		
		Decal decal = new Decal(getApplication().getAssets(), R.raw.dynamics);
		/*decal.build(new Decal.BuildInfo(new Decal.BuildInfo.ImageInfo[]{
			new Decal.BuildInfo.ImageInfo("bg_doors.png", 0.0f, 0.0f, 0.0f, 0.0f),
			new Decal.BuildInfo.ImageInfo("bg_window.png", 300.0f, 0.0f, 0.0f, 0.0f),
			new Decal.BuildInfo.ImageInfo("bg_ground.jpg", 150.0f, 300.0f, 0.0f, 0.0f)
		}));*/
		BackgroundChain chain = new BackgroundChain(new BackgroundChain.Layer[]{
			new BackgroundChain.Layer(new String[]{
				"bg_ground.jpg",
				"bg_ground.jpg",
				"bg_ground.jpg",
				"bg_ground.jpg"
			}, 0.0f, 0.0f),
			new BackgroundChain.Layer(new String[]{
				"bg_wall.jpg"
			}, 0.0f, 398.0f),
			new BackgroundChain.Layer(new String[]{
				"bg_wall_up.jpg",
				"bg_wall_up.jpg",
				"bg_wall_up.jpg",
				"bg_wall_up.jpg",
				"bg_wall_up.jpg",
				"bg_wall_up.jpg",
				"bg_wall_up.jpg",
				"bg_wall_up.jpg"
			}, 0.0f, 345.0f),
			new BackgroundChain.Layer(new String[]{
				"bg_wall_down.jpg"
			}, 0.0f, 980.0f),
			new BackgroundChain.Layer(new String[]{
				"bg_roof_lights.png",
				"bg_roof_lights.png",
				"bg_roof_lights.png",
				"bg_roof_cables.png",
				"bg_roof_lights.png",
				"bg_roof_lights.png",
				"bg_roof_lights.png"
			}, 0.0f, 175.0f),
			new BackgroundChain.Layer(new String[]{
				"bg_floor.png",
				"bg_floor.png",
				"bg_floor.png",
				"bg_floor.png",
				"bg_floor.png",
				"bg_floor.png",
				"bg_floor.png",
				"bg_floor.png"
			}, 0.0f, 1035.0f)
		});
		chain.buildDecal(bgPatterns, decal);
		actors.getScene().attach(decal);
		
		player = new Player(getApplication().getAssets());
		player.setPosition(player.getPositionX(), cam.getViewHeight() - player.getHeight() * 0.5f - 100.0f);
		actors.attach(player);
	}

	@Override
	public void onReload()
	{
		Camera2D cam = (Camera2D)actors.getScene().getCamera();
		cam.setViewPosition(cam.getViewWidth() * 0.5f, cam.getViewHeight() * 0.5f);
	}

	@Override
	public void onUpdate()
	{
		Camera2D cam = (Camera2D)actors.getScene().getCamera();
		
		float dt = 1.0f / 30.0f;
		//float dt = getApplication().getTimer().getDeltaTime() * 0.001f;
		//distance += dt * speed;
		
		//cam.setViewPosition(cam.getViewWidth()*0.5f + distance, cam.getViewHeight()*0.5f);
		
		player.setPosition(distance + player.getWidth(), player.getPositionY());
		if(isFly)
			player.setGravityY(player.getGravityY() - 1500.0f*dt);
		
		/*accel += dt;
		if(accel > 4.0f){
			Rocket rocket = new Rocket(getApplication().getAssets(), R.raw.dynamics, "rocket.png");
			rocket.setPosition(cam.getViewPositionX() + cam.getViewWidth()*0.5f + rocket.getWidth(), new Random().nextFloat() * cam.getViewHeight());
			rocket.setMovement(new float[] {-5.0f * new Random().nextFloat()*50,0.0f});
			actors.attach(rocket);
			
			accel = 0.0f;
		}*/
		
		actors.update(dt);
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
		
		actors.input(ev);
	}

	@Override
	public void onExit()
	{
		getApplication().getEcho().getMusic("background_sound").stop();
		getApplication().getEcho().unloadAll();
		
		actors.cleanup();
	}
}
