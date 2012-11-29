package com.psionic.upp;

import com.PsichiX.XenonCoreDroid.XeApplication.Touches;

public interface IForce
{
	void onActivate();
	void onUpdate(float dt);
	void onInput(Touches touches);
	void onDeactivate();
}
