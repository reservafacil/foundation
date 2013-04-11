package com.brazoft.foundation.util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 */
public class ImageHandler
{
	/**
	 * @param width
	 * @param height
	 * @return Returns a RenderedImage
	 */
	public static BufferedImage createRGBImage(int width, int height)
	{
		return new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	}

	/**
	 * @param data
	 * @return Returns a RenderedImage
	 * @throws IOException
	 */
	public static BufferedImage createRGBImage(byte[] data) throws IOException
	{
		return ImageIO.read(new ByteArrayInputStream(data));
	}

	/**
	 * @param scale
	 * @param background
	 * @return Returns a Square image
	 */
	public static RenderedImage createRGBSquare(int scale, Color background)
	{
		BufferedImage image;
		Graphics graphic;

		image = ImageHandler.createRGBImage(scale, scale);
		graphic = image.createGraphics();

		graphic.setColor(background);
		graphic.drawRect(0, 0, scale, scale);
		graphic.fillRect(0, 0, scale, scale);

		return image;
	}

	/**
	 * @param scale
	 * @param background
	 * @return Returns a Square image
	 */
	public static RenderedImage createRGBCircle(int scale, Color background)
	{
		BufferedImage image;
		Graphics graphic;

		image = (BufferedImage) createRGBImage(scale, scale);
		graphic = image.createGraphics();

		graphic.setColor(background);
		graphic.drawOval(0, 0, scale, scale);
		graphic.fillOval(0, 0, scale, scale);

		return image;
	}

	/**
	 * @param image
	 * @param path
	 * @throws IOException
	 */
	public static void write(RenderedImage image, String path) throws IOException
	{
		ImageHandler.write(image, new File(path));
	}

	/**
	 * @param image
	 * @param path
	 * @throws IOException
	 */
	public static void write(RenderedImage image, File path) throws IOException
	{
		ImageHandler.write(image, new FileOutputStream(path));
	}

	/**
	 * @param image
	 * @param output
	 * @throws IOException
	 */
	public static void write(RenderedImage image, OutputStream output) throws IOException
	{
		ImageIO.write(image, "PNG", output);
	}

	public static RenderedImage resize(RenderedImage image, int scale)
	{
		BufferedImage bi;
		BufferedImage resized;
		Image scaled;
		
		bi = (BufferedImage) image;
		scaled = bi.getScaledInstance(scale, scale, java.awt.Image.SCALE_SMOOTH);
		
		resized = new BufferedImage(scale, scale, BufferedImage.TYPE_INT_RGB);
		resized.createGraphics().drawImage(scaled, 0, 0, null);
		
		return resized;
	}
}