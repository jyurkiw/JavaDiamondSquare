package ea.jeff.test;

import java.awt.Graphics;
import java.awt.Panel;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class SimpleImageViewer {
	public BufferedImage image;
	private JFrame frame;
	private Panel imagePanel;
	
	protected SimpleImageViewer() throws IOException {
		this.image = initImg();
		initFrame();
	}
	
	public SimpleImageViewer(BufferedImage image) {
		this.image = image;
		initFrame();
	}
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		SimpleImageViewer main = new SimpleImageViewer();
		main.run();
	}
	
	private void run() {
		Panel panel = new ImgPanel(image);
		frame.getContentPane().add(panel);
	}
	
	public void initFrame() {
		frame = new JFrame("Test Frame");
		frame.setSize((int)(image.getWidth() * 1.1), (int)(image.getHeight() * 1.15));
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		imagePanel = new ImgPanel(image);
		frame.getContentPane().add(imagePanel);
		
		frame.setVisible(true);
	}
	
	private BufferedImage initImg() throws IOException {
		//File imgFile = new File("img/Checker.bmp");
		//return ImageIO.read(imgFile);
		int h = 256, w = 256;
		BufferedImage img = new BufferedImage(h, w, BufferedImage.TYPE_INT_ARGB);
		
		//int red = 0xff000000 + 0x00ff0000 + 0x00000000 + 0x00000000;
		short max = 0, min = 255, c = max;
		int color = SimpleImageViewer.getIntPixel(c, c, c);
		
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				img.setRGB(x, y, color);
			}
		}
		
		return img;
	}
	
	public static int getIntPixel(short red, short blue, short green) {
		int color = 0x000000ff;
		
		color = color << 8;
		color = color | red;
		color = color << 8;
		color = color | green;
		color = color << 8;
		color = color | blue;
		
		return color;
	}
	
	protected class ImgPanel extends Panel {
		private static final long serialVersionUID = 1L;
		
		private BufferedImage image;
		
		public ImgPanel(BufferedImage image) {
			this.image = image;
		}
		
		public void paint(Graphics g) {
			g.drawImage(image, 0, 0, null);
		}
	}
}
