package com.psionic.upp;

import java.util.ArrayList;

import com.psionic.upp.Decal.BuildInfo.ImageInfo;

public class BackgroundChain
{
	/*private Layer[] layers;
	
	public BackgroundChain(Layer[] r)
	{
		layers = r;
	}
	
	public Layer[] getLayers()
	{
		return layers;
	}
	
	public void buildDecalArea(Pattern[] src, Decal tar, float fromX, float fromY, float toX, float toY)
	{
		ArrayList<Decal.BuildInfo.ImageInfo> images = new ArrayList<Decal.BuildInfo.ImageInfo>();
		for(Layer lay : layers)
		{
			float x = fromX + lay.getOffsetX();
			float y = fromY + lay.getOffsetY();
			String[] pats = lay.getPatterns();
			if(pats != null && pats.length > 0)
			{
				int idx = 0;
				while(true)
				{
					String subName = pats[idx];
					idx++;
					idx %= pats.length;
					Pattern pat = null;
					for(Pattern p : src)
						if(p.getSubImage().equals(subName))
						{
							pat = p;
							break;
						}
					if(pat == null)
						break;
					Decal.BuildInfo.ImageInfo im = new Decal.BuildInfo.ImageInfo(subName, x + pat.getOffsetX(), y + pat.getOffsetY(), pat.getWidth(), pat.getHeight());
					x += pat.getAdvanceX();
					y += pat.getAdvanceY();
					
					images.add(im);
					if(x > toX || y > toY)
						break;
				}
			}
			
		}
	}
	
	public void buildDecal(Pattern[] p, Decal d)
	{
		int c = 0;
		for(Layer r : layers)
			c += r.getPatterns().length;
		Decal.BuildInfo.ImageInfo[] im = new Decal.BuildInfo.ImageInfo[c];
		int i = 0;
		for(Layer r : layers)
		{
			float x = r.getOffsetX();
			float y = r.getOffsetY();
			String[] pats = r.getPatterns();
			for(String pat : pats)
			{
				for(Pattern pt : p)
				{
					if(!pt.getSubImage().equals(pat))
						continue;
					im[i] = new Decal.BuildInfo.ImageInfo(pat, x + pt.getOffsetX(), y + pt.getOffsetY(), pt.getWidth(), pt.getHeight());
					i++;
					x += pt.getAdvanceX();
					y += pt.getAdvanceY();
					break;
				}
			}
			r.setLength(x);
		}
		Decal.BuildInfo bi = new Decal.BuildInfo(im);
		d.build(bi);
	}
	
	public static class Layer
	{
		private String[] patterns;
		private float offsetX = 0.0f;
		private float offsetY = 0.0f;
		private float advanceX = 0.0f;
		private float advanceY = 0.0f;
		
		public Layer(String[] pats)
		{
			patterns = pats;
		}
		
		public String[] getPatterns()
		{
			return patterns;
		}
		
		public float getOffsetX()
		{
			return offsetX;
		}
		
		public float getOffsetY()
		{
			return offsetY;
		}
	}
	
	public static class Pattern
	{
		private String subImage;
		private float offsetX = 0.0f;
		private float offsetY = 0.0f;
		private float advanceX = 0.0f;
		private float advanceY = 0.0f;
		private float width = 0.0f;
		private float height = 0.0f;
		
		public Pattern(String subImgName, float offX, float offY, float advX, float advY, float w, float h)
		{
			subImage = subImgName;
			offsetX = offX;
			offsetY = offY;
			advanceX = advX;
			advanceY = advY;
			width = w;
			height = h;
		}
		
		public String getSubImage()
		{
			return subImage;
		}
		
		public float getOffsetX()
		{
			return offsetX;
		}
		
		public float getOffsetY()
		{
			return offsetY;
		}
		
		public float getAdvanceX()
		{
			return advanceX;
		}
		
		public float getAdvanceY()
		{
			return advanceY;
		}
		
		public float getWidth()
		{
			return width;
		}
		
		public float getHeight()
		{
			return height;
		}
	}*/
}
