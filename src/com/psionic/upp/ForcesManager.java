package com.psionic.upp;

import java.util.HashMap;

import com.PsichiX.XenonCoreDroid.XeApplication.Touches;

public class ForcesManager
{
	private HashMap<String, ForceData> forces = new HashMap<String, ForceData>();
	
	public void addForce(String name, IForce force)
	{
		if(!forces.containsKey(name))
			forces.put(name, new ForceData(force));
	}
	
	public void removeForce(String name)
	{
		if(forces.containsKey(name))
			forces.remove(name);
	}
	
	public void activateForce(String name)
	{
		if(forces.containsKey(name))
			forces.get(name).activate();
	}
	
	public void deactivateForce(String name)
	{
		if(forces.containsKey(name))
			forces.get(name).deactivate();
	}
	
	public void update(float dt)
	{
		for(ForceData f : forces.values())
			f.update(dt);
	}
	
	public void input(Touches touches)
	{
		for(ForceData f : forces.values())
			f.input(touches);
	}
	
	private class ForceData
	{
		private IForce force;
		private boolean isActive = false;
		
		public ForceData(IForce f)
		{
			force = f;
		}
		
		public void activate()
		{
			if(!isActive)
				force.onActivate();
			isActive = true;
		}
		
		public void deactivate()
		{
			if(isActive)
				force.onDeactivate();
			isActive = false;
		}
		
		public void update(float dt)
		{
			if(isActive)
				force.onUpdate(dt);
		}
		
		public void input(Touches touches)
		{
			if(isActive)
				force.onInput(touches);
		}
	}
}
