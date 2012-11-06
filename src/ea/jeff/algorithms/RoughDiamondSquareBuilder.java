package ea.jeff.algorithms;

public class RoughDiamondSquareBuilder extends DiamondSquareBuilder implements DiamondSquareImplementation {
	private float topLeft;
	private float topRight;
	private float lowerLeft;
	private float lowerRight;
	
	public RoughDiamondSquareBuilder() {
		super();
	}
	
	@Override
	public DiamondSquare build(DiamondSquare base) {
		initForBuild(base);
		
		cycle(topLeft, topRight, lowerLeft,	lowerRight);
		
		return diamondSquare;
	}
	
	private void initForBuild(DiamondSquare base) {
		this.diamondSquare = base;
		
		maxDimension = diamondSquare.getDimension();
		currDimension = maxDimension;
	}

	@Override
	public void setCornerValues(float topLeft, float topRight, float lowerLeft,	float lowerRight) {
		this.topLeft = topLeft;
		this.topRight = topRight;
		this.lowerLeft = lowerLeft;
		this.lowerRight = lowerRight;
	}
}
