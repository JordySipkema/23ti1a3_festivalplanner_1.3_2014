package nl.avans.festivalplanner.utils;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import nl.avans.festivalplanner.model.simulator.Element;
import nl.avans.festivalplanner.model.simulator.Vector;
public class Utils 
{
	public static int getPercentOfValue(int maxValue, int percent)
	{
		return (int)((maxValue / 100) * percent);
	}
	
	public static String getTimeString(Calendar gc)
	{
		String timeFormat = "HH:mm";
		return new SimpleDateFormat(timeFormat).format(new Timestamp(gc.getTimeInMillis()));
	}
	
	/**
	 * measures the width of a string
	 * @param s string to be measured
	 * @return width of the string in pixels
	 * @author jack
	 */
	public static int getWidth(String s)
	{
		int width;
		
//		width = s.length() * 3;
		
		AffineTransform affinetransform = new AffineTransform();     
		FontRenderContext frc = new FontRenderContext(affinetransform,true,true);     
		Font font = new Font("Tahoma", Font.PLAIN, 12);
		width = (int)(font.getStringBounds(s, frc).getWidth());
		
		return width;
	}

	/**
	 * Crops a string to fit the maxWidth that has been passed in
	 * @param s string to be cropped
	 * @param maxWidth: max width(in pixels) the returned string has to fit in.
	 * @return a cropped string that fits the specified maxWidth
	 * @author jack
	 */
	public static String cropString(String s, int maxWidth)
	{
//		System.out.println("StringWidth and maxWidth"); //DEBUGGING PURPOSES
//		System.out.println(getWidth(s));
//		System.out.println(maxWidth);
		
		String string = s;
		
		if(getWidth(string) < maxWidth)
			return string;
			
		while(getWidth(string + "...") > maxWidth)
			string = string.substring(0, string.length() - 1);

		string += "...";
		
		return string;
	}
	
	/**
	 * writes an object to file
	 * @param file where to save the object.
	 * @param object object to be saved.
	 * 
	 * @author michiel
	 * @return
	 */
	public static boolean writeObject(File file, Object object)
	{
		try 
		{
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
		
			out.writeObject(object);
			out.flush();
			out.close();
			
			System.out.println("File wrote to: " + file.getAbsolutePath());
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return true;
	}
	
	/**
	 * reads an object from file
	 * @param file file to load
	 * @author michiel
	 * @return
	 */
	public static Object readObject(File file)
	{
		try
		{
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			return in.readObject();
		}
		catch(Exception e)
		{ return null; }
	}

	public static void drawAreaBackground(Graphics2D g, int x, int y , int width, int height)
	{
		Rectangle2D rect = new Rectangle2D.Double(x,y,width,height);
	
		g.setColor(new Color(69,69,69,188));
		g.fill(rect);

		g.setColor(Color.black);
		g.draw(rect);	
	}
	
	public static Recyclebin getRecyclebin()
	{
		return new Recyclebin();
	}
	
	public static class Recyclebin
	{
		private boolean _visible;
		private boolean _beingTouched;
		
		private Dimension _size = null;
		private Vector _position = null;

		private Rectangle _boundingBox;
		
		private Element _elementInHand = null;
		
		public Recyclebin()
		{
			_visible = false;
			_beingTouched = false;
			
			this._size = new Dimension(200, 50);
			this._position = new Vector(722/2 - (int)_size.getWidth()/2 , 0);
			
			this._boundingBox = new Rectangle(_position.getPoint(), _size);
		}
		
		public void display()
		{
			this._visible = true;
		}
		
		public void hide()
		{
			this._visible = false;
		}
		
		public void beingTouched(boolean b)
		{
			this._beingTouched = b;
		}
		
		public void setElementInHand(Element element)
		{
			this._elementInHand = element;
		}
		
		public Element getElementInHand()
		{
			return this._elementInHand;
		}
		
		public void draw(Graphics2D g2)
		{			
			if(!_visible)
				return;
			
			boolean debugmethod = false;
			
			int x = _position.getPoint().x;
			int y = _position.getPoint().y;
			
			g2.setColor(Color.white);
			
			if(debugmethod)
			{
				g2.draw(_boundingBox);
			}
			
			if(_beingTouched)
			{
				g2.setColor(Color.red);
			}

			x += 45;
			y += 10;
			
			g2.setStroke(new BasicStroke(3));
			g2.drawLine(x, y, x+15, y+15);
			g2.drawLine(x, y+15, x+15, y);

			x += 30;
			y += 15;
			
			Font font = new Font("SANS_SERIF", Font.BOLD, 21);
			g2.setFont(font);
			g2.drawString(Enums.Text.Remove.toString(), x, y);
		}
		
		public boolean contains(Point point)
		{
			return _boundingBox.contains(point);
		}
	}
}
