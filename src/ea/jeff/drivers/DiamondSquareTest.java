package ea.jeff.drivers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import ea.jeff.algorithms.DiamondSquare;
import ea.jeff.algorithms.DiamondSquareFactory;
import ea.jeff.test.SimpleImageViewer;
import ea.jeff.util.Jrng;

public class DiamondSquareTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DiamondSquareFactory factory = new DiamondSquareFactory();
		Jrng rand = new Jrng();
		float min = 200f;
		float max = 10000f;
		
		for (int x = 0; x < 10; x++) {
			factory.setCornerValues(rand.nextFloat(min, max), rand.nextFloat(min, max), rand.nextFloat(min, max), rand.nextFloat(min, max));
			factory.seedRng();
			DiamondSquare ds = factory.createNewDiamondSquare(11, 7.5f);
			
			BufferedImage image = DiamondSquareFactory.convertDiamondSquareToBufferedImage(ds);
			//SimpleImageViewer viewer = new SimpleImageViewer(image);
			
			//write the image to file
			String extension = "PNG";
			String filename = "img/" + System.currentTimeMillis() + "_" + x + "." + extension.toLowerCase();
			
			try {
				File outFile = new File(filename);
				
				outFile.createNewFile();
				if(ImageIO.write(image, extension, outFile)) {
					System.out.println("File Written!");
				} else {
					System.out.println("File NOT written!!!");
					outFile.delete();
					for (String format : ImageIO.getWriterFormatNames()) {
						System.out.println(format);
					}
				}
				
				
				
			} catch (IOException e) {
				System.out.println("Error writing image to disk!");
				e.printStackTrace();
			}
		}
	}

}
