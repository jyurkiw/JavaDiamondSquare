package ea.jeff.algorithms;

import ea.jeff.util.Point;

public class DiamondSquare {
	private int dimension;
	private int size;
	private float[][] data;
	
	public DiamondSquare(int size) {
		this.size = size;
		dimension = (int)Math.pow(2, size);
		data = new float[dimension + 1][dimension + 1];
	}
	
	public float getHeightAt(int x, int y) {
		return data[x][y];
	}
	
	public float getHeightAt(Point p) {
		return data[p.getX()][p.getY()];
	}
	
	public void setHeightAt(int x, int y, float height) {
		data[x][y] = height;
	}
	
	public void setHeightAt(Point p, float height) {
		data[p.getX()][p.getY()] = height;
	}

	public int getDimension() {
		return dimension;
	}

	public int getSize() {
		return size;
	}
	
	public float getMinHeight() {
		float min = data[0][0];
		
		for (int x = 0; x < data.length; x++) {
			for (int y = 0; y < data[x].length; y++) {
				if (data[x][y] < min) {
					min = data[x][y];
				}
			}
		}
		
		return min;
	}
	
	public float getMaxHeight() {
		float max = data[0][0];
		
		for (int x = 0; x < data.length; x++) {
			for (int y = 0; y < data[x].length; y++) {
				if (data[x][y] > max) {
					max = data[x][y];
				}
			}
		}
		
		return max;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DiamondSquare [dimension=");
		builder.append(dimension);
		builder.append(", ");
		if (data != null) {
			builder.append("data=[\n");
			for (int x = 0; x < data.length; x++) {
				builder.append(String.format("\t%3d=[", x));
				for (int y = 0; y < data[x].length; y++) {
					builder.append(String.format("%3d=[%6.3f], ", y, data[x][y]));
				}
				builder.append("],\n");
			}
		}
		builder.append("]");
		return builder.toString();
	}
}
