package com.psionic.upp.helper;

import com.PsichiX.XenonCoreDroid.XeAssets;
import com.PsichiX.XenonCoreDroid.XeUtils.Resource;
import com.PsichiX.XenonCoreDroid.XeUtils.Xml;
import com.PsichiX.XenonCoreDroid.XeAssets.*;
import com.PsichiX.XenonCoreDroid.HighLevel.Graphics.*;
import java.util.HashMap;

public class SpriteSheet extends Asset
{
	private Image _image;
	private Material _material;
	private HashMap<String, SubImage> _subImages = new HashMap<String, SubImage>();
	
	protected SpriteSheet(XeAssets a, String n)
	{
		super(a, n);
	}
	
	protected SpriteSheet(XeAssets a, Integer id)
	{
		super(a, id);
	}
	
	protected boolean onLoadAsset(Xml xml, Resource res)
	{
		_subImages.clear();
		String imgpath = xml.getValue("TextureAtlas", "imagePath");
		int imgid = Resource.getId(getOwner().getContext(), imgpath);
		_image = (Image)getOwner().get(imgid, Image.class);
		if(_image == null)
			return false;
		String matpath = xml.getValue("TextureAtlas", "materialPath");
		int matid = Resource.getId(getOwner().getContext(), matpath);
		_material = (Material)getOwner().get(matid, Material.class);
		Xml.Element elm = xml.get("TextureAtlas");
		if(elm == null)
			return false;
		for(Xml.Element child : elm.getElements())
		{
			if(!child.getName().equals("SubTexture"))
				continue;
			String name = child.getValue("", "name");
			String sx = child.getValue("", "x");
			String sy = child.getValue("", "y");
			String sw = child.getValue("", "width");
			String sh = child.getValue("", "height");
			try
			{
				int x = Integer.parseInt(sx);
				int y = Integer.parseInt(sy);
				int w = Integer.parseInt(sw);
				int h = Integer.parseInt(sh);
				_subImages.put(name, new SubImage(this, x, y, w, h));
			}
			catch(Exception ex){}
		}
		return true;
	}
	
	public Image getImage()
	{
		return _image;
	}
	
	public Material getMaterial()
	{
		return _material;
	}
	
	public SubImage getSubImage(String name)
	{
		return _subImages.containsKey(name) ? _subImages.get(name) : null;
	}
	
	public String[] getSubImageNames()
	{
		return (String[])_subImages.keySet().toArray();
	}
	
	public class SubImage
	{
		private SpriteSheet _owner;
		private float _x = 0.0f;
		private float _y = 0.0f;
		private float _w = 0.0f;
		private float _h = 0.0f;
		private float[] _coords;
		
		protected SubImage(SpriteSheet owner, float x, float y, float w, float h)
		{
			_owner = owner;
			float tw = owner.getImage().getTexture().getWidth();
			float th = owner.getImage().getTexture().getHeight();
			float optw = 1.0f / tw;
			float opth = 1.0f / th;
			_x = x;
			_y = y;
			_w = w;
			_h = h;
			float tcx = x * optw;
			float tcy = y * opth;
			float tcw = w * optw;
			float tch = h * opth;
			_coords = new float[]{
				tcx, tcy,
				tcx + tcw, tcy,
				tcx + tcw, tcy + tch,
				tcx, tcy + tch
			};
		}
		
		public SpriteSheet getSheet()
		{
			return _owner;
		}
		
		public float getX()
		{
			return _x;
		}
		
		public float getY()
		{
			return _y;
		}
		
		public float getWidth()
		{
			return _w;
		}
		
		public float getHeight()
		{
			return _h;
		}
		
		public float[] getTexCoords()
		{
			return _coords;
		}
		
		public void apply(Sprite spr)
		{
			Material mat = getSheet().getMaterial();
			if(mat != null)
				spr.setMaterial(mat);
			spr.getVertexArray("vTexCoord").update(getTexCoords(), 0);
			spr.setSize(_w, _h);
		}
	}
}
