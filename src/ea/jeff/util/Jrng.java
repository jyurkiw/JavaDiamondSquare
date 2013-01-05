package ea.jeff.util;

/**
 * Jeff's shitty RNG algorithm
 * @author jyurkiw
 *
 */
public class Jrng extends Xorshift {
	public Jrng(int a, int b, int c, int d) {
		super(a, b, c, d);
	}
	
	public Jrng() {
		super();
	}
}