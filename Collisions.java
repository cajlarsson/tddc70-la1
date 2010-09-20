import java.io.*;
import java.util.Random;

class Collisions {
	static final int tablesize = 1000003;
	static Random r = new Random();
	static boolean[] check = new boolean[tablesize];

	/* Returns the next uniformly distributed random hash value */
	static int nextHashValue() {
		return r.nextInt(tablesize);
	}

	public static void main(String[] args) throws IOException {

		/* Sample risk of collision for i keys */
		for (int i = 0; i < 10000;i += 50) {
			/* Set counter to zero */
			int cnt = 0;
			/* Make 100 samples for each i */
			for (int j = 1; j <= 100; j++) {
				/* Reset the check array */
				for (int m = 0; m < check.length; m++) {
					check[m] = false;
				}
				/* Hash i values and check for the first collision */
				for (int k=0;k<i;k++) {
					int h = nextHashValue();
					if(check[h]) {
						cnt++; // increase counter if a collision occured
						break;
					}
					else {
						check[h] = true;
					}
				}
			}
			/* Print sample risk */
			System.out.println(i + " " + ((double) cnt)/100.0);
		}
	}
}
