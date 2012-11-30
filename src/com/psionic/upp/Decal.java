package com.psionic.upp;

import com.PsichiX.XenonCoreDroid.XeAssets;
import com.PsichiX.XenonCoreDroid.XePhoton;
import com.PsichiX.XenonCoreDroid.HighLevel.Graphics;
import com.PsichiX.XenonCoreDroid.HighLevel.Graphics.Shape;
import com.psionic.upp.helper.SpriteSheet;

public class Decal extends Shape
{
	SpriteSheet sheet;
	
	public Decal(XeAssets assets, int sheetResId)
	{
		super();
		sheet = (SpriteSheet)assets.get(sheetResId, SpriteSheet.class);
	}
	
	public SpriteSheet getSheet()
	{
		return sheet;
	}
	
	public void build(BuildInfo info)
	{
		release();
		BuildInfo.ImageInfo[] imgs = info.getImages();
		int vcount = 4 * imgs.length;
		int icount = 6 * imgs.length;
		XePhoton.VertexArray va = Graphics.renderer().createVertexArray(2, vcount, null);
		XePhoton.VertexArray ta = Graphics.renderer().createVertexArray(2, vcount, null);
		XePhoton.IndexArray ia = Graphics.renderer().createIndexArray(icount, null);
		int vpos = 0;
		int ipos = 0;
		for(BuildInfo.ImageInfo img : imgs)
		{
			SpriteSheet.SubImage sub = sheet.getSubImage(img.getName());
			if(sub == null)
				continue;
			/*if(img.isSizeUndefined())
				img.setSizeFromSheet(sheet);*/
			float[] verts = img.getCoords();
			va.update(verts, vpos);
			float[] coords = sub.getTexCoords();
			ta.update(coords, vpos);
			short[] inds = new short[]{
				(short)(vpos + 0),
				(short)(vpos + 1),
				(short)(vpos + 2),
				(short)(vpos + 2),
				(short)(vpos + 3),
				(short)(vpos + 0)
			};
			ia.update(inds, ipos);
			vpos += 4;
			ipos += 6;
		}
		setVertexArray("vPosition", va);
		setVertexArray("vTexCoord", ta);
		setIndexArray(ia);
		setMaterial(sheet.getMaterial());
	}
	
	public static class BuildInfo
	{
		private ImageInfo[] images;
		
		public BuildInfo(ImageInfo[] imgs)
		{
			images = imgs;
		}
		
		public ImageInfo[] getImages()
		{
			return images;
		}
		
		public static class ImageInfo
		{
			private String name;
			private float x = 0.0f;
			private float y = 0.0f;
			private float w = 0.0f;
			private float h = 0.0f;
			
			public ImageInfo(String n, float px, float py, float iw, float ih)
			{
				name = n;
				x = px;
				y = py;
				w = iw;
				h = ih;
			}
			
			/*public boolean isSizeUndefined()
			{
				return w <= 0.0f || h <= 0.0f;
			}
			
			public void setSizeFromSheet(SpriteSheet sheet)
			{
				SpriteSheet.SubImage sub = sheet.getSubImage(name);
				if(sub == null)
					return;
				w = sub.getWidth();
				h = sub.getHeight();
			}*/
			
			public String getName()
			{
				return name;
			}
			
			public float[] getCoords()
			{
				return new float[]{
					x, y,
					x + w, y,
					x + w, y + h,
					x, y + h
				};
			}
		}
	}
}
