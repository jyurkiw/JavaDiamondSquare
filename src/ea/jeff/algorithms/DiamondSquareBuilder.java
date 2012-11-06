package ea.jeff.algorithms;

import ea.jeff.util.Point;

public abstract class DiamondSquareBuilder {
	protected DiamondSquare diamondSquare;
	protected int maxDimension;
	protected int currDimension;
	
	protected Point topLeft;
	protected Point topRight;
	protected Point bottomLeft;
	protected Point bottomRight;
	
	protected Point top;
	protected Point right;
	protected Point bottom;
	protected Point left;
	
	protected Point center;
	
	private Jrng rand;
	protected float noise;
	
	public DiamondSquareBuilder() {
		topLeft = new Point(0, 0);
		topRight = new Point(0, 0);
		bottomLeft = new Point(0, 0);
		bottomRight = new Point(0, 0);
		
		top = new Point(0, 0);
		right = new Point(0, 0);
		bottom = new Point(0, 0);
		left = new Point(0, 0);
		
		center = new Point(0, 0);
	}
	
	public void seedRng(int a, int b) {
		rand = new Jrng(a, b);
	}
	
	/**
	 * Cycle through an iteration of the algorithm.
	 * Set the top left corner.
	 * Set the square corners.
	 * Set the center point.
	 * Set the diamond corners.
	 * Set the square corner heights.
	 * Calculate the center height.
	 * Calculate the diamond heights.
	 * @param x
	 * @param y
	 * @param topLeft
	 * @param topRight
	 * @param bottomLeft
	 * @param bottomRight
	 */
	public void cycle(int x, int y, float topLeft, float topRight, float bottomLeft, float bottomRight) {
		calcSquarePositions();
		calcCenterPosition();
		calcDiamondPositions();
		
		setCornerHeights(topLeft, topRight, bottomLeft, bottomRight);
		
		calcCenterHeight();
		calcDiamondHeights();
	}
	
	/**
	 * Cycle through an iteration of the algorithm.
	 * Set the top left corner.
	 * Set the square corners.
	 * Set the center point.
	 * Set the diamond corners.
	 * Calculate the center height.
	 * Calculate the diamond heights.
	 * @param x
	 * @param y
	 */
	public void cycle(int x, int y) {
		topLeft.setX(x);
		topLeft.setY(y);
		
		calcSquarePositions();
		calcCenterPosition();
		calcDiamondPositions();
		
		calcCenterHeight();
		calcDiamondHeights();
	}
	
	/**
	 * Perform a full cycle of the algorithm.
	 * Set the top left corner to 0, 0.
	 * Run a cycle.
	 * Check upper right corner.
	 * If upper right corner is less than the dimension
	 * 		Set upper left corner to upper right corner
	 * 		otherwise, set upper left corner to next row
	 * If upper right corner y-value is equal to the
	 * dimension, we're done.
	 * When done, cut the current dimension in half.
	 */
	public void cycle(float topLeft, float topRight, float bottomLeft, float bottomRight) {
		cycle(0, 0, topLeft, topRight, bottomLeft, bottomRight);
		
		for (int maxIndex = 1; maxIndex < diamondSquare.getSize(); maxIndex++) {
			currDimension /= 2;
			for (int yIndex = 0; yIndex < (int)Math.pow(2, maxIndex); yIndex++) {
				for (int xIndex = 0; xIndex < (int)Math.pow(2, maxIndex); xIndex++) {
					cycle(currDimension * xIndex, currDimension * yIndex);
				}
			}
		}
	}
	
	protected float addNoise(float value) {
		return value + (rand.nextFloat(value * this.noise) * rand.nextCoin());
	}
	
	public void setCornerHeights(float topLeft, float topRight, float bottomLeft, float bottomRight) {
		diamondSquare.setHeightAt(this.topLeft, topLeft);
		diamondSquare.setHeightAt(this.topRight, topRight);
		diamondSquare.setHeightAt(this.bottomLeft, bottomLeft);
		diamondSquare.setHeightAt(this.bottomRight, bottomRight);
	}
	
	/**
	 * Calculate corner positions based on current position of upper left hand corner
	 * and current dimension.
	 */
	public void calcSquarePositions() {
		topRight.setX(topLeft.getX() + currDimension);
		topRight.setY(topLeft.getY());
		
		bottomLeft.setX(topLeft.getX());
		bottomLeft.setY(topLeft.getY() + currDimension);
		
		bottomRight.setX(topLeft.getX() + currDimension);
		bottomRight.setY(topLeft.getY() + currDimension);
	}
	
	/**
	 * Calculate diamond positions based on current position of upper left hand corner,
	 * current center position, and bottom right corner.
	 */
	public void calcDiamondPositions() {
		top.setX(center.getX());
		top.setY(topLeft.getY());
		
		right.setX(bottomRight.getX());
		right.setY(center.getY());
		
		bottom.setX(center.getX());
		bottom.setY(bottomRight.getY());
		
		left.setX(topLeft.getX());
		left.setY(center.getY());
	}
	
	/**
	 * Calculate center position based on current position of upper left hand corner
	 * and current dimension.
	 */
	public void calcCenterPosition() {
		center.setX(topLeft.getX() + (currDimension / 2));
		center.setY(topLeft.getY() + (currDimension / 2));
	}
	
	/**
	 * Center height is the average of the four corners of the square.
	 */
	public void calcCenterHeight() {
		diamondSquare.setHeightAt(center, addNoise(
				diamondSquare.getHeightAt(topLeft) +
				diamondSquare.getHeightAt(topRight) +
				diamondSquare.getHeightAt(bottomLeft) +
				diamondSquare.getHeightAt(bottomRight))/4.0f);
	}
	
	/**
	 * Diamond heights are the average of their two sibling square heights.
	 */
	public void calcDiamondHeights() {
		diamondSquare.setHeightAt(top, addNoise(diamondSquare.getHeightAt(topLeft) + diamondSquare.getHeightAt(topRight))/2.0f);
		diamondSquare.setHeightAt(right, addNoise(diamondSquare.getHeightAt(bottomRight) + diamondSquare.getHeightAt(topRight))/2.0f);
		diamondSquare.setHeightAt(bottom, addNoise(diamondSquare.getHeightAt(bottomLeft) + diamondSquare.getHeightAt(bottomRight))/2.0f);
		diamondSquare.setHeightAt(left, addNoise(diamondSquare.getHeightAt(topLeft) + diamondSquare.getHeightAt(bottomLeft))/2.0f);
	}

	public float getNoise() {
		return noise;
	}

	public void setNoise(float noise) {
		this.noise = noise;
	}
}
