package ea.jeff.util;

public abstract class PRNGBase {
	public abstract int next();
	
	public int next(int max) {
		return next() % max;
	}
	
	public int next(int min, int max) {
		return next(max - min) + min;
	}
	
	public long nextLong() {
		return (long)((long)next() << 32) + (long)next();
	}
	
	public long nextLong(long max) {
		return nextLong() % max;
	}
	
	public long nextLong(long min, long max) {
		return nextLong(max - min) + min;
	}
	
	public float nextFloat() {
		return (float)(next() % Integer.MAX_VALUE);
	}
	
	public float nextFloat(float max) {
		return nextFloat() % max;
	}
	
	public float nextFloat(float min, float max) {
		return nextFloat(max - min) + min;
	}
	
	public double nextDouble() {
		return (double)(next() / Integer.MAX_VALUE);
	}
	
	public double nextDouble(double max) {
		return nextDouble() % max;
	}
	
	public double nextDouble(double min, double max) {
		return nextDouble(max - min) + min;
	}
	
	public boolean nextBoolean() {
		return next() % 2 == 0;
	}
	
	public boolean[] nextBooleanArray(int states) {
		boolean[] stateArr = new boolean[states];
		for (int x = 0; x < states; x++) {
			stateArr[x] = nextBoolean();
		}
		
		return stateArr;
	}
	
	public boolean[][] nextBooleanMultiArray(int rows, int states) {
		boolean[][] stateArr = new boolean[rows][states];
		
		for (int row = 0; row < rows; row++) {
			for (int state = 0; state < states; state++) {
				stateArr[row][state] = nextBoolean();
			}
		}
		
		return stateArr;
	}
	
	public int nextCoin() {
		if (nextBoolean()) {
			return 1;
		} else {
			return -1;
		}
	}
}
