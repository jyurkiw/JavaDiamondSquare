package ea.jeff.util;

import java.util.LinkedList;

public class Xorshift extends PRNGBase {
	protected LinkedList<Integer> seeds;
	protected int t;

	public Xorshift (int x, int y, int z, int w) {
		seeds = new LinkedList<Integer>();
		
		seeds.add(x);
		seeds.add(y);
		seeds.add(z);
		seeds.add(w);
	}
	
	public Xorshift() {
		long time = System.currentTimeMillis();
		int t1 = (int)(0x0000FFFF & time);
		int t2 = Math.abs((int)(time >> 32) - t1);
		int t3 = ((t1 ^ 0) >> 9) + (t2 << 5);
		int t4 = t1 & ((t2 << 9) + (t1 >> 5));
		
		seeds = new LinkedList<Integer>();
		
		seeds.add(t1);
		seeds.add(t2);
		seeds.add(t3);
		seeds.add(t4);
	}

	public int next() {
		t = seeds.pop();
		t = t ^ (t << 11);
		seeds.addLast(seeds.peekLast() ^ (seeds.peekLast() >> 19) ^ (t ^ (t >> 8)));
		return seeds.peekLast();
	}
}
