package ea.jeff.algorithms;

import java.awt.image.BufferedImage;

import ea.jeff.test.SimpleImageViewer;

public class DiamondSquareFactory {
	private DiamondSquareImplementation algorithm;
	
	public DiamondSquareFactory() {
		algorithm = new RoughDiamondSquareBuilder();
	}
	
	public DiamondSquare createNewDiamondSquare(int size, float noise) {
		DiamondSquare ds = new DiamondSquare(size);
		
		if (algorithm instanceof DiamondSquareBuilder) {
			DiamondSquareBuilder builder = (DiamondSquareBuilder)algorithm;
			builder.reset();
			builder.setNoise(noise);
		}
		
		return algorithm.build(ds);
	}
	
	public void setCornerValues(float topLeft, float topRight, float lowerLeft,	float lowerRight) {
		algorithm.setCornerValues(topLeft, topRight, lowerLeft, lowerRight);
	}
	
	public static BufferedImage convertDiamondSquareToBufferedImage(DiamondSquare base) {
		BufferedImage img = new BufferedImage(base.getDimension(), base.getDimension(), BufferedImage.TYPE_INT_ARGB);
		
		float min = base.getMinHeight(), height = base.getMaxHeight() - min;
		short colorValue, black = 255;
		
		for (int x = 0; x < base.getDimension(); x++) {
			for (int y = 0; y < base.getDimension(); y++) {
				colorValue = (short) (black - (short)(black * (base.getHeightAt(x, y) - min) / height));
				img.setRGB(x, y, SimpleImageViewer.getIntPixel(colorValue, colorValue, colorValue));
			}
		}
		
		return img;
	}

	public DiamondSquareImplementation getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(DiamondSquareImplementation algorithm) {
		this.algorithm = algorithm;
	}
	
	public void seedRng(int a, int b, int c, int d) {
		if (algorithm instanceof DiamondSquareBuilder) {
			DiamondSquareBuilder builder = (DiamondSquareBuilder)algorithm;
			builder.seedRng(a, b, c, d);
		}
	}
	
	public void seedRng() {
		if (algorithm instanceof DiamondSquareBuilder) {
			DiamondSquareBuilder builder = (DiamondSquareBuilder)algorithm;
			builder.seedRng();
		}
	}
}
