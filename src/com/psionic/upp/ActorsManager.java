package com.psionic.upp;

import java.util.LinkedList;

import com.PsichiX.XenonCoreDroid.XeApplication.Touches;
import com.PsichiX.XenonCoreDroid.XeAssets;
import com.PsichiX.XenonCoreDroid.HighLevel.Graphics.Camera2D;
import com.PsichiX.XenonCoreDroid.HighLevel.Graphics.Scene;

public class ActorsManager
{
	private Scene scene;
	private LinkedList<Actor> actors = new LinkedList<Actor>();
	private LinkedList<Actor> actorsToAttach = new LinkedList<Actor>();
	private LinkedList<Actor> actorsToDetach = new LinkedList<Actor>();
	
	public ActorsManager(Scene scn)
	{
		scene = scn;
	}
	
	public ActorsManager()
	{
		scene = new Scene();
	}
	
	public ActorsManager(XeAssets assets, int sceneResId)
	{
		scene = (Scene)assets.get(R.raw.scene, Scene.class);
		Camera2D cam = (Camera2D)scene.getCamera();
		cam.setViewPosition(cam.getViewWidth() * 0.5f, cam.getViewHeight() * 0.5f);
	}
	
	public Scene getScene()
	{
		return scene;
	}
	
	public void attach(Actor act)
	{
		if(!actorsToAttach.contains(act))
			actorsToAttach.add(act);
	}
	
	public void detach(Actor act)
	{
		if(!actorsToDetach.contains(act))
			actorsToDetach.add(act);
	}
	
	public void update(float dt)
	{
		for(Actor a : actorsToAttach)
		{
			if(!actors.contains(a))
			{
				actors.add(a);
				scene.attach(a);
				a.onAttach(this);
			}
		}
		for(Actor a : actorsToDetach)
		{
			if(actors.contains(a))
			{
				actors.remove(a);
				scene.detach(a);
				a.onDetach(this);
			}
		}
		scene.update(dt);
		for(Actor a1 : actors)
			for(Actor a2 : actors)
				if(a1 != a2)
					a1.testCollisionWith(a2);
	}
	
	public void input(Touches touches)
	{
		for(Actor a : actors)
			a.input(touches);
	}

	public void detachAll()
	{
		for(Actor a : actors)
			detach(a);
	}
	
	public void cleanup()
	{
		actorsToAttach.clear();
		actorsToDetach.clear();
		actors.clear();
		scene.detachAll();
	}
	
	public LinkedList<Actor> getActors()
	{
		return actors;
	}
}
