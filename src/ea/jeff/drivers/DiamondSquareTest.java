package ea.jeff.drivers;

import java.awt.image.BufferedImage;

import ea.jeff.algorithms.DiamondSquare;
import ea.jeff.algorithms.DiamondSquareFactory;
import ea.jeff.test.SimpleImageViewer;

public class DiamondSquareTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DiamondSquareFactory factory = new DiamondSquareFactory();
		factory.setCornerValues(1.0f, 5.0f, 9.75f, 6.0f);
		factory.seedRng((int)System.currentTimeMillis(), (int)(System.currentTimeMillis() >> 32));
		DiamondSquare ds = factory.createNewDiamondSquare(9, 1.5f);
		
		BufferedImage image = DiamondSquareFactory.convertDiamondSquareToBufferedImage(ds);
		SimpleImageViewer viewer = new SimpleImageViewer(image);
	}

}
