package com.psionic.upp;

public class BackgroundChain
{
	private Layer[] rows;
	
	public BackgroundChain(Layer[] r)
	{
		rows = r;
	}
	
	public Layer[] getLayers()
	{
		return rows;
	}
	
	public void buildDecal(Pattern[] p, Decal d)
	{
		int c = 0;
		for(Layer r : rows)
			c += r.getPatterns().length;
		Decal.BuildInfo.ImageInfo[] im = new Decal.BuildInfo.ImageInfo[c];
		int i = 0;
		for(Layer r : rows)
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
		private float length = 0.0f;
		
		public Layer(String[] pats, float offX, float offY)
		{
			patterns = pats;
			offsetX = offX;
			offsetY = offY;
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
		
		public float getLength()
		{
			return length;
		}
		
		public void setLength(float v)
		{
			length = v;
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
	}
}
