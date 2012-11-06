package ea.jeff.util;

public class Point {
	private int x, y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public void stepRight() {
		this.x++;
	}
	
	public void stepLeft() {
		this.x--;
	}
	
	public void stepDown() {
		this.y++;
	}
	
	public void stepUp() {
		this.y--;
	}
}
