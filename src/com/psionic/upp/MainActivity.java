package com.psionic.upp;

import com.PsichiX.XenonCoreDroid.XeActivity;
import com.PsichiX.XenonCoreDroid.XeApplication;
import com.PsichiX.XenonCoreDroid.HighLevel.Utils;
import com.PsichiX.XenonCoreDroid.HighLevel.Graphics;
import com.psionic.upp.helper.SpriteSheet;

import android.os.Bundle;

public class MainActivity extends XeActivity
{
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		// setup application before running it
		XeApplication.SETUP_SOUND_STREAMS = 4;
		XeApplication.SETUP_WINDOW_HAS_TITLE = false;
		XeApplication.SETUP_WINDOW_FULLSCREEN = true;
		XeApplication.SETUP_SCREEN_ORIENTATION = XeApplication.Orientation.LANDSCAPE;
		
		// create application
		super.onCreate(savedInstanceState);
		// run state
		Utils.initModule(getApplicationCore().getAssets());
		Graphics.initModule(getApplicationCore().getAssets(), getApplicationCore().getPhoton());
		getApplicationCore().getAssets().registerClass(SpriteSheet.class);
		getApplicationCore().getTimer().setFixedStep(1000 / 30);
		getApplicationCore().getPhoton().getRenderer().getTimer().setFixedStep(1000 / 30);
		getApplicationCore().getPhoton().getRenderer().setClearBackground(true,0.0f,0.0f,0.0f,1.0f);
		//getApplicationCore().acquireWakeLock();
		getApplicationCore().run(new MenuState());
	}
}
