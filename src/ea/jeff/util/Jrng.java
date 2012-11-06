package ea.jeff.util;

import java.util.LinkedList;

/**
 * Jeff's shitty RNG algorithm
 * @author jyurkiw
 *
 */
public class Jrng {
	private LinkedList<Long> seeds;
	
	public Jrng(int seeda, int seedb) {
		seeds = new LinkedList<Long>();
		
		seeds.add((long)Math.min(seeda, seedb));
		seeds.add((long)Math.max(seeda, seedb));
		seeds.add((long)(seeda + seedb));
		seeds.add(seeds.get(seeds.size() - 2) + seeds.get(seeds.size() - 1));
		seeds.add(seeds.get(seeds.size() - 2) + seeds.get(seeds.size() - 1));
		
		for(int x = 0; x < 16; x++) next();
	}
	
	@Override
	public String toString() {
		String s = "";
		for (int x = 0; x < seeds.size(); x++)
			s += String.format("%2d: %12d\n", x, seeds.get(x));
		
		return s;
	}
	
	private long next() {
		long t = seeds.pop();
		seeds.addLast((t * (seeds.peekFirst() << 3)) ^ (t * seeds.peekLast() / 3) + 1);
		return Math.abs(t);
	}
	
	public static void main(String[] args) {
//		int x = 548124789;
//		int y = 5912;
//		
//		printi(x);
//		printi(y);
//		printi(x & y);
//		printi(x | y);
//		printi(x ^ y);
		
		int max = 20, cycles = 50000, report = cycles, val;
		Jrng shit = new Jrng(42, 302);
		int[] freq = new int[max];
		
		for (int x = 0; x < max; x++) {
			freq[x] = 0;
		}
		
		for (int x = 0; x < cycles; x++) {
			if (x % report == 0) System.out.println("At " + x/report);
			val = shit.nextInt(max);
			freq[val]++;
		}
		
		for (int x = 0; x < max; x++) {
			System.out.println(String.format("%2d: %d", x, freq[x]));
		}
		
		int outLines = 5, perLine = 30;
		Object[] vals;
		String outform = "";
		String outbase = "%2d  ";
		
		for (int x = 0; x < perLine; x++) {
			outform += outbase;
		}
		
		outform += "\n";
		
		for (int x = 0; x < outLines; x++) {
			vals = new Object[perLine];
			for (int y = 0; y < perLine; y++) {
				vals[y] = shit.nextInt(max);
			}
			System.out.println(String.format(outform, vals));
		}
		
		System.out.println("Outputting 10 float values.");
		for (int x = 0; x < 10; x++) {
			System.out.println(shit.nextFloat(0.1f));
		}
		
//		System.out.println("Speed test!");
//		long start, end, lval;
//		
//		start = System.currentTimeMillis();
//		for (long x = 0; x < 1000000000; x++) {
//			lval = shit.nextLong();
//		}
//		end = System.currentTimeMillis();
//		
//		System.out.println("1,000,000,000 long values took " + (end - start) + " milliseconds to generate.");
	}
	
	private static void printi(int i) {
		System.out.println(String.format("%32s", Integer.toBinaryString(i)).replace(" ", "0"));
	}
	
	public long nextLong() {
		return next();
	}
	
	public long nextLong(long max) {
		return nextLong() % max;
	}
	
	public long nextLong(long min, long max) {
		return nextLong(max - min) + min;
	}
	
	public int nextInt() {
		return (int)(nextLong() % Integer.MAX_VALUE);
	}
	
	public int nextInt(int max) {
		return (int)(nextLong() % max);
	}
	
	public int nextInt(int min, int max) {
		return (int)nextLong(min, max);
	}
	
	public float nextFloat() {
		return (float)nextLong() / (float)Long.MAX_VALUE;
	}
	
	public float nextFloat(float max) {
		return nextFloat() % max;
	}
	
	public float nextFloat(float min, float max) {
		return nextFloat(max - min) + min;
	}
	
	public double nextDouble() {
		return (double)nextLong() / (double)Long.MAX_VALUE;
	}
	
	public double nextDouble(double max) {
		return nextDouble() % max;
	}
	
	public double nextDouble(double min, double max) {
		return nextDouble(max - min) + min;
	}
	
	public int nextCoin() {
		if (nextLong() % 2 == 0) {
			return 1;
		} else {
			return -1;
		}
	}
}
/*
2,147,483,647

18,446,744,073,709,551,615
*/